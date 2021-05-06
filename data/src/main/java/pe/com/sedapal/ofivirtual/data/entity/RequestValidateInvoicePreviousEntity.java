package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestValidateInvoicePreviousEntity {

    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("recibo")
    private long recibo;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public long getRecibo() {
        return recibo;
    }

    public void setRecibo(long recibo) {
        this.recibo = recibo;
    }
}
