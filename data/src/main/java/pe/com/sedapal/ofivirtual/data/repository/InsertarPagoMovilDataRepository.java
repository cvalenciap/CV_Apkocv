package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.ObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.InsertarPagoMovilEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ObtenerDatosPagoEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UserEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.InsertarPagoMovilDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.InsertarPagoMovilDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.ObtenerDatosPago;
import pe.com.sedapal.ofivirtual.domain.repository.InsertarPagoMovilRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * Created by Hernan Pareja on 5/01/2017.
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class InsertarPagoMovilDataRepository implements InsertarPagoMovilRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final InsertarPagoMovilDataStoreFactory goInsertarPagoMovilDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final InsertarPagoMovilEntityMapper goInsertarPagoMovilEntityMapper;
    private final ObtenerDatosPagoEntityMapper goObtenerDatosPagoEntityMapper;

    /**
     * Constructs a {@link InsertarPagoMovilDataRepository}.
     *
     * @param {@link                  UserDataStoreFactory} A factory to construct different data source implementations.
     * @param poTokenDataStoreFactory {@link UserEntityMapper}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    InsertarPagoMovilDataRepository(InsertarPagoMovilDataStoreFactory poInsertarPagoMovilDataStoreFactory,
                                    TokenDataStoreFactory poTokenDataStoreFactory,
                                    InsertarPagoMovilEntityMapper poInsertarPagoMovilEntityMapper,
                                    ObtenerDatosPagoEntityMapper poObtenerDatosPagoEntityMapper) {
        this.goInsertarPagoMovilDataStoreFactory = poInsertarPagoMovilDataStoreFactory;
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goInsertarPagoMovilEntityMapper = poInsertarPagoMovilEntityMapper;
        this.goObtenerDatosPagoEntityMapper = poObtenerDatosPagoEntityMapper;
    }


    @Override
    public Observable<ObtenerDatosPago> obtenerDatosPago(String psTrxId) {
        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            RequestObtenerDatosPagoEntity request = new RequestObtenerDatosPagoEntity();
            request.setTrxId(psTrxId);

            final InsertarPagoMovilDataStore loInsertarPagoMovilDataStore = goInsertarPagoMovilDataStoreFactory.createCloudInsertarPagoMovil();

            loInsertarPagoMovilDataStore.obtenerDatosPago(new CallbackDataStore<ObtenerDatosPagoEntity>() {
                @Override
                public void onSuccess(ObtenerDatosPagoEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goObtenerDatosPagoEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, request);
        });
    }
}
