package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CardTypeDto extends RealmObject {

    public static final String ID = "id";
    public static final String CODIGO = "codigo";
    public static final String NAME = "name";

    @PrimaryKey
    private int id;
    private int idTarjeta;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
