package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestNisDetailEntity {

    @JsonProperty("nis_rad")
    private long nisRad;

    @JsonProperty("auth_correo")
    private String correo;


    @JsonProperty("flagChannel")
    private String flag;

    @JsonProperty("flag_multiple")
    private String flagMultiple;

    public String getFlagMultiple() {
        return flagMultiple;
    }

    public void setFlagMultiple(String flagMultiple) {
        this.flagMultiple = flagMultiple;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }
}
