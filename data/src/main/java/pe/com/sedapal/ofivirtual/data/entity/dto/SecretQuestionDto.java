package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SecretQuestionDto extends RealmObject {

    @PrimaryKey
    private int idPreguntaSecreta;
    private String preguntaSecreta;

    public int getIdPreguntaSecreta() {
        return idPreguntaSecreta;
    }

    public void setIdPreguntaSecreta(int idPreguntaSecreta) {
        this.idPreguntaSecreta = idPreguntaSecreta;
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }
}
