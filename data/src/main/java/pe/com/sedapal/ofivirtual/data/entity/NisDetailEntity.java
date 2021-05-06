package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 23/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NisDetailEntity {

    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("est_sum")
    private String estsum;
    @JsonProperty("direccion")
    private String direccion;
    @JsonProperty("total_deuda")
    private String totalDeuda;
    @JsonProperty("nom_cliente")
    private String nomCliente;
    @JsonProperty("auth_correo")
    private String auth_correo;

    public String getAuth_correo() {
        return auth_correo;
    }

    public void setAuth_correo(String auth_correo) {
        this.auth_correo = auth_correo;
    }

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public String getEstsum() {
        return estsum;
    }

    public void setEstsum(String estsum) {
        this.estsum = estsum;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTotalDeuda() {
        return totalDeuda;
    }

    public void setTotalDeuda(String totalDeuda) {
        this.totalDeuda = totalDeuda;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }
}
