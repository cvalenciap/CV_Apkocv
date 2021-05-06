package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

public class DistrictModel implements Serializable {
    private int codDist = 0;
    private String desDist = "";

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

    @Override
    public String toString() {
        return this.desDist;
    }
}
