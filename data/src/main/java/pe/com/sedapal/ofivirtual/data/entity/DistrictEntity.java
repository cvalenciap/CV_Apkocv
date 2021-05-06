package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistrictEntity {

    @JsonProperty("codDist")
    private int codDist;
    @JsonProperty("desDist")
    private String desDist;

    public int getCodDist() {
        return codDist;
    }

    public void setCodDist(int codDist) {
        this.codDist = codDist;
    }

    public String getDesDist() {
        return desDist;
    }

    public void setDesDist(String desDist) {
        this.desDist = desDist;
    }
}
