package pe.com.sedapal.ofivirtual.data.entity;

public class ObtenerDatosPagoEntity {
    private String trxId;
    private String errorCode;
    private String errorMsg;
    private String fecha;
    private String hora;
    private String descripcion;
    private String numOperacion;
    private String numTarjeta;
    private int numLiquidacion;
    private int nisRad;
    private String estado;
    private int monto;
    private String correo;
    private String fechaEmision;
    private String fechaVencimientio;
    private int simboloVar;
    private String fechaHora;
    private String nombreCompleto;
    private String canal;

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumOperacion() {
        return numOperacion;
    }

    public void setNumOperacion(String numOperacion) {
        this.numOperacion = numOperacion;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public int getNumLiquidacion() {
        return numLiquidacion;
    }

    public void setNumLiquidacion(int numLiquidacion) {
        this.numLiquidacion = numLiquidacion;
    }

    public int getNisRad() {
        return nisRad;
    }

    public void setNisRad(int nisRad) {
        this.nisRad = nisRad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaVencimientio() {
        return fechaVencimientio;
    }

    public void setFechaVencimientio(String fechaVencimientio) {
        this.fechaVencimientio = fechaVencimientio;
    }

    public int getSimboloVar() {
        return simboloVar;
    }

    public void setSimboloVar(int simboloVar) {
        this.simboloVar = simboloVar;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
}
