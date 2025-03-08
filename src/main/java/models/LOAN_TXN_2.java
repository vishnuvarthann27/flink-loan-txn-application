package models;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LOAN_TXN_2 {

    @JsonCreator
    public LOAN_TXN_2() {
    }

    @JsonProperty("invstr_loan_nbr")
    private String invstr_loan_nbr;

    @JsonProperty("srvcr_loan_nbr")
    private String srvcr_loan_nbr;

    @JsonProperty("txn_type")
    private String txn_type;

    @JsonProperty("grs_prin_pymt_amt")
    private String grs_prin_pymt_amt;

    @JsonProperty("grs_int_pymt_amt")
    private String grs_int_pymt_amt;

    @JsonProperty("txn_dt_date")
    private String txn_dt_date;

    @JsonProperty("txn_dt_month")
    private String txn_dt_month;

    @JsonProperty("txn_dt_year")
    private String txn_dt_year;

    @JsonProperty("next_pymt_due_dt_date")
    private String next_pymt_due_dt_date;

    @JsonProperty("next_pymt_due_dt_month")
    private String next_pymt_due_dt_month;

    @JsonProperty("next_pymt_due_dt_year")
    private String next_pymt_due_dt_year;

    @JsonProperty("prin_bal_aftr_pymt")
    private String prin_bal_aftr_pymt;



    public LOAN_TXN toLOANTxnFormat() {
        LOAN_TXN loanTXN = new LOAN_TXN();

        try {
            loanTXN.setInvstr_loan_nbr(invstr_loan_nbr != null ? Integer.parseInt(invstr_loan_nbr) : null);
            loanTXN.setSrvcr_loan_nbr(srvcr_loan_nbr != null ? Integer.parseInt(srvcr_loan_nbr) : null);
            loanTXN.setPrin_bal_aftr_pymt(prin_bal_aftr_pymt != null ? Double.parseDouble(prin_bal_aftr_pymt) : 0.00);
            loanTXN.setTxn_dt(parseDate(txn_dt_year, txn_dt_month, txn_dt_date));
            loanTXN.setNext_pymt_due_dt(parseDate(next_pymt_due_dt_year, next_pymt_due_dt_month, next_pymt_due_dt_date));

            loanTXN.setCrtl_amt(txn_type != null && txn_type.equals("Curtailment") ? Double.parseDouble(grs_prin_pymt_amt) : 0.00);
            loanTXN.setLqdtn_pymt_amt(txn_type != null && txn_type.equals("Liquidation") ? Double.parseDouble(grs_prin_pymt_amt) : 0.00);
            loanTXN.setPrin_pymt_amt(txn_type != null && txn_type.equals("Payment") ? Double.parseDouble(grs_prin_pymt_amt) : 0.00);

            loanTXN.setInt_only_pymt_amt(txn_type != null && txn_type.equals("Interest Only") ? Double.parseDouble(grs_int_pymt_amt) : 0.00);
            loanTXN.setInt_pymt_amt(txn_type != null && txn_type.equals("Payment") ? Double.parseDouble(grs_int_pymt_amt) : 0.00);

        } catch (NumberFormatException e) {
            System.err.println("Error converting number: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

        return loanTXN;
    }

    private Date parseDate(String year, String month, String day) throws ParseException  {
        if (year == null || month == null || day == null) {
            return null;
        }

        String dateString = year + "-" + month + "-" + day;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.parse(dateString);
    }
}
