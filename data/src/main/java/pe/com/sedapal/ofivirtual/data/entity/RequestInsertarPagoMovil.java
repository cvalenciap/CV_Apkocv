package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestInsertarPagoMovil {

    @JsonProperty("TRANSACTION_ID")
    String TRANSACTION_ID;
    @JsonProperty("ACTION_CODE")
    String ACTION_CODE;
    @JsonProperty("STATUS")
    String STATUS;
    @JsonProperty("TRANSACTION_DATE")
    String TRANSACTION_DATE;
    @JsonProperty("ACTION_DESCRIPTION")
    String ACTION_DESCRIPTION;
    @JsonProperty("TRACE_NUMBER")
    String TRACE_NUMBER;
    @JsonProperty("CARD")
    String CARD;
    @JsonProperty("liquidacion")
    String liquidacion;
    @JsonProperty("nis_rad")
    String numero_suministro;
    @JsonProperty("amount")
    String monto;
    @JsonProperty("correo")
    String correo;
    @JsonProperty("estadoRptVISA")
    String estadoRptVisa;
    @JsonProperty("flagChannel")
    String flagChannel;


    public String getEstadoRptVisa() {
        return estadoRptVisa;
    }

    public void setEstadoRptVisa(String estadoRptVisa) {
        this.estadoRptVisa = estadoRptVisa;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTRANSACTION_ID() {
        return TRANSACTION_ID;
    }

    public void setTRANSACTION_ID(String TRANSACTION_ID) {
        this.TRANSACTION_ID = TRANSACTION_ID;
    }

    public String getACTION_CODE() {
        return ACTION_CODE;
    }

    public void setACTION_CODE(String ACTION_CODE) {
        this.ACTION_CODE = ACTION_CODE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getTRANSACTION_DATE() {
        return TRANSACTION_DATE;
    }

    public void setTRANSACTION_DATE(String TRANSACTION_DATE) {
        this.TRANSACTION_DATE = TRANSACTION_DATE;
    }

    public String getACTION_DESCRIPTION() {
        return ACTION_DESCRIPTION;
    }

    public void setACTION_DESCRIPTION(String ACTION_DESCRIPTION) {
        this.ACTION_DESCRIPTION = ACTION_DESCRIPTION;
    }

    public String getTRACE_NUMBER() {
        return TRACE_NUMBER;
    }

    public void setTRACE_NUMBER(String TRACE_NUMBER) {
        this.TRACE_NUMBER = TRACE_NUMBER;
    }

    public String getCARD() {
        return CARD;
    }

    public void setCARD(String CARD) {
        this.CARD = CARD;
    }

    public String getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(String liquidacion) {
        this.liquidacion = liquidacion;
    }

    public String getNumero_suministro() {
        return numero_suministro;
    }

    public void setNumero_suministro(String numero_suministro) {
        this.numero_suministro = numero_suministro;
    }

    public String getFlagChannel() {
        return flagChannel;
    }

    public void setFlagChannel(String flagChannel) {
        this.flagChannel = flagChannel;
    }
}
