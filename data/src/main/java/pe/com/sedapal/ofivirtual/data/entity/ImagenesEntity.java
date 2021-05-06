package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagenesEntity {
    @JsonProperty("base_url")
    private String base_url;
    @JsonProperty("carpeta_imagenes")
    private String carpeta_imagenes;
    @JsonProperty("logo_sedapal_svg")
    private String logo_sedapal_svg;
    @JsonProperty("carusel_1_png")
    private String carusel_1_png;
    @JsonProperty("carusel_2_png")
    private String carusel_2_png;
    @JsonProperty("carusel_3_png")
    private String carusel_3_png;
    @JsonProperty("carusel_4_png")
    private String carusel_4_png;
    @JsonProperty("onboarding_1")
    private String onboarding_1;
    @JsonProperty("onboarding_2")
    private String onboarding_2;
    @JsonProperty("onboarding_3")
    private String onboarding_3;
    @JsonProperty("onboarding_4")
    private String onboarding_4;
    @JsonProperty("ODESC1")
    private String ODESC1;
    @JsonProperty("ODESC2")
    private String ODESC2;
    @JsonProperty("ODESC3")
    private String ODESC3;
    @JsonProperty("ODESC4")
    private String ODESC4;

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getLogo_sedapal_svg() {
        return logo_sedapal_svg;
    }

    public void setLogo_sedapal_svg(String logo_sedapal_svg) {
        this.logo_sedapal_svg = logo_sedapal_svg;
    }

    public String getcarusel_1_png() {
        return carusel_1_png;
    }

    public void setcarusel_1_png(String carusel_1_png) {
        this.carusel_1_png = carusel_1_png;
    }

    public String getcarusel_2_png() {
        return carusel_2_png;
    }

    public void setcarusel_2_png(String carusel_2_png) {
        this.carusel_2_png = carusel_2_png;
    }

    public String getcarusel_3_png() {
        return carusel_3_png;
    }

    public void setcarusel_3_png(String carusel_3_png) {
        this.carusel_3_png = carusel_3_png;
    }

    public String getOnboarding_1() {
        return onboarding_1;
    }

    public void setOnboarding_1(String onboarding_1) {
        this.onboarding_1 = onboarding_1;
    }

    public String getOnboarding_2() {
        return onboarding_2;
    }

    public void setOnboarding_2(String onboarding_2) {
        this.onboarding_2 = onboarding_2;
    }

    public String getOnboarding_3() {
        return onboarding_3;
    }

    public void setOnboarding_3(String onboarding_3) {
        this.onboarding_3 = onboarding_3;
    }

    public String getOnboarding_4() {
        return onboarding_4;
    }

    public void setOnboarding_4(String onboarding_4) {
        this.onboarding_4 = onboarding_4;
    }

    public String getcarusel_4_png() {
        return carusel_4_png;
    }

    public void setcarusel_4_png(String carusel_4_png) {
        this.carusel_4_png = carusel_4_png;
    }

    public String getODESC1() {
        return ODESC1;
    }

    public void setODESC1(String ODESC1) {
        this.ODESC1 = ODESC1;
    }

    public String getCarpeta_imagenes() {
        return carpeta_imagenes;
    }

    public void setCarpeta_imagenes(String carpeta_imagenes) {
        this.carpeta_imagenes = carpeta_imagenes;
    }

    public String getODESC2() {
        return ODESC2;
    }

    public void setODESC2(String ODESC2) {
        this.ODESC2 = ODESC2;
    }

    public String getODESC3() {
        return ODESC3;
    }

    public void setODESC3(String ODESC3) {
        this.ODESC3 = ODESC3;
    }

    public String getODESC4() {
        return ODESC4;
    }

    public void setODESC4(String ODESC4) {
        this.ODESC4 = ODESC4;
    }
}
