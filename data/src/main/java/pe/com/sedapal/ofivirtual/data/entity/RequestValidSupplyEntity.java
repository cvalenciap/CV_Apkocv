package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestValidSupplyEntity {

    @JsonProperty("nis_rad")
    private int nisRad;






    public int getNisRad() {
        return nisRad;
    }

    public void setNisRad(int nisRad) {
        this.nisRad = nisRad;
    }
}
