package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 23/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NisEntity {

    @JsonProperty("nis_rad")
    private String nisRad;

    public String getNisRad() {
        return nisRad;
    }

    public void setNisRad(String nisRad) {
        this.nisRad = nisRad;
    }
}
