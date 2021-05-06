package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 11,diciembre,2018
 */
public class ForgetPasswordSupplyEntity {
    @JsonProperty("CLAVE")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
