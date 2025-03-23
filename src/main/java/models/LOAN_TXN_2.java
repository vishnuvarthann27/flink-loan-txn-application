package models;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonProperty("txn_seq_nbr")
    private  String txn_seq_nbr;

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

    @JsonProperty("created_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date createdTimestamp;

    public String getInvstr_loan_nbr() {
        return invstr_loan_nbr;
    }

    public void setInvstr_loan_nbr(String invstr_loan_nbr) {
        this.invstr_loan_nbr = invstr_loan_nbr;
    }

    public String getSrvcr_loan_nbr() {
        return srvcr_loan_nbr;
    }

    public void setSrvcr_loan_nbr(String srvcr_loan_nbr) {
        this.srvcr_loan_nbr = srvcr_loan_nbr;
    }

    public String getTxn_seq_nbr() {
        return txn_seq_nbr;
    }

    public void setTxn_seq_nbr(String txn_seq_nbr) {
        this.txn_seq_nbr = txn_seq_nbr;
    }

    public String getTxn_type() {
        return txn_type;
    }

    public void setTxn_type(String txn_type) {
        this.txn_type = txn_type;
    }

    public String getGrs_prin_pymt_amt() {
        return grs_prin_pymt_amt;
    }

    public void setGrs_prin_pymt_amt(String grs_prin_pymt_amt) {
        this.grs_prin_pymt_amt = grs_prin_pymt_amt;
    }

    public String getGrs_int_pymt_amt() {
        return grs_int_pymt_amt;
    }

    public void setGrs_int_pymt_amt(String grs_int_pymt_amt) {
        this.grs_int_pymt_amt = grs_int_pymt_amt;
    }

    public String getTxn_dt_date() {
        return txn_dt_date;
    }

    public void setTxn_dt_date(String txn_dt_date) {
        this.txn_dt_date = txn_dt_date;
    }

    public String getTxn_dt_month() {
        return txn_dt_month;
    }

    public void setTxn_dt_month(String txn_dt_month) {
        this.txn_dt_month = txn_dt_month;
    }

    public String getTxn_dt_year() {
        return txn_dt_year;
    }

    public void setTxn_dt_year(String txn_dt_year) {
        this.txn_dt_year = txn_dt_year;
    }

    public String getNext_pymt_due_dt_date() {
        return next_pymt_due_dt_date;
    }

    public void setNext_pymt_due_dt_date(String next_pymt_due_dt_date) {
        this.next_pymt_due_dt_date = next_pymt_due_dt_date;
    }

    public String getNext_pymt_due_dt_month() {
        return next_pymt_due_dt_month;
    }

    public void setNext_pymt_due_dt_month(String next_pymt_due_dt_month) {
        this.next_pymt_due_dt_month = next_pymt_due_dt_month;
    }

    public String getNext_pymt_due_dt_year() {
        return next_pymt_due_dt_year;
    }

    public void setNext_pymt_due_dt_year(String next_pymt_due_dt_year) {
        this.next_pymt_due_dt_year = next_pymt_due_dt_year;
    }

    public String getPrin_bal_aftr_pymt() {
        return prin_bal_aftr_pymt;
    }

    public void setPrin_bal_aftr_pymt(String prin_bal_aftr_pymt) {
        this.prin_bal_aftr_pymt = prin_bal_aftr_pymt;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public LOAN_TXN toLOANTxnFormat() {
        LOAN_TXN loanTXN = new LOAN_TXN();

        try {
            loanTXN.setInvstr_loan_nbr(invstr_loan_nbr != null ? Integer.parseInt(invstr_loan_nbr) : null);
            loanTXN.setSrvcr_loan_nbr(srvcr_loan_nbr != null ? Integer.parseInt(srvcr_loan_nbr) : null);
            loanTXN.setTxn_seq_nbr(txn_seq_nbr != null ? Integer.parseInt(txn_seq_nbr) : null);
            loanTXN.setPrin_bal_aftr_pymt(prin_bal_aftr_pymt != null ? Double.parseDouble(prin_bal_aftr_pymt) : 0.00);
            loanTXN.setTxn_dt(parseDate(txn_dt_year, txn_dt_month, txn_dt_date));
            loanTXN.setNext_pymt_due_dt(parseDate(next_pymt_due_dt_year, next_pymt_due_dt_month, next_pymt_due_dt_date));

            loanTXN.setCrtl_amt(txn_type != null && txn_type.equals("Curtailment") ? Double.parseDouble(grs_prin_pymt_amt) : 0.00);
            loanTXN.setLqdtn_pymt_amt(txn_type != null && txn_type.equals("Liquidation") ? Double.parseDouble(grs_prin_pymt_amt) : 0.00);
            loanTXN.setPrin_pymt_amt(txn_type != null && txn_type.equals("Payment") ? Double.parseDouble(grs_prin_pymt_amt) : 0.00);

            loanTXN.setInt_only_pymt_amt(txn_type != null && txn_type.equals("Interest Only") ? Double.parseDouble(grs_int_pymt_amt) : 0.00);
            loanTXN.setInt_pymt_amt(txn_type != null && txn_type.equals("Payment") ? Double.parseDouble(grs_int_pymt_amt) : 0.00);

            loanTXN.setCreatedTimestamp(createdTimestamp);

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
