package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 29/07/2020
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidateVersionEntity {
    private String versionMovil;
    private String flagChannel;

    @JsonProperty("codigoError")
    private String codigoError;
    @JsonProperty("esObligatorio")
    private String esObligatorio;

    public String getVersionMovil() {
        return versionMovil;
    }

    public void setVersionMovil(String versionMovil) {
        this.versionMovil = versionMovil;
    }

    public String getFlagChannel() {
        return flagChannel;
    }

    public void setFlagChannel(String flagChannel) {
        this.flagChannel = flagChannel;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(String esObligatorio) {
        this.esObligatorio = esObligatorio;
    }
}
