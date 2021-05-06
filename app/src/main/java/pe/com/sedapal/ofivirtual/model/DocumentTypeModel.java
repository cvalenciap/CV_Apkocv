package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

public class DocumentTypeModel implements Serializable {
    private int idTipoDocumento = 0;
    private String tipoDocumento = "";
    private String descripcion = "";

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.tipoDocumento;
    }
}
