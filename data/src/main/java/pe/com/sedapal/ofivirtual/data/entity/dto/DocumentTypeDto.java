package pe.com.sedapal.ofivirtual.data.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DocumentTypeDto extends RealmObject {

    public static final String ID = "id";
    public static final String CODIGO = "codigo";
    public static final String NAME = "name";

    @PrimaryKey
    private int idTipoDocumento;
    private String tipoDocumento;
    private String descripcion;

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
}
