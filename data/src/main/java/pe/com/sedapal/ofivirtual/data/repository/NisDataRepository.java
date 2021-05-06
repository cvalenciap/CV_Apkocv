package pe.com.sedapal.ofivirtual.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.HistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestHistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ListHistoricConsumEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ListNisEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.NisDetailEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.NisDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.NisDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.HistoricConsum;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.NisDetail;
import pe.com.sedapal.ofivirtual.domain.repository.NisRepository;

/**
 * Created by jsaenz on 11/03/2019
 * </p>
 * {@link NisDataRepository} for retrieving User data.
 */
@Singleton
public class NisDataRepository implements NisRepository {
    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final NisDataStoreFactory goNisDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;

    private final ListNisEntityMapper goListNisEntityMapper;
    private final NisDetailEntityMapper goNisDetailEntityMapper;
    private final ListHistoricConsumEntityMapper goListHistoricConsumEntityMapper;


    /**
     * Constructor
     * @param poTokenDataStoreFactory
     * @param poNisDataStoreFactory
     * @param poListNisEntityMapper
     */
    @Inject
    NisDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,
                      NisDataStoreFactory poNisDataStoreFactory,
                      ListNisEntityMapper poListNisEntityMapper,
                      NisDetailEntityMapper poNisDetailEntityMapper,
                      ListHistoricConsumEntityMapper poListHistoricConsumEntityMapper){
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goNisDataStoreFactory = poNisDataStoreFactory;
        this.goListNisEntityMapper = poListNisEntityMapper;
        this.goNisDetailEntityMapper = poNisDetailEntityMapper;
        this.goListHistoricConsumEntityMapper = poListHistoricConsumEntityMapper;
    }


    /**
     * Get list nis
     *
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */

    @Override
    public Observable<List<Nis>> listNis(long nisRad, String correo, String flag) {
        RequestNisEntity loRequestNisEntity = new RequestNisEntity();
        loRequestNisEntity.setNisRad(nisRad);
        loRequestNisEntity.setCorreo(correo);
        loRequestNisEntity.setFlag(flag);


        return Observable.create(loEmitter-> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final NisDataStore loNisDataStore = goNisDataStoreFactory.createCloud();
            loNisDataStore.getListNis(new CallbackDataStore<List<NisEntity>>() {
                @Override
                public void onSuccess(List<NisEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goListNisEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC,loRequestNisEntity);
        });
    }

    @Override
    public Observable<NisDetail> getNisDetail(long nisRad,String correo, String flag, String flag_multiple) {
        RequestNisDetailEntity loRequestNisDetailEntity = new RequestNisDetailEntity();
        loRequestNisDetailEntity.setNisRad(nisRad);
        loRequestNisDetailEntity.setCorreo(correo);
        loRequestNisDetailEntity.setFlag(flag);
        loRequestNisDetailEntity.setFlagMultiple(flag_multiple);

        return Observable.create(loEmitter-> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final NisDataStore loNisDataStore = goNisDataStoreFactory.createCloudDetailNis();
            loNisDataStore.getNisDetail(new CallbackDataStore<NisDetailEntity>() {
                @Override
                public void onSuccess(NisDetailEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goNisDetailEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,loRequestNisDetailEntity);
        });
    }

    @Override
    public Observable<List<HistoricConsum>> getListHistoricConsum(long nisRad) {
        RequestHistoricConsumEntity loRequestHistoricConsumEntity = new RequestHistoricConsumEntity();
        loRequestHistoricConsumEntity.setNisRad(nisRad);

        return Observable.create(loEmitter-> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final NisDataStore loHistoricConsumDataStore = goNisDataStoreFactory.createCloudListHistoricConsum();
            loHistoricConsumDataStore.getListHistoricConsum(new CallbackDataStore<List<HistoricConsumEntity>>() {
                @Override
                public void onSuccess(List<HistoricConsumEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goListHistoricConsumEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC,loRequestHistoricConsumEntity);
        });
    }
}
