package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by jsaenz on 11/03/2019
 */

public class PayInvoices {

    private String mes;
    private String vencimiento;
    private double cobrado;
    private String fecha_pago;
    private String tipRec;
    private String nomAgencia;
    private String lugarPago;
    private String recibo;
    private String nroFactura;
    private long nisRad;
    private long secNis;
    private long secRec;
    private String formaPago;
    private String horaPago;
    private String fFact;

    private String estado;

    private String totalFact;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTotalFact() {
        return totalFact;
    }

    public void setTotalFact(String totalFact) {
        this.totalFact = totalFact;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public double getCobrado() {
        return cobrado;
    }

    public void setCobrado(double cobrado) {
        this.cobrado = cobrado;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getTipRec() {
        return tipRec;
    }

    public void setTipRec(String tipRec) {
        this.tipRec = tipRec;
    }

    public String getNomAgencia() {
        return nomAgencia;
    }

    public void setNomAgencia(String nomAgencia) {
        this.nomAgencia = nomAgencia;
    }

    public String getLugarPago() {
        return lugarPago;
    }

    public void setLugarPago(String lugarPago) {
        this.lugarPago = lugarPago;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
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

    public long getSecRec() {
        return secRec;
    }

    public void setSecRec(long secRec) {
        this.secRec = secRec;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getHoraPago() {
        return horaPago;
    }

    public void setHoraPago(String horaPago) {
        this.horaPago = horaPago;
    }

    public String getfFact() {
        return fFact;
    }

    public void setfFact(String fFact) {
        this.fFact = fFact;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }
}
