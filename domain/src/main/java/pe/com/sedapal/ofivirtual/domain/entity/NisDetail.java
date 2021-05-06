package pe.com.sedapal.ofivirtual.domain.entity;

public class NisDetail {

    private long nisRad;
    private String estsum;
    private String direccion;
    private String totalDeuda;
    private String nomCliente;

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
