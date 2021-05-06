package pe.com.sedapal.ofivirtual.model;

/**
 * Created by jsaenz on 27/02/2019
 */
public class UpdateDataUserModel {
    long idCliente;
    int tipoSolicitante;
    int tipoDocum;
    long nroDoc;
    String apellido1;
    String apellido2;
    String nombres;
    String genero;
    String distrito;
    String direccion;
    String fechaNac;
    long telefono1;
    long telefono2;
    String aceptaNotifica;

    public UpdateDataUserModel(long idCliente, int tipoSolicitante, int tipoDocum, long nroDoc, String apellido1, String apellido2, String nombres, String genero, String distrito, String direccion, String fechaNac, long telefono1, long telefono2, String aceptaNotifica) {
        this.idCliente = idCliente;
        this.tipoSolicitante = tipoSolicitante;
        this.tipoDocum = tipoDocum;
        this.nroDoc = nroDoc;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombres = nombres;
        this.genero = genero;
        this.distrito = distrito;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.aceptaNotifica = aceptaNotifica;
    }

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
