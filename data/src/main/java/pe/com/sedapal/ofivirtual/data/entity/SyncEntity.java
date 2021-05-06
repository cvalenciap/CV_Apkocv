package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncEntity {

    @JsonProperty("listaTipoDocumentos")
    private List<DocumentTypeEntity> documentTypes;
    @JsonProperty("listaTipoSolicitantes")
    private List<ApplicantTypeEntity> applicantTypes;
    @JsonProperty("listaDistritos")
    private List<DistrictEntity> districts;
    @JsonProperty("listaPreguntaSecreta")
    private List<SecretQuestionEntity> listSecretQuestion;
    @JsonProperty("listaTipoTarjetas")
    private List<CardTypeEntity> listCardType;
    @JsonProperty("lImagenes")
    private ImagenesEntity lImagenes;

    public List<DocumentTypeEntity> getDocumentTypes() {
        return documentTypes;
    }

    public void setDocumentTypes(List<DocumentTypeEntity> documentTypes) {
        this.documentTypes = documentTypes;
    }

    public List<ApplicantTypeEntity> getApplicantTypes() {
        return applicantTypes;
    }

    public void setApplicantTypes(List<ApplicantTypeEntity> applicantTypes) {
        this.applicantTypes = applicantTypes;
    }

    public List<DistrictEntity> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictEntity> districts) {
        this.districts = districts;
    }

    public List<SecretQuestionEntity> getListSecretQuestion() {
        return listSecretQuestion;
    }

    public void setListSecretQuestion(List<SecretQuestionEntity> listSecretQuestion) {
        this.listSecretQuestion = listSecretQuestion;
    }

    public List<CardTypeEntity> getListCardType() {
        return listCardType;
    }

    public void setListCardType(List<CardTypeEntity> listCardType) {
        this.listCardType = listCardType;
    }

    public ImagenesEntity getlImagenes() {
        return lImagenes;
    }

    public void setlImagenes(ImagenesEntity lImagenes) {
        this.lImagenes = lImagenes;
    }

    //public String getSyncDate() {
    //    return syncDate;
    //}
//
    //public void setSyncDate(String syncDate) {
    //    this.syncDate = syncDate;
    //}
}
