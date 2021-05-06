package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestRegUserEntity {

    @JsonProperty("nis_rad")
    private long nisRad;
    @JsonProperty("ref_cobro")
    private long referenciaCobro;
    @JsonProperty("tipo_doc")
    private int idTipoDoc;
    @JsonProperty("nro_doc")
    private String numDoc;
    @JsonProperty("apellido1")
    private String apellido1;
    @JsonProperty("apellido2")
    private String apellido2;
    @JsonProperty("nombres")
    private String nombres;
    @JsonProperty("correo")
    private String correo;
    @JsonProperty("telefono1")
    private long telefono1;
    @JsonProperty("acepta_terminos")
    private int aceptaTerminos;
    @JsonProperty("acepta_noti")
    private int aceptaNotificaciones;
    @JsonProperty("clave")
    private String clave;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public long getReferenciaCobro() {
        return referenciaCobro;
    }

    public void setReferenciaCobro(long referenciaCobro) {
        this.referenciaCobro = referenciaCobro;
    }

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(long telefono1) {
        this.telefono1 = telefono1;
    }

    public int getAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(int aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    public int getAceptaNotificaciones() {
        return aceptaNotificaciones;
    }

    public void setAceptaNotificaciones(int aceptaNotificaciones) {
        this.aceptaNotificaciones = aceptaNotificaciones;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
