package pe.com.sedapal.ofivirtual.model;

/**
 * Created by jsaenz on 06,diciembre,2018
 */
public class GenderPersonModel {
    private String idGender = "";
    private String descripcion = "";

    public String getIdGender() {
        return idGender;
    }

    public void setIdGender(String idGender) {
        this.idGender = idGender;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
}
