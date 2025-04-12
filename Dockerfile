
FROM maven:3.8.8-eclipse-temurin-17 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests


FROM flink:1.17-scala_2.12
WORKDIR /opt/flink
COPY --from=builder /app/target/flink-loan-txn-application-0.1.jar /opt/flink/usrlib/
EXPOSE 8081
EXPOSE 6123
CMD ["sh", "-c", "bin/jobmanager.sh start-foreground & bin/taskmanager.sh start-foreground & bin/flink run /opt/flink/usrlib/flink-loan-txn-application-0.1.jar"]






