package pe.com.sedapal.ofivirtual.model;

import java.text.DecimalFormat;

/**
 * Created by jsaenz on 06,diciembre,2018
 */
public class NisDetailModel {

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

        Float litersOfPetrol=Float.parseFloat(totalDeuda);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);

        return  df.format(litersOfPetrol);
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
