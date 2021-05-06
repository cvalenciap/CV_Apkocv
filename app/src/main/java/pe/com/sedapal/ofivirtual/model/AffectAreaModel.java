package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

/**
 * Created by jsaenz on 7/03/2019.
 */

public class AffectAreaModel implements Serializable {
    private String descArea;
    private String tipArea;

    public String getDescArea() {
        return descArea;
    }

    public void setDescArea(String descArea) {
        this.descArea = descArea;
    }

    public String getTipArea() {
        return tipArea;
    }

    public void setTipArea(String tipArea) {
        this.tipArea = tipArea;
    }
}
