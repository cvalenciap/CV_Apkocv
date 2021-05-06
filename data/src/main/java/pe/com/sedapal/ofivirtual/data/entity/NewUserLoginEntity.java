package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewUserLoginEntity {
    @JsonProperty("id_cliente")
    private long idCliente;
    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("admin_com")
    private int admin;
    @JsonProperty("admin_etic")
    private int adminEtic;
    @JsonProperty("flag_respuesta")
    private String flagRespuesta;

    private String descRespuesta;
    private Boolean isPendingConfirmRegister;


    public int getAdminEtic() {
        return adminEtic;
    }

    public void setAdminEtic(int adminEtic) {
        this.adminEtic = adminEtic;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getFlagRespuesta() {
        return flagRespuesta;
    }

    public void setFlagRespuesta(String flagRespuesta) {
        this.flagRespuesta = flagRespuesta;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }

    public Boolean getPendingConfirmRegister() {
        return isPendingConfirmRegister;
    }

    public void setPendingConfirmRegister(Boolean pendingConfirmRegister) {
        isPendingConfirmRegister = pendingConfirmRegister;
    }
}
