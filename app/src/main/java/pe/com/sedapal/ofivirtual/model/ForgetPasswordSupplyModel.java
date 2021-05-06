package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

/**
 * Created by jsaenz on 11,diciembre,2018
 */
public class ForgetPasswordSupplyModel implements Serializable {
    private String password="";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
