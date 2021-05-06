package pe.com.sedapal.ofivirtual.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by jsaenz on 7/03/2019.
 */
public class SubsidiaryModel implements Serializable {
    private String nomRecaudador;
    private String dirRecaudador;
    private String distrito;
    private String tipRecaudador;
    private double longitud;
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

    public LatLng getLatLng() {
        try {
            return new LatLng(latitud, longitud);
        } catch (Exception poException) {
            return null;
        }
    }
}
