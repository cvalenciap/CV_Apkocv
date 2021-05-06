package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayInvoicesEntity {

    @JsonProperty("mes")
    private String mes;
    @JsonProperty("vencimiento")
    private String vencimiento;
    @JsonProperty("cobrado")
    private double cobrado;
    @JsonProperty("fecha_pago")
    private String fecha_pago;
    @JsonProperty("tipo_recibo")
    private String tipRec;
    @JsonProperty("nom_agencia")
    private String nomAgencia;
    @JsonProperty("lugar_pago")
    private String lugarPago;
    @JsonProperty("recibo")
    private String recibo;
    @JsonProperty("nro_factura")
    private String nroFactura;
    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("sec_nis")
    private long secNis;
    @JsonProperty("sec_rec")
    private long secRec;
    @JsonProperty("forma_pago")
    private String formaPago;
    @JsonProperty("hora_pago")
    private String horaPago;
    @JsonProperty("f_fact")
    private String fFact;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("total_fact")
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
