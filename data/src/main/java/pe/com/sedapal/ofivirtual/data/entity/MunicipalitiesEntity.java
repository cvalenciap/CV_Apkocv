package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipalitiesEntity {
    @JsonProperty("cod_municipio")
    private int codMunicipio;
    @JsonProperty("nom_municipio")
    private String nomMunicipio;
    @JsonProperty("nro_incidencias")
    private int nroIncidencias;
    @JsonProperty("mapa")
    private boolean mapa;
    @JsonProperty("incidencias")
    private List<IncidentsMunicipalitiesEntity> incidencias;

    public int getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(int codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public String getNomMunicipio() {
        return nomMunicipio;
    }

    public void setNomMunicipio(String nomMunicipio) {
        this.nomMunicipio = nomMunicipio;
    }

    public int getNroIncidencias() {
        return nroIncidencias;
    }

    public void setNroIncidencias(int nroIncidencias) {
        this.nroIncidencias = nroIncidencias;
    }

    public List<IncidentsMunicipalitiesEntity> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidentsMunicipalitiesEntity> incidencias) {
        this.incidencias = incidencias;
    }

    public boolean isMapa() {
        return mapa;
    }

    public void setMapa(boolean mapa) {
        this.mapa = mapa;
    }
}
