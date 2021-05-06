package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 27/07/20
 */
public class RequestValidateConfirmationCodeEntity {
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("codeVerify")
    private String codeVerify;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodeVerify() {
        return codeVerify;
    }

    public void setCodeVerify(String codeVerify) {
        this.codeVerify = codeVerify;
    }
}
