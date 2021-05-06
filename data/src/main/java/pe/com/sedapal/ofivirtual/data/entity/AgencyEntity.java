package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgencyEntity {
    @JsonProperty("cod_agencia")
    private long codAgencia;
    @JsonProperty("nom_entidad")
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
