package pe.com.sedapal.ofivirtual.domain.entity;

import java.util.List;

/**
 * Created by jsaenz on 7/03/2019.
 */
public class IncidentsMunicipalities {
    private int codIncidencia;
    private String nomIncidencia;
    private String tipoIncidencia;
    private String estadoIncidencia;
    private String fechaInicio;
    private String fechaEstimadaSol;
    private String observacion;
    List<AffectArea> areasAfectadas;
    private double latitud;
    private double longitud;

    public int getCodIncidencia() {
        return codIncidencia;
    }

    public void setCodIncidencia(int codIncidencia) {
        this.codIncidencia = codIncidencia;
    }

    public String getNomIncidencia() {
        return nomIncidencia;
    }

    public void setNomIncidencia(String nomIncidencia) {
        this.nomIncidencia = nomIncidencia;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public String getEstadoIncidencia() {
        return estadoIncidencia;
    }

    public void setEstadoIncidencia(String estadoIncidencia) {
        this.estadoIncidencia = estadoIncidencia;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaEstimadaSol() {
        return fechaEstimadaSol;
    }

    public void setFechaEstimadaSol(String fechaEstimadaSol) {
        this.fechaEstimadaSol = fechaEstimadaSol;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<AffectArea> getAreasAfectadas() {
        return areasAfectadas;
    }

    public void setAreasAfectadas(List<AffectArea> areasAfectadas) {
        this.areasAfectadas = areasAfectadas;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
