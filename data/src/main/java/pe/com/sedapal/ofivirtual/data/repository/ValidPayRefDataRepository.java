package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.RequestPayRefEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UserEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UserDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ValidPayRefDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ValidPayRefDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidPayRefRepository;

/**
 * Created by Hernan Pareja on 5/01/2017.
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class ValidPayRefDataRepository implements ValidPayRefRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    //private final ValidSupplyDataStoreFactory goValidSupplyDataStoreFactory;
    private final ValidPayRefDataStoreFactory goValidPayRefDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;

    /**
     * Constructs a {@link ValidPayRefDataRepository}.
     *
     * @param poValidPayRefDataStoreFactory {@link UserDataStoreFactory} A factory to construct different data source implementations.
     * @param poTokenDataStoreFactory     {@link UserEntityMapper}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    ValidPayRefDataRepository(ValidPayRefDataStoreFactory poValidPayRefDataStoreFactory, TokenDataStoreFactory poTokenDataStoreFactory) {
        this.goValidPayRefDataStoreFactory = poValidPayRefDataStoreFactory;
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
    }

    /**
     * Validate supply
     *
     * @param psNumSupply    Supply Number
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Override
    public Observable<String> valid(long psNumSupply, long psReferenciaCobro) {
        RequestPayRefEntity loRequestPayRefEntity = new RequestPayRefEntity();
        loRequestPayRefEntity.setNisRad(psNumSupply);
        loRequestPayRefEntity.setReferenciaCobro(psReferenciaCobro);
        return Observable.create(loEmitter->{
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final ValidPayRefDataStore loValidPayRefDataStore = goValidPayRefDataStoreFactory.createCloud();
            loValidPayRefDataStore.valid(new CallbackDataStore<String>() {
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
            },TOKEN_SYNC,loRequestPayRefEntity);
        });
    }
}
