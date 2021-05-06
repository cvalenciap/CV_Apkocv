package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 10,diciembre,2018
 */
public class RequestInvoicePdfEntity {
    @JsonProperty("sec_rec")
    private long secRec;
    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("sec_nis")
    private long secNis;
    @JsonProperty("f_fact")
    private String fFact;

    public long getSecRec() {
        return secRec;
    }

    public void setSecRec(long secRec) {
        this.secRec = secRec;
    }

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public long getSecNis() {
        return secNis;
    }

    public void setSecNis(long secNis) {
        this.secNis = secNis;
    }

    public String getfFact() {
        return fFact;
    }

    public void setfFact(String fFact) {
        this.fFact = fFact;
    }
}
