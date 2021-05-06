package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 06,diciembre,2018
 */
public class RequestUpdateDataUserEntity {
    @JsonProperty("id_cliente")
    long idCliente;
    @JsonProperty("tipo_solicitante")
    int tipoSolicitante;
    @JsonProperty("tipo_doc")
    int tipoDocum;
    @JsonProperty("nro_doc")
    long nroDoc;
    @JsonProperty("apellido1")
    String apellido1;
    @JsonProperty("apellido2")
    String apellido2;
    @JsonProperty("nombres")
    String nombres;
    @JsonProperty("sexo")
    String genero;
    @JsonProperty("distrito")
    String distrito;
    @JsonProperty("direccion")
    String direccion;
    @JsonProperty("fecha_nac")
    String fechaNac;
    @JsonProperty("telefono1")
    long telefono1;
    @JsonProperty("telefono2")
    long telefono2;
    @JsonProperty("acepta_noti")
    String aceptaNotifica;

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public int getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(int tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }

    public int getTipoDocum() {
        return tipoDocum;
    }

    public void setTipoDocum(int tipoDocum) {
        this.tipoDocum = tipoDocum;
    }

    public long getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(long nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public long getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(long telefono1) {
        this.telefono1 = telefono1;
    }

    public long getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(long telefono2) {
        this.telefono2 = telefono2;
    }

    public String getAceptaNotifica() {
        return aceptaNotifica;
    }

    public void setAceptaNotifica(String aceptaNotifica) {
        this.aceptaNotifica = aceptaNotifica;
    }
}
