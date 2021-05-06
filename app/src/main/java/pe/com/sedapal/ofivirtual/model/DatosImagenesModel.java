package pe.com.sedapal.ofivirtual.model;

/**
 * Created by jsaenz on 13/03/2019
 */
public class DatosImagenesModel {

    private String baseUrl;
    private String logoSedapalPng;
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
