package pe.com.sedapal.ofivirtual.domain.entity;

import java.io.Serializable;

public class LoginSupply {

    private String idUsuario;
    private String nisRad;
    private String secCta;
    private String usuAnt;
    private String flagRespuesta;
    private String descRespuesta;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getNisRad() {
        return nisRad;
    }

    public void setNisRad(String nisRad) {
        this.nisRad = nisRad;
    }

    public String getSecCta() {
        return secCta;
    }

    public void setSecCta(String secCta) {
        this.secCta = secCta;
    }

    public String getUsuAnt() {
        return usuAnt;
    }

    public void setUsuAnt(String usuAnt) {
        this.usuAnt = usuAnt;
    }
}
