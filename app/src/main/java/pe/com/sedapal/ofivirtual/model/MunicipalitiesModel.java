package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.ofivirtual.data.entity.IncidentsMunicipalitiesEntity;

/**
 * Created by jsaenz on 7/03/2019.
 */
public class MunicipalitiesModel implements Serializable {
    private int codMunicipio;
    private String nomMunicipio;
    private int nroIncidencias;
    private boolean mapa;
    private List<IncidentsMunicipalitiesModel> incidencias;

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

    public List<IncidentsMunicipalitiesModel> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidentsMunicipalitiesModel> incidencias) {
        this.incidencias = incidencias;
    }

    public boolean isMapa() {
        return mapa;
    }

    public void setMapa(boolean mapa) {
        this.mapa = mapa;
    }
}
