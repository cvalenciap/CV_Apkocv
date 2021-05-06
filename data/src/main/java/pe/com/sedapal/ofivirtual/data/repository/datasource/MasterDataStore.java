package pe.com.sedapal.ofivirtual.data.repository.datasource;

import org.w3c.dom.DocumentType;
import pe.com.sedapal.ofivirtual.data.entity.*;
import pe.com.sedapal.ofivirtual.data.entity.dto.DistrictDto;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

import java.util.List;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 23/01/2017.
 */

public interface MasterDataStore {


    /**
     * Login user in request  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link SyncEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 23/01/2017.
     */
    void sync(CallbackDataStore<SyncEntity> poCallback);


    void saveDistricts(List<DistrictEntity> paoDistrictEntities);

    void saveDocumentTypes(List<DocumentTypeEntity> paoDocumentTypeEntities);

    void saveApplicantType(List<ApplicantTypeEntity> paoApplicantTypeEntities);

    void saveSecretQuestions(List<SecretQuestionEntity> paoSecretQuestionEntityEntities);

    void saveCardType(List<CardTypeEntity> paoCardTypeEntities);

    void saveImagenesOnboarding(ImagenesEntity paoImagenesEntity);

    List<DistrictEntity> getListDistricts();

    List<DocumentTypeEntity> getListDocumentTypeEntity();

    List<ApplicantTypeEntity> getListApplicantTypeEntity();

    List<SecretQuestionEntity> getListSecretQuestionEntity();

    List<CardTypeEntity> getListCardTypeEntity();

    List<ImagenesOnboardingEntity> getListImagenesOnboardingEntity();

    void getDatosVisaEntity(CallbackDataStore<DatosVisaEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity);

    void getDatosImagenesEntity(CallbackDataStore<DatosImagenesEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity);

    //void saveKey(String psName, String psValue);
//
    //String getKey(String psName);
//
    //void deleteKey(String psName);
}
