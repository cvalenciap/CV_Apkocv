package pe.com.sedapal.ofivirtual.domain.entity;

/**
 * Created by jsaenz on 13/03/2019
 */
public class DatosVisa {

    private String visanetMerchant;
    private String visanetPassword;
    private String visanetUsername;
    private String visanetEndpointUrl;
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
