package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPagoLiquidacionEnchufateEntity {
    @JsonProperty("customerEmail")
    String customerEmail;
    @JsonProperty("numeroLiquidacion")
    String numeroLiquidacion;
    @JsonProperty("nisRad")
    String nisRad;
    @JsonProperty("amount")
    String amount;
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
    @JsonProperty("BRAND")
    String BRAND;
    @JsonProperty("authCorreo")
    String authCorreo;
    @JsonProperty("flagChannel")
    String flagChannel;
    @JsonProperty("listaRegistros")
    String listaRegistros;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getNumeroLiquidacion() {
        return numeroLiquidacion;
    }

    public void setNumeroLiquidacion(String numeroLiquidacion) {
        this.numeroLiquidacion = numeroLiquidacion;
    }

    public String getNisRad() {
        return nisRad;
    }

    public void setNisRad(String nisRad) {
        this.nisRad = nisRad;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getBRAND() {
        return BRAND;
    }

    public void setBRAND(String BRAND) {
        this.BRAND = BRAND;
    }

    public String getAuthCorreo() {
        return authCorreo;
    }

    public void setAuthCorreo(String authCorreo) {
        this.authCorreo = authCorreo;
    }

    public String getFlagChannel() {
        return flagChannel;
    }

    public void setFlagChannel(String flagChannel) {
        this.flagChannel = flagChannel;
    }

    public String getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(String listaRegistros) {
        this.listaRegistros = listaRegistros;
    }
}
