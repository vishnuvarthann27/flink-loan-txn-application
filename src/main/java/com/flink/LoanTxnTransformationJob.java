package com.flink;

import models.LOAN_TXN;
import models.LOAN_TXN_1;
import models.LOAN_TXN_2;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.formats.json.JsonDeserializationSchema;
import org.apache.flink.formats.json.JsonSerializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonGenerator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;

import java.io.InputStream;
import java.util.Properties;

public class LoanTxnTransformationJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties consumerConfig = new Properties();

        try (InputStream stream = LoanTxnTransformationJob.class.getClassLoader().getResourceAsStream("consumer.properties")) {
            consumerConfig.load(stream);
        }

        Properties producerConfig = new Properties();
        try (InputStream stream = LoanTxnTransformationJob.class.getClassLoader().getResourceAsStream("producer.properties")) {
            producerConfig.load(stream);
        }

        KafkaSource<LOAN_TXN_1> kafkaLoanTxnSource1 =  KafkaSource.<LOAN_TXN_1>builder()
                                                        .setProperties(consumerConfig)
                                                        .setTopics("LOAN_TXN_1")
                                                        .setStartingOffsets(OffsetsInitializer.latest())
                                                        .setValueOnlyDeserializer(new JsonDeserializationSchema<>(LOAN_TXN_1.class))
                                                        .build();

        KafkaSource<LOAN_TXN_2> kafkaLoanTxnSource2 =  KafkaSource.<LOAN_TXN_2>builder()
                                                        .setProperties(consumerConfig)
                                                        .setTopics("LOAN_TXN_2")
                                                        .setStartingOffsets(OffsetsInitializer.latest())
                                                        .setValueOnlyDeserializer(new JsonDeserializationSchema<>(LOAN_TXN_2.class))
                                                        .build();

        DataStream<LOAN_TXN_1> loanTxnStream1 = env.fromSource(kafkaLoanTxnSource1, WatermarkStrategy.noWatermarks(), "loan_txn_source_1");
        DataStream<LOAN_TXN_2> loanTxnStream2 = env.fromSource(kafkaLoanTxnSource2, WatermarkStrategy.noWatermarks(), "loan_txn_source_2");

        KafkaRecordSerializationSchema<LOAN_TXN> loanTxnSerializer = KafkaRecordSerializationSchema.<LOAN_TXN>builder()
                .setTopic("LOAN_TXN")
                .setKeySerializationSchema((loanTxn) -> String.valueOf(loanTxn.getInvstr_loan_nbr()).getBytes())
                .setValueSerializationSchema(new JsonSerializationSchema<LOAN_TXN>(
                        () -> new ObjectMapper().registerModule(new JavaTimeModule())
                ))
                .build();

        KafkaSink<LOAN_TXN> loanTxnSink = KafkaSink.<LOAN_TXN>builder()
                .setKafkaProducerConfig(producerConfig)
                .setRecordSerializer(loanTxnSerializer)
                .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build();

        defineWorkflow(loanTxnStream1, loanTxnStream2)
                .sinkTo(loanTxnSink)
                .name("loan_txn_sink");

        env.setRestartStrategy(RestartStrategies.exponentialDelayRestart(
                Time.seconds(5),
                Time.minutes(1),
                2.0,
                Time.minutes(5),
                0.1

        ));

        env.execute("LOAN_TXN_TRANSFORMATION");
    }

    public static DataStream<LOAN_TXN> defineWorkflow(DataStream<LOAN_TXN_1> loanTxnSource1, DataStream<LOAN_TXN_2> loanTxnSource2) {
        DataStream<LOAN_TXN> loanTxnSource1Stream = loanTxnSource1.map(LOAN_TXN_1::toLOANTxnFormat);

        DataStream<LOAN_TXN> loanTxnSource2Stream = loanTxnSource2.map(LOAN_TXN_2::toLOANTxnFormat);

        return loanTxnSource1Stream.union(loanTxnSource2Stream);
    }
}

