package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 10,diciembre,2018
 */
public class RequestSubsidiaryEntity {
    @JsonProperty("cod_agencia")
    private long codAgencia;

    public long getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(long codAgencia) {
        this.codAgencia = codAgencia;
    }
}
