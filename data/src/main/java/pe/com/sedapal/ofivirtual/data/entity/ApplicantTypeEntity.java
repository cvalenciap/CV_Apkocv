package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicantTypeEntity {

    @JsonProperty("idTipoSolicitante")
    private int idTipoSolicitante;
    @JsonProperty("tipoSolicitante")
    private String tipoSolicitante;
    @JsonProperty("descripcion")
    private String descripcion;

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
}
