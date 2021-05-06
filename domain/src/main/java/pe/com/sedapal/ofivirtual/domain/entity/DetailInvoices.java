package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class DetailInvoices {
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
