package pe.com.sedapal.ofivirtual.domain.entity;

import java.util.List;

/**
 * Created by jsaenz on 7/03/2019.
 */
public class Municipalities {
    private int codMunicipio;
    private String nomMunicipio;
    private int nroIncidencias;
    private boolean mapa;
    private List<IncidentsMunicipalities> incidencias;

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

    public List<IncidentsMunicipalities> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<IncidentsMunicipalities> incidencias) {
        this.incidencias = incidencias;
    }

    public boolean isMapa() {
        return mapa;
    }

    public void setMapa(boolean mapa) {
        this.mapa = mapa;
    }
}
