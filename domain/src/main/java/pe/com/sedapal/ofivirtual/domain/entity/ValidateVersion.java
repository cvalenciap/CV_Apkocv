package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by jsaenz on 29/07/2020
 */

public class ValidateVersion {
    private String versionMovil;
    private String flagChannel;

    private String codigoError;
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
