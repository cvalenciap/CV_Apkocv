package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.RequestInvoicePdfEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.FileDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.FileDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.repository.FileRepository;

/**
 * Created by jsaenz on 11/03/2019
 */

public class FileDataRepository implements FileRepository {

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final FileDataStoreFactory goFileDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;

    @Inject
    FileDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,FileDataStoreFactory poFileDataStoreFactory) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goFileDataStoreFactory = poFileDataStoreFactory;
    }

    @Override
    public Observable<String> getPdf(long secRec, long nisRad, long secNis, String fFact, String psNameFile) {
        return Observable.create(loEmitter -> {
            RequestInvoicePdfEntity loRequestInvoicePdfEntity = new RequestInvoicePdfEntity();
            loRequestInvoicePdfEntity.setSecRec(secRec);
            loRequestInvoicePdfEntity.setNisRad(nisRad);
            loRequestInvoicePdfEntity.setSecNis(secNis);
            loRequestInvoicePdfEntity.setfFact(fFact);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final FileDataStore loFileDiskDataStore = goFileDataStoreFactory.createDisk();
            final FileDataStore loFileCloudDataStore = goFileDataStoreFactory.createCloud();

            loFileCloudDataStore.downloadPdf(new CallbackDataStore<String>() {
                @Override
                public void onSuccess(String poData) {
                    loFileDiskDataStore.savePdf(new CallbackDataStore<String>() {
                        @Override
                        public void onSuccess(String poData) {
                            loEmitter.onNext(poData);
                            loEmitter.onComplete();
                        }

                        @Override
                        public void onError(Throwable poException) {
                            loEmitter.onError(poException);
                        }
                    }, poData , psNameFile);
                }

                @Override
                public void onError(Throwable poException) {
                    loEmitter.onError(poException);
                }
            }, TOKEN_SYNC,loRequestInvoicePdfEntity);
        });
    }
}
