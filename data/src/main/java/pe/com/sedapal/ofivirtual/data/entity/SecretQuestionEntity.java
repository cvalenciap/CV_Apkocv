package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecretQuestionEntity {
    @JsonProperty("idPreguntaSecreta")
    private String idPreguntaSecreta;
    @JsonProperty("preguntaSecreta")
    private String preguntaSecreta;

    public String getIdPreguntaSecreta() {
        return idPreguntaSecreta;
    }

    public void setIdPreguntaSecreta(String idPreguntaSecreta) {
        this.idPreguntaSecreta = idPreguntaSecreta;
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }
}
