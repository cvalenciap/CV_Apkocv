package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class LoginNewUser {

    private long idCliente;
    private long nisRad;
    private int admin;
    private int adminEtic;
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

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }

    public String getFlagRespuesta() {
        return flagRespuesta;
    }

    public void setFlagRespuesta(String flagRespuesta) {
        this.flagRespuesta = flagRespuesta;
    }

    public Boolean getPendingConfirmRegister() {
        return isPendingConfirmRegister;
    }

    public void setPendingConfirmRegister(Boolean pendingConfirmRegister) {
        isPendingConfirmRegister = pendingConfirmRegister;
    }
}
