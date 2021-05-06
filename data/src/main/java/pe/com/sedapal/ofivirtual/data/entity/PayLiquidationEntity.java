package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 10/08/2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayLiquidationEntity {

    @JsonProperty("trxId")
    private String trxId;
    @JsonProperty("errorCode")
    private double errorCode;
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("fecha")
    private String fecha;
    @JsonProperty("hora")
    private String hora;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("numOperacion")
    private String numOperacion;
    @JsonProperty("numTarjeta")
    private String numTarjeta;
    @JsonProperty("numLiquidacion")
    private int numLiquidacion;
    @JsonProperty("nisRad")
    private int nisRad;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("monto")
    private double monto;
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("fechaEmision")
    private String fechaEmision;
    @JsonProperty("fechaVencimiento")
    private String fechaVencimiento;
    @JsonProperty("simboloVar")
    private String simboloVar;
    @JsonProperty("fechaHora")
    private String fechaHora;
    @JsonProperty("nombreCompleto")
    private String nombreCompleto;
    @JsonProperty("canal")
    private String canal;
    @JsonProperty("codAgencia")
    private long codAgencia;

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public double getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(double errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumOperacion() {
        return numOperacion;
    }

    public void setNumOperacion(String numOperacion) {
        this.numOperacion = numOperacion;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getNumLiquidacion() {
        return numLiquidacion;
    }

    public void setNumLiquidacion(int numLiquidacion) {
        this.numLiquidacion = numLiquidacion;
    }

    public int getNisRad() {
        return nisRad;
    }

    public void setNisRad(int nisRad) {
        this.nisRad = nisRad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getSimboloVar() {
        return simboloVar;
    }

    public void setSimboloVar(String simboloVar) {
        this.simboloVar = simboloVar;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public long getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(long codAgencia) {
        this.codAgencia = codAgencia;
    }
}
