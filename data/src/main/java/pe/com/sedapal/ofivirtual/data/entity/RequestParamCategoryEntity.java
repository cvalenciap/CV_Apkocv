package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 10,diciembre,2018
 */
public class RequestParamCategoryEntity {
    @JsonProperty("categoria")
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
