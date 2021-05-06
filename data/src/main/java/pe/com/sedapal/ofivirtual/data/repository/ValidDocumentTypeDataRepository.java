package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidDocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.ValidDocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UserEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ValidDocumentTypeEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UserDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ValidDocumentTypeDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.ValidDocumentTypeDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.ValidDocumentType;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidDocumentTypeRepository;

/**
 * Created by Hernan Pareja on 5/01/2017.
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class ValidDocumentTypeDataRepository implements ValidDocumentTypeRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final ValidDocumentTypeDataStoreFactory goValidDocumentTypeDataStoreFactory;
    private final ValidDocumentTypeEntityMapper goValidDocumentTypeEntityMapper;
    private final TokenDataStoreFactory goTokenDataStoreFactory;

    /**
     * Constructs a {@link ValidDocumentTypeDataRepository}.
     *
     * @param poValidDocumentTypeDataStoreFactory {@link UserDataStoreFactory} A factory to construct different data source implementations.
     * @param poTokenDataStoreFactory     {@link UserEntityMapper}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ValidDocumentTypeDataRepository(ValidDocumentTypeDataStoreFactory poValidDocumentTypeDataStoreFactory, ValidDocumentTypeEntityMapper poValidDocumentTypeEntityMapper, TokenDataStoreFactory poTokenDataStoreFactory) {
        this.goValidDocumentTypeDataStoreFactory = poValidDocumentTypeDataStoreFactory;
        this.goValidDocumentTypeEntityMapper = poValidDocumentTypeEntityMapper;
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
    }

    /**
     *
     * @param psDocumentType
     * @param psDocumentNumber
     * @return
     */
    @Override
    public Observable<ValidDocumentType> valid(int psNroSuministro,int psDocumentType, String psDocumentNumber) {
        RequestValidDocumentTypeEntity loRequestValidDocumentTypeEntity = new RequestValidDocumentTypeEntity();
        loRequestValidDocumentTypeEntity.setTipoDoc(psDocumentType);
        loRequestValidDocumentTypeEntity.setNroDoc(psDocumentNumber);
        loRequestValidDocumentTypeEntity.setNroSuministro(psNroSuministro);

        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final ValidDocumentTypeDataStore loValidDocumentTypeDataStore = goValidDocumentTypeDataStoreFactory.createCloud();

            loValidDocumentTypeDataStore.valid(new CallbackDataStore<ValidDocumentTypeEntity>() {
                @Override
                public void onSuccess(ValidDocumentTypeEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goValidDocumentTypeEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC, loRequestValidDocumentTypeEntity);
        });
    }
}
