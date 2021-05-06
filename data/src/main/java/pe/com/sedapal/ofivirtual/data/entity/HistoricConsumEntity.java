package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 13/03/2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricConsumEntity {

    @JsonProperty("mes_fact")
    private String mesFact;
    @JsonProperty("volumen")
    private double volumen;
    @JsonProperty("monto")
    private double monto;

    public String getMesFact() {
        return mesFact;
    }

    public void setMesFact(String mesFact) {
        this.mesFact = mesFact;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
