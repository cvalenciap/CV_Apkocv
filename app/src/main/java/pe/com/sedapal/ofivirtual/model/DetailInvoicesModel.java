package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

/**
 * Created by jsaenz on 11/03/2019
 */
public class DetailInvoicesModel implements Serializable {
    private String codConcepto;
    private String desConcepto;
    private double montoConcepto;

    public String getCodConcepto() {
        return codConcepto;
    }

    public void setCodConcepto(String codConcepto) {
        this.codConcepto = codConcepto;
    }

    public String getDesConcepto() {
        return desConcepto;
    }

    public void setDesConcepto(String desConcepto) {
        this.desConcepto = desConcepto;
    }

    public double getMontoConcepto() {
        return montoConcepto;
    }

    public void setMontoConcepto(double monto_concepto) {
        this.montoConcepto = monto_concepto;
    }

}
