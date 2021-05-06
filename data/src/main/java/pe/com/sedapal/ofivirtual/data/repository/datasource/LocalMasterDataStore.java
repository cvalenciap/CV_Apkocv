package pe.com.sedapal.ofivirtual.data.repository.datasource;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;
import pe.com.sedapal.ofivirtual.data.db.LocalBase;
import pe.com.sedapal.ofivirtual.data.entity.ApplicantTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.CardTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosVisaEntity;
import pe.com.sedapal.ofivirtual.data.entity.DistrictEntity;
import pe.com.sedapal.ofivirtual.data.entity.DocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.ImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.ImagenesOnboardingEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestParamCategoryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SecretQuestionEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.ApplicantTypeDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.CardTypeDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.DistrictDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.DocumentTypeDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.ImagenesOnboardingDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.SecretQuestionDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.ApplicantTypeDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.CardTypeDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.DistrictDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.DocumentTypeDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.ImagenesOnboardingDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.SecretQuestionDtoMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class LocalMasterDataStore extends LocalBase implements MasterDataStore {

    private final DocumentTypeDtoMapper goDocumentTypeDtoMapper;
    private final ApplicantTypeDtoMapper goApplicantTypeDtoMapper;
    private final DistrictDtoMapper goDistrictDtoMapper;
    private final SecretQuestionDtoMapper goSecretQuestionDtoMapper;
    private final CardTypeDtoMapper goCardTypeDtoMapper;
    private final ImagenesOnboardingDtoMapper goImagenesOnboardingDtoMapper;

    /**
     * Constructs a {@link LocalBase}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public LocalMasterDataStore() {
        super();

        this.goDocumentTypeDtoMapper = new DocumentTypeDtoMapper();
        this.goApplicantTypeDtoMapper = new ApplicantTypeDtoMapper();
        this.goDistrictDtoMapper = new DistrictDtoMapper();
        this.goSecretQuestionDtoMapper = new SecretQuestionDtoMapper();
        this.goCardTypeDtoMapper = new CardTypeDtoMapper();
        this.goImagenesOnboardingDtoMapper = new ImagenesOnboardingDtoMapper();
    }


    @Override
    public void sync(CallbackDataStore<SyncEntity> poCallback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getDatosVisaEntity(CallbackDataStore<DatosVisaEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getDatosImagenesEntity(CallbackDataStore<DatosImagenesEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveDistricts(List<DistrictEntity> paoDistrictEntities) {
        if (paoDistrictEntities != null && !paoDistrictEntities.isEmpty()) {
            List<DistrictDto> laoDistrictDtos = goDistrictDtoMapper.mapToDto(paoDistrictEntities);
            RealmResults<DistrictDto> loResult = getRealm().where(DistrictDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(laoDistrictDtos);
            getRealm().commitTransaction();
        }
    }

    @Override
    public void saveDocumentTypes(List<DocumentTypeEntity> paoDocumentTypeEntities) {
        if (paoDocumentTypeEntities != null && !paoDocumentTypeEntities.isEmpty()) {
            List<DocumentTypeDto> laoDocumentTypeDtos = goDocumentTypeDtoMapper.mapToDto(paoDocumentTypeEntities);
            RealmResults<DocumentTypeDto> loResult = getRealm().where(DocumentTypeDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(laoDocumentTypeDtos);
            getRealm().commitTransaction();
        }
    }

    @Override
    public void saveApplicantType(List<ApplicantTypeEntity> paoApplicantTypeEntities) {
        if (paoApplicantTypeEntities != null && !paoApplicantTypeEntities.isEmpty()) {
            List<ApplicantTypeDto> laoApplicantTypeDtos = goApplicantTypeDtoMapper.mapToDto(paoApplicantTypeEntities);
            RealmResults<ApplicantTypeDto> loResult = getRealm().where(ApplicantTypeDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(laoApplicantTypeDtos);
            getRealm().commitTransaction();
        }
    }

    @Override
    public void saveSecretQuestions(List<SecretQuestionEntity> paoSecretQuestionEntityEntities) {
        if (paoSecretQuestionEntityEntities != null && !paoSecretQuestionEntityEntities.isEmpty()) {
            List<SecretQuestionDto> loSecretQuestionDto = goSecretQuestionDtoMapper.mapToDto(paoSecretQuestionEntityEntities);
            RealmResults<SecretQuestionDto> loResult = getRealm().where(SecretQuestionDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(loSecretQuestionDto);
            getRealm().commitTransaction();
        }
    }

    @Override
    public void saveCardType(List<CardTypeEntity> paoCardTypeEntities) {
        if (paoCardTypeEntities != null && !paoCardTypeEntities.isEmpty()) {
            List<CardTypeDto> loCardTypeDto = goCardTypeDtoMapper.mapToDto(paoCardTypeEntities);
            RealmResults<CardTypeDto> loResult = getRealm().where(CardTypeDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(loCardTypeDto);
            getRealm().commitTransaction();
        }
    }

    @Override
    public void saveImagenesOnboarding(ImagenesEntity paoImagenesEntity) {
        if (paoImagenesEntity != null) {
            List<ImagenesOnboardingEntity> lstImagenesOnb = new ArrayList<>();
            ImagenesOnboardingEntity imagenesOnboardingEntity = new ImagenesOnboardingEntity();

            imagenesOnboardingEntity = new ImagenesOnboardingEntity();
            imagenesOnboardingEntity.setId(0);
            imagenesOnboardingEntity.setIconoOnboarding(paoImagenesEntity.getBase_url() + paoImagenesEntity.getCarpeta_imagenes() + paoImagenesEntity.getcarusel_1_png());
            imagenesOnboardingEntity.setDescripcionOnboarding(paoImagenesEntity.getODESC1());
            imagenesOnboardingEntity.setFondoOnboarding(paoImagenesEntity.getBase_url()  + paoImagenesEntity.getCarpeta_imagenes() +  paoImagenesEntity.getOnboarding_1());
            lstImagenesOnb.add(imagenesOnboardingEntity);

            imagenesOnboardingEntity = new ImagenesOnboardingEntity();
            imagenesOnboardingEntity.setId(1);
            imagenesOnboardingEntity.setIconoOnboarding(paoImagenesEntity.getBase_url() + paoImagenesEntity.getCarpeta_imagenes() + paoImagenesEntity.getcarusel_2_png());
            imagenesOnboardingEntity.setDescripcionOnboarding(paoImagenesEntity.getODESC2());
            imagenesOnboardingEntity.setFondoOnboarding(paoImagenesEntity.getBase_url()  + paoImagenesEntity.getCarpeta_imagenes() +  paoImagenesEntity.getOnboarding_2());
            lstImagenesOnb.add(imagenesOnboardingEntity);

            imagenesOnboardingEntity = new ImagenesOnboardingEntity();
            imagenesOnboardingEntity.setId(2);
            imagenesOnboardingEntity.setIconoOnboarding(paoImagenesEntity.getBase_url() + paoImagenesEntity.getCarpeta_imagenes() + paoImagenesEntity.getcarusel_3_png());
            imagenesOnboardingEntity.setDescripcionOnboarding(paoImagenesEntity.getODESC3());
            imagenesOnboardingEntity.setFondoOnboarding(paoImagenesEntity.getBase_url()  + paoImagenesEntity.getCarpeta_imagenes() +  paoImagenesEntity.getOnboarding_3());
            lstImagenesOnb.add(imagenesOnboardingEntity);

            imagenesOnboardingEntity = new ImagenesOnboardingEntity();
            imagenesOnboardingEntity.setId(3);
            imagenesOnboardingEntity.setIconoOnboarding(paoImagenesEntity.getBase_url() + paoImagenesEntity.getCarpeta_imagenes() + paoImagenesEntity.getcarusel_4_png());
            imagenesOnboardingEntity.setDescripcionOnboarding(paoImagenesEntity.getODESC4());
            imagenesOnboardingEntity.setFondoOnboarding(paoImagenesEntity.getBase_url() +  paoImagenesEntity.getCarpeta_imagenes() +  paoImagenesEntity.getOnboarding_4());
            lstImagenesOnb.add(imagenesOnboardingEntity);



            List<ImagenesOnboardingDto> loImagenesOnboardingDto = goImagenesOnboardingDtoMapper.mapToDto(lstImagenesOnb);
            RealmResults<ImagenesOnboardingDto> loResult = getRealm().where(ImagenesOnboardingDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(loImagenesOnboardingDto);
            getRealm().commitTransaction();
        }
    }

    @Override
    public List<ImagenesOnboardingEntity> getListImagenesOnboardingEntity() {
        List<ImagenesOnboardingEntity> lsListImagenesOnboardingEntity;
        RealmResults<ImagenesOnboardingDto> loResult = getRealm().where(ImagenesOnboardingDto.class).findAll();
        if(loResult != null){
            lsListImagenesOnboardingEntity = goImagenesOnboardingDtoMapper.mapFromDto(loResult);
        }else {
            lsListImagenesOnboardingEntity = new ArrayList<>();
        }

        return lsListImagenesOnboardingEntity;
    }


    @Override
    public List<DistrictEntity> getListDistricts() {
        List<DistrictEntity> lsListDistrictEntity;
        RealmResults<DistrictDto> loResult = getRealm().where(DistrictDto.class).findAll();
        if(loResult != null){
            lsListDistrictEntity = goDistrictDtoMapper.mapFromDto(loResult);
        }else {
            lsListDistrictEntity = new ArrayList<>();
        }

        return lsListDistrictEntity;
    }

    @Override
    public List<DocumentTypeEntity> getListDocumentTypeEntity() {
        List<DocumentTypeEntity> lsListDocumentTypeEntity;
        RealmResults<DocumentTypeDto> loResult = getRealm().where(DocumentTypeDto.class).findAll();
        if(loResult != null){
            lsListDocumentTypeEntity = goDocumentTypeDtoMapper.mapFromDto(loResult);
        }else {
            lsListDocumentTypeEntity = new ArrayList<>();
        }

        return lsListDocumentTypeEntity;
    }

    @Override
    public List<ApplicantTypeEntity> getListApplicantTypeEntity() {
        List<ApplicantTypeEntity> lsListApplicantTypeEntity;
        RealmResults<ApplicantTypeDto> loResult = getRealm().where(ApplicantTypeDto.class).findAll();
        if(loResult != null){
            lsListApplicantTypeEntity = goApplicantTypeDtoMapper.mapFromDto(loResult);
        }else {
            lsListApplicantTypeEntity = new ArrayList<>();
        }

        return lsListApplicantTypeEntity;
    }

    @Override
    public List<SecretQuestionEntity> getListSecretQuestionEntity() {
        List<SecretQuestionEntity> lsSecretQuestionEntity;
        RealmResults<SecretQuestionDto> loResult = getRealm().where(SecretQuestionDto.class).findAll();
        if(loResult != null){
            lsSecretQuestionEntity = goSecretQuestionDtoMapper.mapFromDto(loResult);
        }else {
            lsSecretQuestionEntity = new ArrayList<>();
        }

        return lsSecretQuestionEntity;
    }

    @Override
    public List<CardTypeEntity> getListCardTypeEntity() {
        List<CardTypeEntity> lsCardTypeEntity;
        RealmResults<CardTypeDto> loResult = getRealm().where(CardTypeDto.class).findAll();
        if(loResult != null){
            lsCardTypeEntity = goCardTypeDtoMapper.mapFromDto(loResult);
        }else {
            lsCardTypeEntity = new ArrayList<>();
        }

        return lsCardTypeEntity;
    }

    //@Override
    //public void saveKey(String psName, String psValue) {
//
    //    if (getKey(psName) != null) {
    //        deleteKey(psName);
    //    }
    //    ConfigurationDto loConfigurationDto = new ConfigurationDto();
    //    loConfigurationDto.setName(psName);
    //    loConfigurationDto.setValue(psValue);
//
    //    getRealm().beginTransaction();
    //    getRealm().insert(loConfigurationDto);
    //    getRealm().commitTransaction();
    //}
//
    //@Override
    //public String getKey(String psName) {
    //    String lsValue = null;
//
    //    ConfigurationDto loConfigurationDto = getRealm().where(ConfigurationDto.class).contains(ConfigurationDto.NAME, psName).findFirst();
    //    if (loConfigurationDto != null) {
    //        lsValue = loConfigurationDto.getValue();
    //    }
    //    return lsValue;
    //}
//
    //@Override
    //public void deleteKey(String psName) {
    //    RealmResults<ConfigurationDto> loResult = getRealm().where(ConfigurationDto.class).contains(ConfigurationDto.NAME, psName).findAll();
    //    if (loResult != null) {
    //        loResult.deleteAllFromRealm();
    //    }
    //}
}
