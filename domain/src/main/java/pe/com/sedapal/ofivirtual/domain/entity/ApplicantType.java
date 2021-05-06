package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by jsaenz on 05,diciembre,2018
 */
public class ApplicantType {
    private int idTipoSolicitante;
    private String tipoSolicitante;
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
