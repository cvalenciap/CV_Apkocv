package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 13/03/2019
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosVisaEntity {

    @JsonProperty("VISANET_MERCHANT")
    private String visanetMerchant;
    @JsonProperty("VISANET_PASSWORD")
    private String visanetPassword;
    @JsonProperty("VISANET_USERNAME")
    private String visanetUsername;
    @JsonProperty("VISANET_END_POINT_URL")
    private String visanetEndpointUrl;
    @JsonProperty("comision")
    private double comision;

    public String getVisanetMerchant() {
        return visanetMerchant;
    }

    public void setVisanetMerchant(String visanetMerchant) {
        this.visanetMerchant = visanetMerchant;
    }

    public String getVisanetPassword() {
        return visanetPassword;
    }

    public void setVisanetPassword(String visanetPassword) {
        this.visanetPassword = visanetPassword;
    }

    public String getVisanetUsername() {
        return visanetUsername;
    }

    public void setVisanetUsername(String visanetUsername) {
        this.visanetUsername = visanetUsername;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public String getVisanetEndpointUrl() {
        return visanetEndpointUrl;
    }

    public void setVisanetEndpointUrl(String visanetEndpointUrl) {
        this.visanetEndpointUrl = visanetEndpointUrl;
    }

}
