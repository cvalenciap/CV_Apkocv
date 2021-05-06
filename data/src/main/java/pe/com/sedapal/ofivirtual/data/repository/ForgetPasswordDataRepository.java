package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.ForgetPasswordSupplyEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestForgetPasswordEmail;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ForgetPasswordSupplyEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UserEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ForgetPasswordDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ForgetPasswordDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.ForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.entity.ReqForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.repository.ForgetPasswordRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * Created by Hernan Pareja on 5/01/2017.
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class ForgetPasswordDataRepository implements ForgetPasswordRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final ForgetPasswordDataStoreFactory goForgetPasswordDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final ForgetPasswordSupplyEntityMapper goForgetPasswordSupplyEntityMapper;

    /**
     * Constructs a {@link ForgetPasswordDataRepository}.
     *
     * @param  {@link UserDataStoreFactory} A factory to construct different data source implementations.
     * @param poTokenDataStoreFactory     {@link UserEntityMapper}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ForgetPasswordDataRepository(ForgetPasswordDataStoreFactory poForgetPasswordDataStoreFactory,TokenDataStoreFactory poTokenDataStoreFactory,ForgetPasswordSupplyEntityMapper poForgetPasswordSupplyEntityMapper) {
        this.goForgetPasswordDataStoreFactory = poForgetPasswordDataStoreFactory;
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goForgetPasswordSupplyEntityMapper = poForgetPasswordSupplyEntityMapper;
    }

    @Override
    public Observable<String> validForgetPasswordEmail(String correo) {
        RequestForgetPasswordEmail loRequestForgetPasswordEmail = new RequestForgetPasswordEmail();
        loRequestForgetPasswordEmail.setCorreo(correo);

        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final ForgetPasswordDataStore loForgetPasswordDataStore = goForgetPasswordDataStoreFactory.createCloudEmail();

            loForgetPasswordDataStore.validEmail(new CallbackDataStore<String>() {
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
            },TOKEN_SYNC, loRequestForgetPasswordEmail);
        });
    }

    @Override
    public Observable<ForgetPasswordSupply> validForgetPasswordSupply(ReqForgetPasswordSupply psForgetPasswordSupply) {
        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final ForgetPasswordDataStore loForgetPasswordDataStore = goForgetPasswordDataStoreFactory.createCloudSupply();

            loForgetPasswordDataStore.validSupply(new CallbackDataStore<ForgetPasswordSupplyEntity>() {
                @Override
                public void onSuccess(ForgetPasswordSupplyEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goForgetPasswordSupplyEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, goForgetPasswordSupplyEntityMapper.mapToEntity(psForgetPasswordSupply));
        });
    }
}
