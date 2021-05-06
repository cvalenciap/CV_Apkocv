package pe.com.sedapal.ofivirtual.model;

public class ObtainDataUserLoginModel {
    private long nisRad;
    private long refCobro;
    private int tipoDocu;
    private long nroDoc;
    private String apellido1 = "";
    private String apellido2 = "";
    private String nombres = "";
    private String correo = "";
    private String telefono1 = "";
    private String telefono2 = "";
    private String clave = "";
    private String aceptaTerminos = "";
    private int aceptaNoti;
    private int tipoSoli;
    private String sexo = "";
    private String distrito = "";
    private String direccion = "";
    private String fecha_nac = "";
    private int idCliente;

    public long getNisRad() {
        return nisRad;
    }

    public void setNisRad(long nisRad) {
        this.nisRad = nisRad;
    }

    public long getRefCobro() {
        return refCobro;
    }

    public void setRefCobro(long refCobro) {
        this.refCobro = refCobro;
    }

    public int getTipoDocu() {
        return tipoDocu;
    }

    public void setTipoDocu(int tipoDocu) {
        this.tipoDocu = tipoDocu;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(String aceptaTerminos) {
        this.aceptaTerminos = aceptaTerminos;
    }

    public int getAceptaNoti() {
        return aceptaNoti;
    }

    public void setAceptaNoti(int aceptaNoti) {
        this.aceptaNoti = aceptaNoti;
    }

    public int getTipoSoli() {
        return tipoSoli;
    }

    public void setTipoSoli(int tipoSoli) {
        this.tipoSoli = tipoSoli;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
