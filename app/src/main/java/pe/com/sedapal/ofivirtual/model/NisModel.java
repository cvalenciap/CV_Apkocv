package pe.com.sedapal.ofivirtual.model;

/**
 * Created by jsaenz on 06,diciembre,2018
 */
public class NisModel {
    private long nisRad;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    @Override
    public String toString() {
        return String.valueOf(this.nisRad);
    }
}
