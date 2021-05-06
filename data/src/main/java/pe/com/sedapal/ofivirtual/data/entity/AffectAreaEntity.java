package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AffectAreaEntity {
    @JsonProperty("desc_area")
    private String descArea;
    @JsonProperty("tipo_area")
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
