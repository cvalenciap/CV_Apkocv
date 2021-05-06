package pe.com.sedapal.ofivirtual.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.AgencyEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.AgencyEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.SubsidiaryEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.PayPlaceDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.PayPlaceDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.Agency;
import pe.com.sedapal.ofivirtual.domain.entity.Subsidiary;
import pe.com.sedapal.ofivirtual.domain.repository.PayPlaceRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * Created by jsaenz on 11/03/2019
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class PayPlaceDataRepository implements PayPlaceRepository {
    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final PayPlaceDataStoreFactory goPayPlaceDataStoreFactory;
    private final AgencyEntityMapper goAgencyEntityMapper;
    private final SubsidiaryEntityMapper goSubsidiaryEntityMapper;


    /**
     * Constructs a {@link PayPlaceDataRepository}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 7/03/2019
     */
    @Inject
    PayPlaceDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,
                           PayPlaceDataStoreFactory poPayPlaceDataStoreFactory,
                           AgencyEntityMapper poAgencyEntityMapper,
                           SubsidiaryEntityMapper poSubsidiaryEntityMapper) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goPayPlaceDataStoreFactory = poPayPlaceDataStoreFactory;
        this.goAgencyEntityMapper = poAgencyEntityMapper;
        this.goSubsidiaryEntityMapper = poSubsidiaryEntityMapper;
    }


    /**
     * Get list pending invoices
     * @author jsaenz
     * @version 1.0
     * @since 7/03/2019
     */


    @Override
    public Observable<List<Agency>> getListAgency() {
        return Observable.create(loEmitter->{
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final PayPlaceDataStore loPayPlaceDataStore = goPayPlaceDataStoreFactory.createCloudListAgency();
            loPayPlaceDataStore.getListAgency(new CallbackDataStore<List<AgencyEntity>>() {
                @Override
                public void onSuccess(List<AgencyEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goAgencyEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC);
        });
    }

    @Override
    public Observable<List<Subsidiary>> getListSubsidiary(long psCodAgencia) {
        return Observable.create(loEmitter->{
            RequestSubsidiaryEntity loRequestSubsidiaryEntity = new RequestSubsidiaryEntity();
            loRequestSubsidiaryEntity.setCodAgencia(psCodAgencia);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final PayPlaceDataStore loPayPlaceDataStore = goPayPlaceDataStoreFactory.createCloudListSubsidiary();
            loPayPlaceDataStore.getListSubsidiary(new CallbackDataStore<List<SubsidiaryEntity>>() {
                @Override
                public void onSuccess(List<SubsidiaryEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goSubsidiaryEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,loRequestSubsidiaryEntity);
        });
    }
}
