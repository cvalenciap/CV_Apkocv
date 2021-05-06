package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

public class ApplicantTypeModel implements Serializable {
    private int idTipoSolicitante = 0;
    private String tipoSolicitante = "";
    private String descripcion = "";

    public int getIdTipoSolicitante() {
        return idTipoSolicitante;
    }

    public void setIdTipoSolicitante(int idTipoSolicitante) {
        this.idTipoSolicitante = idTipoSolicitante;
    }

    public String getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(String tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.tipoSolicitante;
    }
}
