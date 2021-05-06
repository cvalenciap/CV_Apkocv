package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by jsaenz on 11,diciembre,2018
 */
public class ReqForgetPasswordSupply {
    private int nis;
    private int idTipoUsuario;
    private int idPregunta;
    private String respuesta;

    public int getNis() {
        return nis;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
