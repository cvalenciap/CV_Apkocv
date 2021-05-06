package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class Agency {

    private long codAgencia;
    private String nomEntidad;

    public long getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(long codAgencia) {
        this.codAgencia = codAgencia;
    }

    public String getNomEntidad() {
        return nomEntidad;
    }

    public void setNomEntidad(String nomEntidad) {
        this.nomEntidad = nomEntidad;
    }
}
