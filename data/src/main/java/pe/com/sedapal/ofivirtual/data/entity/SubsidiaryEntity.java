package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubsidiaryEntity {
    @JsonProperty("nom_recaudador")
    private String nomRecaudador;
    @JsonProperty("dir_recaudador")
    private String dirRecaudador;
    @JsonProperty("distrito")
    private String distrito;
    @JsonProperty("tip_recaudador")
    private String tipRecaudador;
    @JsonProperty("longitud")
    private double longitud;
    @JsonProperty("latitud")
    private double latitud;

    public String getNomRecaudador() {
        return nomRecaudador;
    }

    public void setNomRecaudador(String nomRecaudador) {
        this.nomRecaudador = nomRecaudador;
    }

    public String getDirRecaudador() {
        return dirRecaudador;
    }

    public void setDirRecaudador(String dirRecaudador) {
        this.dirRecaudador = dirRecaudador;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTipRecaudador() {
        return tipRecaudador;
    }

    public void setTipRecaudador(String tipRecaudador) {
        this.tipRecaudador = tipRecaudador;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
