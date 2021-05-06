package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.LiquidacionEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayLiquidationEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPagoLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.data.entity.mapper.LiquidacionEnchufateEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.RequestLiquidationInvoicesEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.EnchufateDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.EnchufateDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.Liquidacion;
import pe.com.sedapal.ofivirtual.domain.entity.PayLiquidation;
import pe.com.sedapal.ofivirtual.domain.entity.RequestLiquidacionEnchufate;
import pe.com.sedapal.ofivirtual.domain.repository.EnchufateRepository;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

/**
 * Created by jsaenz on 09-04-19
 * </p>
 * {@link MasterRepository} for retrieving Master data.
 */
@Singleton
public class EnchufateDataRepository implements EnchufateRepository {
    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final EnchufateDataStoreFactory goEnchufateDataStoreFactory;
    private final LiquidacionEnchufateEntityMapper goLiquidacionEnchufateEntityMapper;
    private final RequestLiquidationInvoicesEntityMapper goRequestLiquidationInvoicesEntityMapper;

    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    /**
     * Constructs a {@link EnchufateDataRepository}.
     * @author jsaenz
     * @version 1.0
     * @since 09/04/2019
     */
    @Inject
    EnchufateDataRepository(TokenDataStoreFactory poTokenDataStoreFactory,
                            EnchufateDataStoreFactory poEnchufateDataStoreFactory,
                            LiquidacionEnchufateEntityMapper poLiquidacionEnchufateEntityMapper,
                            RequestLiquidationInvoicesEntityMapper poRequestLiquidationInvoicesEntityMapper) {
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goEnchufateDataStoreFactory = poEnchufateDataStoreFactory;
        this.goLiquidacionEnchufateEntityMapper = poLiquidacionEnchufateEntityMapper;
        this.goRequestLiquidationInvoicesEntityMapper = poRequestLiquidationInvoicesEntityMapper;
    }

    @Override
    public Observable<Liquidacion> getCloudLiquidacionEnchufate(RequestLiquidacionEnchufate poRequestLiquidacionEnchufate) {
        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final EnchufateDataStore loEnchufateDataStore = goEnchufateDataStoreFactory.createCloudLiquidacionEnchufate();
            loEnchufateDataStore.generarLiquidacionEnchufateEntity(new CallbackDataStore<LiquidacionEntity>() {
                @Override
                public void onSuccess(LiquidacionEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goLiquidacionEnchufateEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, goRequestLiquidationInvoicesEntityMapper.transform(poRequestLiquidacionEnchufate));
        });
    }

    @Override
    public Observable<PayLiquidation> getCloudPagoLiquidacionEnchufate(String customerEmail,
                                                                       String numeroLiquidacion,
                                                                       String nisRad,
                                                                       String amount,
                                                                       String TRANSACTION_ID,
                                                                       String ACTION_CODE,
                                                                       String STATUS,
                                                                       String TRANSACTION_DATE,
                                                                       String ACTION_DESCRIPTION,
                                                                       String TRACE_NUMBER,
                                                                       String CARD,
                                                                       String BRAND,
                                                                       String authCorreo,
                                                                       String flagChannel,
                                                                       String listaRegistros) {
        return Observable.create(loEmitter -> {
            RequestPagoLiquidacionEnchufateEntity poRequest = new RequestPagoLiquidacionEnchufateEntity();
            poRequest.setCustomerEmail(customerEmail);
            poRequest.setNumeroLiquidacion(numeroLiquidacion);
            poRequest.setNisRad(nisRad);
            poRequest.setAmount(amount);
            poRequest.setTRANSACTION_ID(TRANSACTION_ID);
            poRequest.setACTION_CODE(ACTION_CODE);
            poRequest.setSTATUS(STATUS);
            poRequest.setTRANSACTION_DATE(TRANSACTION_DATE);
            poRequest.setACTION_DESCRIPTION(ACTION_DESCRIPTION);
            poRequest.setTRACE_NUMBER(TRACE_NUMBER);
            poRequest.setCARD(CARD);
            poRequest.setBRAND(BRAND);
            poRequest.setAuthCorreo(authCorreo);
            poRequest.setFlagChannel(flagChannel);
            poRequest.setListaRegistros(listaRegistros);

            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final EnchufateDataStore loEnchufateDataStore = goEnchufateDataStoreFactory.createCloudPagoLiquidacionEnchufate();
            loEnchufateDataStore.generarPagoLiquidacionEnchufateEntity(new CallbackDataStore<PayLiquidationEntity>() {
                @Override
                public void onSuccess(PayLiquidationEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goLiquidacionEnchufateEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, poRequest);
        });
    }
}
