package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestValidDocumentTypeEntity {

    @JsonProperty("nis_rad")
    private int nroSuministro;

    @JsonProperty("tipo_doc")
    private int tipoDoc;

    @JsonProperty("nro_doc")
    private String nroDoc;

    public int getNroSuministro() {
        return nroSuministro;
    }

    public void setNroSuministro(int nroSuministro) {
        this.nroSuministro = nroSuministro;
    }

    public int getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(int tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }
}
