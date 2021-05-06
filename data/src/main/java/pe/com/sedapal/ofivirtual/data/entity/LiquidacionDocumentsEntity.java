package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiquidacionDocumentsEntity {

    @JsonProperty("codigoServicio")
    private String codigoServicio;
    @JsonProperty("tipoDoc")
    private String tipoDoc;
    @JsonProperty("numeroDoc")
    private long numeroDoc;
    @JsonProperty("descripcionDoc")
    private String descripcionDoc;
    @JsonProperty("periodo")
    private String periodo;
    @JsonProperty("fechaInicioPer")
    private String fechaInicioPer;
    @JsonProperty("fechaFinPer")
    private String fechaFinPer;
    @JsonProperty("fechaEmision")
    private String fechaEmision;
    @JsonProperty("fechaVencimiento")
    private String fechaVencimiento;
    @JsonProperty("deuda")
    private float deuda;
    @JsonProperty("mora")
    private float mora;
    @JsonProperty("igv")
    private float igv;
    @JsonProperty("numeroSuministro")
    private float numeroSuministro;

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public long getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(long numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getDescripcionDoc() {
        return descripcionDoc;
    }

    public void setDescripcionDoc(String descripcionDoc) {
        this.descripcionDoc = descripcionDoc;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFechaInicioPer() {
        return fechaInicioPer;
    }

    public void setFechaInicioPer(String fechaInicioPer) {
        this.fechaInicioPer = fechaInicioPer;
    }

    public String getFechaFinPer() {
        return fechaFinPer;
    }

    public void setFechaFinPer(String fechaFinPer) {
        this.fechaFinPer = fechaFinPer;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getDeuda() {
        return deuda;
    }

    public void setDeuda(float deuda) {
        this.deuda = deuda;
    }

    public float getMora() {
        return mora;
    }

    public void setMora(float mora) {
        this.mora = mora;
    }

    public float getIgv() {
        return igv;
    }

    public void setIgv(float igv) {
        this.igv = igv;
    }

    public float getNumeroSuministro() {
        return numeroSuministro;
    }

    public void setNumeroSuministro(float numeroSuministro) {
        this.numeroSuministro = numeroSuministro;
    }
}
