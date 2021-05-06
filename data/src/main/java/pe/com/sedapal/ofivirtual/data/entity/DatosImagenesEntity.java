package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 13/03/2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosImagenesEntity {
    @JsonProperty("base_url")
    private String baseUrl;
    @JsonProperty("logo_sedapal_png")
    private String logoSedapalPng;
    @JsonProperty("logo_sedapal_svg")
    private String logoSedapalSvg;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLogoSedapalPng() {
        return logoSedapalPng;
    }

    public void setLogoSedapalPng(String logoSedapalPng) {
        this.logoSedapalPng = logoSedapalPng;
    }

    public String getLogoSedapalSvg() {
        return logoSedapalSvg;
    }

    public void setLogoSedapalSvg(String logoSedapalSvg) {
        this.logoSedapalSvg = logoSedapalSvg;
    }
}
