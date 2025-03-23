package models;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LOAN_TXN_1 {

    @JsonCreator
    public LOAN_TXN_1() {
    }

    @JsonProperty("invstr_loan_nbr")
    private String invstr_loan_nbr;

    @JsonProperty("txn_seq_nbr")
    private  String txn_seq_nbr;

    @JsonProperty("srvcr_loan_nbr")
    private String srvcr_loan_nbr;

    @JsonProperty("txn_cd")
    private String txn_cd;

    @JsonProperty("ovrd_cd")
    private String ovrd_cd;

    @JsonProperty("grs_prin_pymt_amt")
    private String grs_prin_pymt_amt;

    @JsonProperty("grs_int_pymt_amt")
    private String grs_int_pymt_amt;

    @JsonProperty("txn_dt")
    private String txn_dt;

    @JsonProperty("next_pymt_due_dt")
    private String next_pymt_due_dt;

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

    public String getTxn_cd() {
        return txn_cd;
    }

    public void setTxn_cd(String txn_cd) {
        this.txn_cd = txn_cd;
    }

    public String getOvrd_cd() {
        return ovrd_cd;
    }

    public void setOvrd_cd(String ovrd_cd) {
        this.ovrd_cd = ovrd_cd;
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

    public String getTxn_dt() {
        return txn_dt;
    }

    public void setTxn_dt(String txn_dt) {
        this.txn_dt = txn_dt;
    }

    public String getNext_pymt_due_dt() {
        return next_pymt_due_dt;
    }

    public void setNext_pymt_due_dt(String next_pymt_due_dt) {
        this.next_pymt_due_dt = next_pymt_due_dt;
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

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public String getTxn_seq_nbr() {
        return txn_seq_nbr;
    }

    public void setTxn_seq_nbr(String txn_seq_nbr) {
        this.txn_seq_nbr = txn_seq_nbr;
    }

    public LOAN_TXN toLOANTxnFormat(){
        LOAN_TXN loanTXN = new LOAN_TXN();

        try {
            loanTXN.setInvstr_loan_nbr(invstr_loan_nbr != null ? Integer.parseInt(invstr_loan_nbr) : null);
            loanTXN.setSrvcr_loan_nbr(srvcr_loan_nbr != null ? Integer.parseInt(srvcr_loan_nbr) : null);
            loanTXN.setTxn_seq_nbr(txn_seq_nbr != null ? Integer.parseInt(txn_seq_nbr): null);
            loanTXN.setPrin_bal_aftr_pymt(prin_bal_aftr_pymt != null ? Double.parseDouble(prin_bal_aftr_pymt) : 0.0);
            loanTXN.setTxn_dt(parseDate(txn_dt));
            loanTXN.setNext_pymt_due_dt(parseDate(next_pymt_due_dt));

            loanTXN.setCrtl_amt(txn_cd != null ? txn_cd.equals("11") ? Double.parseDouble(grs_prin_pymt_amt) : txn_cd.equals("90") && ovrd_cd.equals("11") ? -Double.parseDouble(grs_prin_pymt_amt): 0.0 : 0.0);
            loanTXN.setLqdtn_pymt_amt(txn_cd != null ? txn_cd.equals("30") ? Double.parseDouble(grs_prin_pymt_amt) : txn_cd.equals("90") && ovrd_cd.equals("30") ? -Double.parseDouble(grs_prin_pymt_amt): 0.0 : 0.0);
            loanTXN.setPrin_pymt_amt(txn_cd != null ? txn_cd.equals("02") ? Double.parseDouble(grs_prin_pymt_amt) : txn_cd.equals("90") && ovrd_cd.equals("02") ? -Double.parseDouble(grs_prin_pymt_amt): 0.0 : 0.0);

            loanTXN.setInt_only_pymt_amt(txn_cd != null ? txn_cd.equals("12") ? Double.parseDouble(grs_int_pymt_amt) : txn_cd.equals("90") && ovrd_cd.equals("12") ? -Double.parseDouble(grs_int_pymt_amt): 0.0 : 0.0);
            loanTXN.setInt_pymt_amt(txn_cd != null ? txn_cd.equals("02") ? Double.parseDouble(grs_int_pymt_amt) : txn_cd.equals("90") && ovrd_cd.equals("02") ? -Double.parseDouble(grs_int_pymt_amt): 0.0 : 0.0);

            loanTXN.setCreatedTimestamp(createdTimestamp);
        } catch (NumberFormatException e) {
            System.err.println("Error converting number: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

        return loanTXN;
    }

    private Date parseDate(String dateStr) throws ParseException {
        return (dateStr != null && !dateStr.isEmpty()) ? DATE_FORMAT.parse(dateStr) : null;
    }
}
