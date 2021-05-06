package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class PendingInvoices {

    private double deuda;
    private String fFact;
    private String mes;
    private String recibo;
    private String nroFactura;
    private int secNis;
    private int secRec;
    private String tipoRecibo;
    private String vencimiento;
    private double volumen;

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public String getfFact() {
        return fFact;
    }

    public void setfFact(String fFact) {
        this.fFact = fFact;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public int getSecNis() {
        return secNis;
    }

    public void setSecNis(int secNis) {
        this.secNis = secNis;
    }

    public int getSecRec() {
        return secRec;
    }

    public void setSecRec(int secRec) {
        this.secRec = secRec;
    }

    public String getTipoRecibo() {
        return tipoRecibo;
    }

    public void setTipoRecibo(String tipoRecibo) {
        this.tipoRecibo = tipoRecibo;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
}
