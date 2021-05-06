package pe.com.sedapal.ofivirtual.domain.entity;

public class HistoricConsum {
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
