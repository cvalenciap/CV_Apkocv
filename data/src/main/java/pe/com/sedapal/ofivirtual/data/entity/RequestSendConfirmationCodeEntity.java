package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 29/07/20
 */
public class RequestSendConfirmationCodeEntity {
    @JsonProperty("correo")
    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
