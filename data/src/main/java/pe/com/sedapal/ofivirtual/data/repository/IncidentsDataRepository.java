package pe.com.sedapal.ofivirtual.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.IncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.MunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestIncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.IncidentsMunicipalitiesEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.MunicipalitiesEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.IncidentsDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.IncidentsDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.IncidentsMunicipalities;
import pe.com.sedapal.ofivirtual.domain.entity.Municipalities;
import pe.com.sedapal.ofivirtual.domain.repository.IncidentsRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * Created by jsaenz on 11/03/2019
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class IncidentsDataRepository implements IncidentsRepository {
    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final IncidentsDataStoreFactory goIncidentsDataStoreFactory;
    private final MunicipalitiesEntityMapper goMunicipalitiesEntityMapper;
    private final IncidentsMunicipalitiesEntityMapper goIncidentsMunicipalitiesEntityMapper;

    /**
     * Constructs a {@link IncidentsDataRepository}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 7/03/2019
     */
    @Inject
    IncidentsDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,
                            IncidentsDataStoreFactory poIncidentsDataStoreFactory,
                            MunicipalitiesEntityMapper poMunicipalitiesEntityMapper,
                            IncidentsMunicipalitiesEntityMapper poIncidentsMunicipalitiesEntityMapper) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goIncidentsDataStoreFactory = poIncidentsDataStoreFactory;
        this.goMunicipalitiesEntityMapper = poMunicipalitiesEntityMapper;
        this.goIncidentsMunicipalitiesEntityMapper = poIncidentsMunicipalitiesEntityMapper;
    }


    /**
     * Get Incidents
     * @author jsaenz
     * @version 1.0
     * @since 7/03/2019
     */

    @Override
    public Observable<List<Municipalities>> getListMunicipalities() {
        return Observable.create(loEmitter->{

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final IncidentsDataStore loIncidentsDataStore = goIncidentsDataStoreFactory.createCloudListMunicipalities();
            loIncidentsDataStore.getListMunicipalities(new CallbackDataStore<List<MunicipalitiesEntity>>() {
                @Override
                public void onSuccess(List<MunicipalitiesEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goMunicipalitiesEntityMapper.mapToEntity(poData));
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
    public Observable<List<IncidentsMunicipalities>> getListIncidentsMunicipalities(int psCodMunicipio) {
        return Observable.create(loEmitter->{
            RequestIncidentsMunicipalitiesEntity loRequestIncidentsMunicipalitiesEntity = new RequestIncidentsMunicipalitiesEntity();
            loRequestIncidentsMunicipalitiesEntity.setCodMunicipio(psCodMunicipio);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final IncidentsDataStore loIncidentsDataStore = goIncidentsDataStoreFactory.createCloudListIncidentsMunicipalities();
            loIncidentsDataStore.getListIncidentsMunicipalities(new CallbackDataStore<List<IncidentsMunicipalitiesEntity>>() {
                @Override
                public void onSuccess(List<IncidentsMunicipalitiesEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goIncidentsMunicipalitiesEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,loRequestIncidentsMunicipalitiesEntity);
        });
    }
}
