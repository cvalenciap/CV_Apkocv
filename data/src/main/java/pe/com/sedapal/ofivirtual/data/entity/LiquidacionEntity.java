package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 10/08/2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiquidacionEntity {

    @JsonProperty("numeroLiquidacion")
    private String numeroLiquidacion;
    @JsonProperty("importeTotal")
    private double importeTotal;
    @JsonProperty("listaRegistros")
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
