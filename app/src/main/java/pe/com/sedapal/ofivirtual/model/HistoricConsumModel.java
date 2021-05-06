package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

public class HistoricConsumModel implements Serializable {
    private String mesFact;
    private double volumen;
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
