package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;

/**
 * Created by jsaenz on 10,diciembre,2018
 */
public class LiquidacionModel implements Serializable {
    private String numeroLiquidacion;
    private double importeTotal;
    private String listaRegistros;

    public String getNumeroLiquidacion() {
        return numeroLiquidacion;
    }

    public void setNumeroLiquidacion(String numeroLiquidacion) {
        this.numeroLiquidacion = numeroLiquidacion;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(String listaRegistros) {
        this.listaRegistros = listaRegistros;
    }
}
