package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidSupplyEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UserEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UserDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ValidSupplyDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ValidSupplyDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidSupplyRepository;

/**
 * Created by Hernan Pareja on 5/01/2017.
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class ValidSupplyDataRepository implements ValidSupplyRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final ValidSupplyDataStoreFactory goValidSupplyDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;

    /**
     * Constructs a {@link ValidSupplyDataRepository}.
     *
     * @param poValidSupplyDataStoreFactory {@link UserDataStoreFactory} A factory to construct different data source implementations.
     * @param poTokenDataStoreFactory     {@link UserEntityMapper}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ValidSupplyDataRepository(ValidSupplyDataStoreFactory poValidSupplyDataStoreFactory, TokenDataStoreFactory poTokenDataStoreFactory) {
        this.goValidSupplyDataStoreFactory = poValidSupplyDataStoreFactory;
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
    }

    /**
     * Validate supply
     *
     * @param psNumSupply    Supply Number
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Override
    public Observable<String> valid(int psNumSupply) {
        RequestValidSupplyEntity loRequestValidSupplyEntity = new RequestValidSupplyEntity();
        loRequestValidSupplyEntity.setNisRad(psNumSupply);


        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final ValidSupplyDataStore loValidSupplyDataStore = goValidSupplyDataStoreFactory.createCloud();

            loValidSupplyDataStore.valid(new CallbackDataStore<String>() {
                @Override
                public void onSuccess(String poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(poData);
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC, loRequestValidSupplyEntity);
        });
    }
}
