package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DistrictDto extends RealmObject {

    public static final String CODIGO = "codDist";
    public static final String DESCRIPCION = "desDist";

    @PrimaryKey
    private int codDist;
    private String desDist;

    public int getCodDist() {
        return codDist;
    }

    public void setCodDist(int codDist) {
        this.codDist = codDist;
    }

    public String getDesDist() {
        return desDist;
    }

    public void setDesDist(String desDist) {
        this.desDist = desDist;
    }
}
