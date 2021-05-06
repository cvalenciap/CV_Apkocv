package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 23/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {

    @JsonProperty("idUsuario")
    private String userId;
    @JsonProperty("clave")
    private String password;
    @JsonProperty("claveActual")
    private String currentPassword;
    @JsonProperty("claveNueva")
    private String newPassword;
    @JsonProperty("email")
    private String email;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("fechaAlta")
    private String entryDate;
    @JsonProperty("nombre")
    private String fullName;
    @JsonProperty("nroDocumento")
    private String documentNumber;
    @JsonProperty("tipoDocumento")
    private String documentType;
    @JsonProperty("token")
    private String token;
    @JsonProperty("codActivacion")
    private String activationCode;
    @JsonProperty("nroCelular")
    private String phoneNumber;
    @JsonProperty("flgPublicidad")
    private String adverts;
    @JsonIgnore
    private boolean saveEmail;

    public String getAdverts() {
        return adverts;
    }

    public void setAdverts(String adverts) {
        this.adverts = adverts;
    }

    public String getDomain() {
        return this.email.split("@")[1];
    }

    public String getAccount() {
        return this.email.split("@")[0];
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSaveEmail() {
        return saveEmail;
    }

    public void setSaveEmail(boolean saveEmail) {
        this.saveEmail = saveEmail;
    }


    @Override
    public String toString() {
        return fullName;
    }
}
