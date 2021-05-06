package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ApplicantTypeDto extends RealmObject {

    public static final String ID = "id";
    public static final String CODIGO = "codigo";
    public static final String NAME = "name";

    @PrimaryKey
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
