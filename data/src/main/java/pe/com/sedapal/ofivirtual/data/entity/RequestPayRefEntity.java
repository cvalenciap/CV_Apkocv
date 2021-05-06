package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPayRefEntity {

    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("ref_cobro")
    private long referenciaCobro;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public long getReferenciaCobro() {
        return referenciaCobro;
    }

    public void setReferenciaCobro(long referenciaCobro) {
        this.referenciaCobro = referenciaCobro;
    }
}
