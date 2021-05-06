package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailInvoicesEntity {
    @JsonProperty("cod_concepto")
    private String codConcepto;
    @JsonProperty("desc_concepto")
    private String desConcepto;
    @JsonProperty("monto_concepto")
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

    public void setMontoConcepto(double montoConcepto) {
        this.montoConcepto = montoConcepto;
    }
}
