package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 23/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplyLoginEntity {

    @JsonProperty("id_usuario")
    private String idUsuario;
    @JsonProperty("flag_respuesta")
    private String flagRespuesta;

    @JsonProperty("nis_rad")
    private String nisRad;
    @JsonProperty("sec_cta")
    private String secCta;
    @JsonProperty("usu_ant")
    private String usuAnt;

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
