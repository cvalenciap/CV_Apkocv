package pe.com.sedapal.ofivirtual.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.DatosImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosVisaEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestParamCategoryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ApplicantTypeEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.CardTypeEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.DatosImagenesEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.DatosVisaEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.DistrictEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.DocumentTypeEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ImagenesOnboardingEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.SecretQuestionEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.MasterDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.MasterDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.ApplicantType;
import pe.com.sedapal.ofivirtual.domain.entity.CardType;
import pe.com.sedapal.ofivirtual.domain.entity.DatosImagenes;
import pe.com.sedapal.ofivirtual.domain.entity.DatosVisa;
import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.domain.entity.ImagenesOnboarding;
import pe.com.sedapal.ofivirtual.domain.entity.SecretQuestion;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

/**
 * Created by jsaenz on 5/01/2017.
 * </p>
 * {@link MasterRepository} for retrieving Master data.
 */
@Singleton
public class MasterDataRepository implements MasterRepository {
    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final MasterDataStoreFactory goMasterDataStoreFactory;
    private final DocumentTypeEntityMapper goDocumentTypeEntityMapper;
    private final DistrictEntityMapper goDistrictEntityMapper;
    private final ApplicantTypeEntityMapper goApplicantTypeEntityMapper;
    private final SecretQuestionEntityMapper goSecretQuestionEntityMapper;
    private final DatosVisaEntityMapper goDatosVisaEntityMapper;
    private final DatosImagenesEntityMapper goDatosImagenesEntityMapper;
    private final ImagenesOnboardingEntityMapper goImagenesOnboardingEntityMapper;
    private final CardTypeEntityMapper goCardTypeEntityMapper;

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    /**
     * Constructs a {@link MasterDataRepository}.
     *
     * @param poMasterDataStoreFactory {@link MasterDataStoreFactory} A factory to construct different data source implementations.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    MasterDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,
                         MasterDataStoreFactory poMasterDataStoreFactory,
                         DocumentTypeEntityMapper poDocumentTypeEntityMapper,
                         DistrictEntityMapper poDistrictEntityMapper,
                         ApplicantTypeEntityMapper poApplicantTypeEntityMapper,
                         SecretQuestionEntityMapper poSecretQuestionEntityMapper,
                         DatosVisaEntityMapper poDatosVisaEntityMapper,
                         DatosImagenesEntityMapper poDatosImagenesEntityMapper,
                         CardTypeEntityMapper poCardTypeEntityMapper,
                         ImagenesOnboardingEntityMapper poImagenesOnboardingEntityMapper) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goMasterDataStoreFactory = poMasterDataStoreFactory;
        this.goDocumentTypeEntityMapper = poDocumentTypeEntityMapper;
        this.goDistrictEntityMapper = poDistrictEntityMapper;
        this.goApplicantTypeEntityMapper = poApplicantTypeEntityMapper;
        this.goSecretQuestionEntityMapper = poSecretQuestionEntityMapper;
        this.goDatosVisaEntityMapper = poDatosVisaEntityMapper;
        this.goDatosImagenesEntityMapper = poDatosImagenesEntityMapper;
        this.goCardTypeEntityMapper = poCardTypeEntityMapper;
        this.goImagenesOnboardingEntityMapper = poImagenesOnboardingEntityMapper;
    }


    @Override
    public Observable<Void> sync() {

        return Observable.create(loEmitter -> {
            final MasterDataStore loMasterCloudDataStore = goMasterDataStoreFactory.createCloud();

            loMasterCloudDataStore.sync(new CallbackDataStore<SyncEntity>() {
                @Override
                public void onSuccess(SyncEntity poData) {
                    final MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
                    loMasterLocalDataStore.saveApplicantType(poData.getApplicantTypes());
                    loMasterLocalDataStore.saveDocumentTypes(poData.getDocumentTypes());
                    loMasterLocalDataStore.saveDistricts(poData.getDistricts());
                    loMasterLocalDataStore.saveSecretQuestions(poData.getListSecretQuestion());
                    loMasterLocalDataStore.saveCardType(poData.getListCardType());
                    loMasterLocalDataStore.saveImagenesOnboarding(poData.getlImagenes());

                    if (!loEmitter.isDisposed()) {
                        //loEmitter.onNext(null);
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            });

        });
    }

    @Override
    public Observable<List<DocumentType>> getListDocumentType() {
        return Observable.create(loEmitter -> {
            List<DocumentType> loListDocumentType;
            MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
            loListDocumentType = goDocumentTypeEntityMapper.mapToEntity(loMasterLocalDataStore.getListDocumentTypeEntity());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loListDocumentType);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<District>> getListDistrict() {
        return Observable.create(loEmitter-> {
            List<District> loListDistrict;
            MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
            loListDistrict = goDistrictEntityMapper.mapToEntity(loMasterLocalDataStore.getListDistricts());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loListDistrict);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<ApplicantType>> getListApplicantType() {
        return Observable.create(loEmitter -> {
            List<ApplicantType> loListApplicantType;
            MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
            loListApplicantType = goApplicantTypeEntityMapper.mapToEntity(loMasterLocalDataStore.getListApplicantTypeEntity());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loListApplicantType);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<SecretQuestion>> getListSecretQuestions() {
        return Observable.create(loEmitter -> {
            List<SecretQuestion> loListApplicantType;
            MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
            loListApplicantType = goSecretQuestionEntityMapper.mapToEntity(loMasterLocalDataStore.getListSecretQuestionEntity());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loListApplicantType);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<CardType>> getListCardType() {
        return Observable.create(loEmitter -> {
            List<CardType> loListApplicantType;
            MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
            loListApplicantType = goCardTypeEntityMapper.mapToEntity(loMasterLocalDataStore.getListCardTypeEntity());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loListApplicantType);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<List<ImagenesOnboarding>> getListImagenesOnboardingEntity() {
        return Observable.create(loEmitter -> {
            List<ImagenesOnboarding> loListApplicantType;
            MasterDataStore loMasterLocalDataStore = goMasterDataStoreFactory.createLocal();
            loListApplicantType = goImagenesOnboardingEntityMapper.mapToEntity(loMasterLocalDataStore.getListImagenesOnboardingEntity());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loListApplicantType);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<DatosVisa> getDatosVisa(String poCategoria) {
        return Observable.create(loEmitter-> {
            RequestParamCategoryEntity poRequest = new RequestParamCategoryEntity();
            poRequest.setCategoria(poCategoria);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final MasterDataStore loMasterDataStore = goMasterDataStoreFactory.createCloudDatosVisa();
            loMasterDataStore.getDatosVisaEntity(new CallbackDataStore<DatosVisaEntity>() {
                @Override
                public void onSuccess(DatosVisaEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goDatosVisaEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,poRequest);
        });
    }

    @Override
    public Observable<DatosImagenes> getDatosImagenes(String poCategoria) {
        return Observable.create(loEmitter-> {
            RequestParamCategoryEntity poRequest = new RequestParamCategoryEntity();
            poRequest.setCategoria(poCategoria);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final MasterDataStore loMasterDataStore = goMasterDataStoreFactory.createCloudDatosImagenes();
            loMasterDataStore.getDatosImagenesEntity(new CallbackDataStore<DatosImagenesEntity>() {
                @Override
                public void onSuccess(DatosImagenesEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goDatosImagenesEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,poRequest);
        });
    }
}
