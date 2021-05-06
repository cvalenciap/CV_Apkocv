package pe.com.sedapal.ofivirtual.model;

/**
 * Created by jsaenz on 11,diciembre,2018
 */
public class SecretQuestionModel {
    private int idPreguntaSecreta = 0;
    private String preguntaSecreta ="";

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

    @Override
    public String toString() {
        return this.preguntaSecreta;
    }
}
