package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingInvoicesEntity {
    @JsonProperty("deuda")
    private double deuda;
    @JsonProperty("f_fact")
    private String fFact;
    @JsonProperty("mes")
    private String mes;
    @JsonProperty("recibo")
    private String recibo;
    @JsonProperty("nro_factura")
    private String nroFactura;
    @JsonProperty("sec_nis")
    private int secNis;
    @JsonProperty("sec_rec")
    private int secRec;
    @JsonProperty("tipo_recibo")
    private String tipoRecibo;
    @JsonProperty("vencimiento")
    private String vencimiento;
    @JsonProperty("volumen")
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
