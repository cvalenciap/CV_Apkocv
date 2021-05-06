package pe.com.sedapal.ofivirtual.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.DetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.PendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateInvoicePreviousEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.DetailInvoicesEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.DetailPayInvoiceEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.PayInvoicesEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.PendingInvoicesEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.InvoicesDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.InvoicesDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.domain.entity.PayInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.PendingInvoices;
import pe.com.sedapal.ofivirtual.domain.repository.InvoicesRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * Created by jsaenz on 11/03/2019
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class InvoicesDataRepository implements InvoicesRepository {
    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final InvoicesDataStoreFactory goInvoicesDataStoreFactory;
    private final PendingInvoicesEntityMapper goPendingInvoicesEntityMapper;
    private final PayInvoicesEntityMapper goPayInvoicesEntityMapper;
    private final DetailInvoicesEntityMapper goDetailInvoicesEntityMapper;
    private final DetailPayInvoiceEntityMapper goDetailPayInvoiceEntityMapper;

    /**
     * Constructs a {@link InvoicesDataRepository}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 7/03/2019
     */
    @Inject
    InvoicesDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,
                           InvoicesDataStoreFactory poInvoicesDataStoreFactory,
                           PendingInvoicesEntityMapper poPendingInvoicesEntityMapper,
                           PayInvoicesEntityMapper poPayInvoicesEntityMapper,
                           DetailInvoicesEntityMapper poDetailInvoicesEntityMapper,
                           DetailPayInvoiceEntityMapper poDetailPayInvoiceEntityMapper) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goPendingInvoicesEntityMapper = poPendingInvoicesEntityMapper;
        this.goPayInvoicesEntityMapper = poPayInvoicesEntityMapper;
        this.goInvoicesDataStoreFactory = poInvoicesDataStoreFactory;
        this.goDetailInvoicesEntityMapper = poDetailInvoicesEntityMapper;
        this.goDetailPayInvoiceEntityMapper = poDetailPayInvoiceEntityMapper;
    }


    /**
     * Get list pending invoices
     * @author jsaenz
     * @version 1.0
     * @since 7/03/2019
     */

    @Override
    public Observable<List<PendingInvoices>> getListPendingInvoices(long psNisRad, int psPageNum, int psPageSize) {
        return Observable.create(loEmitter->{
            RequestPendingInvoicesEntity loRequestPendingInvoicesEntity = new RequestPendingInvoicesEntity();
            loRequestPendingInvoicesEntity.setNisRad(psNisRad);
            loRequestPendingInvoicesEntity.setPageNum(psPageNum);
            loRequestPendingInvoicesEntity.setPageSize(psPageSize);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final InvoicesDataStore loInvoicesDataStore = goInvoicesDataStoreFactory.createCloudListPendingInvoices();

            loInvoicesDataStore.getPendingInvoices(new CallbackDataStore<List<PendingInvoicesEntity>>() {
                @Override
                public void onSuccess(List<PendingInvoicesEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goPendingInvoicesEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequestPendingInvoicesEntity);
        });
    }

    @Override
    public Observable<List<PayInvoices>> getListPayInvoices(long psNisRad, int psPageNum, int psPageSize) {
        return Observable.create(loEmitter->{
            RequestPayInvoicesEntity loRequestPayInvoicesEntity = new RequestPayInvoicesEntity();
            loRequestPayInvoicesEntity.setNisRad(psNisRad);
            loRequestPayInvoicesEntity.setPageNum(psPageNum);
            loRequestPayInvoicesEntity.setPageSize(psPageSize);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final InvoicesDataStore loInvoicesDataStore = goInvoicesDataStoreFactory.createCloudListPayInvoices();

            loInvoicesDataStore.getPayInvoices(new CallbackDataStore<List<PayInvoicesEntity>>() {
                @Override
                public void onSuccess(List<PayInvoicesEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goPayInvoicesEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequestPayInvoicesEntity);
        });
    }

    @Override
    public Observable<List<DetailInvoices>> getListDetailInvoices(long psRecibo) {
        return Observable.create(loEmitter->{
            RequestDetailInvoicesEntity loRequestDetailInvoicesEntity = new RequestDetailInvoicesEntity();
            loRequestDetailInvoicesEntity.setRecibo(psRecibo);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final InvoicesDataStore loInvoicesDataStore = goInvoicesDataStoreFactory.createCloudDetailInvoices();
            loInvoicesDataStore.getDetailInvoices(new CallbackDataStore<List<DetailInvoicesEntity>>() {
                @Override
                public void onSuccess(List<DetailInvoicesEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goDetailInvoicesEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,loRequestDetailInvoicesEntity);
        });
    }

    @Override
    public Observable<List<DetailPayInvoice>> getListDetailPayInvoice(String psRecibo, String psNumFact) {
        return Observable.create(loEmitter->{
            RequestDetailPayInvoiceEntity loRequestDetailPayInvoiceEntity = new RequestDetailPayInvoiceEntity();
            loRequestDetailPayInvoiceEntity.setRecibo(psRecibo);
            loRequestDetailPayInvoiceEntity.setNroFactura(psNumFact);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final InvoicesDataStore loInvoicesDataStore = goInvoicesDataStoreFactory.createCloudDetailPayInvoice();
            loInvoicesDataStore.getDetailPayInvoice(new CallbackDataStore<List<DetailPayInvoiceEntity>>() {
                @Override
                public void onSuccess(List<DetailPayInvoiceEntity> poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goDetailPayInvoiceEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,loRequestDetailPayInvoiceEntity);
        });
    }

    @Override
    public Observable<String> validateInvoicePrevious(long psNisRad, long psRecibo) {
        return Observable.create(loEmitter->{
            RequestValidateInvoicePreviousEntity loRequestValidateInvoicePreviousEntity = new RequestValidateInvoicePreviousEntity();
            loRequestValidateInvoicePreviousEntity.setNisRad(psNisRad);
            loRequestValidateInvoicePreviousEntity.setRecibo(psRecibo);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final InvoicesDataStore loInvoicesDataStore = goInvoicesDataStoreFactory.createCloudValidateInvoicePrevious();
            loInvoicesDataStore.validateInvoicePrevious(new CallbackDataStore<String>() {
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
            },TOKEN_SYNC,loRequestValidateInvoicePreviousEntity);
        });
    }
}
