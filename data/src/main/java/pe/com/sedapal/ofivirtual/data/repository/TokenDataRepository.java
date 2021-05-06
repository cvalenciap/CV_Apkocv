package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.TokenEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.repository.TokenRepository;

@Singleton
public class TokenDataRepository implements TokenRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";

    private final TokenDataStoreFactory goTokenDataStoreFactory;
    /**
     *
     */
    @Inject
    TokenDataRepository(TokenDataStoreFactory poTokenDataStoreFactory) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
    }

    @Override
    public Observable<String> obtainToken() {
        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenCloudDataStore = goTokenDataStoreFactory.createCloud();

            loTokenCloudDataStore.obtainToken(new CallbackDataStore<TokenEntity>() {
                @Override
                public void onSuccess(TokenEntity poData) {

                    final TokenDataStore loTokenLocalDataStore = goTokenDataStoreFactory.createLocal();
                    loTokenLocalDataStore.saveToken(DATE_TOKEN_SYNC, poData.getToken());

                    if(!loEmitter.isDisposed()){
                        loEmitter.onNext(poData.getToken());
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
}
