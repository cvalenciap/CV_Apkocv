package pe.com.sedapal.ofivirtual.domain.entity;

import java.util.List;

/**
 * Created by jsaenz on 10,diciembre,2018
 */
public class LiquidacionEnchufate {
    private String codigoServicio;
    private String tipoDoc;
    private String numeroDoc;

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }
}
