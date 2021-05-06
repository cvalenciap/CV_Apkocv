package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jsaenz on 7/03/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentsMunicipalitiesEntity {
    @JsonProperty("cod_incidencia")
    private int codIncidencia;
    @JsonProperty("nom_incidencia")
    private String nomIncidencia;
    @JsonProperty("tipo_incidencia")
    private String tipoIncidencia;
    @JsonProperty("estado_incidencia")
    private String estadoIncidencia;
    @JsonProperty("fecha_inicio")
    private String fechaInicio;
    @JsonProperty("fecha_estimada_sol")
    private String fechaEstimadaSol;
    @JsonProperty("observacion")
    private String observacion;
    @JsonProperty("areas_afectadas")
    List<AffectAreaEntity> areasAfectadas;
    @JsonProperty("latitud")
    private double latitud;
    @JsonProperty("longitud")
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

    public List<AffectAreaEntity> getAreasAfectadas() {
        return areasAfectadas;
    }

    public void setAreasAfectadas(List<AffectAreaEntity> areasAfectadas) {
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
