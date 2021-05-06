package pe.com.sedapal.ofivirtual.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jsaenz on 7/03/2019.
 */
public class IncidentsMunicipalitiesModel implements Serializable {
    private int codIncidencia;
    private String nomIncidencia;
    private String tipoIncidencia;
    private String estadoIncidencia;
    private String fechaInicio;
    private String fechaEstimadaSol;
    private String observacion;
    List<AffectAreaModel> areasAfectadas;
    private double latitud = 0;
    private double longitud = 0;

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

    public List<AffectAreaModel> getAreasAfectadas() {
        return areasAfectadas;
    }

    public void setAreasAfectadas(List<AffectAreaModel> areasAfectadas) {
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

    public LatLng getLatLng() {
        try {
            return new LatLng(latitud, longitud);
        } catch (Exception poException) {
            return null;
        }
    }
}
