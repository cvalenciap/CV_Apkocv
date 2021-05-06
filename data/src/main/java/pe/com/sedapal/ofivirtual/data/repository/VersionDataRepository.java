package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.ValidateVersionEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ValidateVersionEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.VersionDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.VersionDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.ValidateVersion;
import pe.com.sedapal.ofivirtual.domain.repository.VersionRepository;

/**
 * Created by jsaenz on 29/07/2020
 */
@Singleton
public class VersionDataRepository implements VersionRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final VersionDataStoreFactory goVersionDataStoreFactory;
    private final ValidateVersionEntityMapper goValidateVersionEntityMapper;

    @Inject
    VersionDataRepository(TokenDataStoreFactory poTokenDataStoreFactory, VersionDataStoreFactory poVersionDataStoreFactory, ValidateVersionEntityMapper poValidateVersionEntityMapper) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goVersionDataStoreFactory = poVersionDataStoreFactory;
        this.goValidateVersionEntityMapper = poValidateVersionEntityMapper;
    }

    @Override
    public Observable<ValidateVersion> validateVersion(String psFlagChannel, String psVersionMovil) {
        ValidateVersionEntity loRequest = new ValidateVersionEntity();
        loRequest.setFlagChannel(psFlagChannel);
        loRequest.setVersionMovil(psVersionMovil);

        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final VersionDataStore loVersionDataStore = goVersionDataStoreFactory.createCloud();
            loVersionDataStore.validateVersion(new CallbackDataStore<ValidateVersionEntity>() {
                @Override
                public void onSuccess(ValidateVersionEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goValidateVersionEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequest);
        });
    }
}
