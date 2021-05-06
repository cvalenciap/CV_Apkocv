package pe.com.sedapal.ofivirtual.presenter.enchufate;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.domain.entity.PayLiquidation;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.enchufate.GetPagoLiquidacionEnchufateUseCase;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;
import pe.com.sedapal.ofivirtual.model.mapper.LiquidacionModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.presenter.view.PayLiquidationView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 24/04/19.
 */
public class PayLiquidacionEnchufatePresenter implements Presenter {
    private PayLiquidationView goPayLiquidationView;
    private final LiquidacionModelMapper goLiquidacionModelMapper;
    private final GetPagoLiquidacionEnchufateUseCase goGetPagoLiquidacionEnchufateUseCase;

    private int giCountRetry = 0;

    /**
     * Constructs a {@link PayLiquidacionEnchufatePresenter}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    @Inject
    public PayLiquidacionEnchufatePresenter(LiquidacionModelMapper poLiquidacionModelMapper,
                                            GetPagoLiquidacionEnchufateUseCase poGetPagoLiquidacionEnchufateUseCase) {
        this.goLiquidacionModelMapper = poLiquidacionModelMapper;
        this.goGetPagoLiquidacionEnchufateUseCase = poGetPagoLiquidacionEnchufateUseCase;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    public void setView(@NonNull PayLiquidationView poPayLiquidationView) {
        this.goPayLiquidationView = poPayLiquidationView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        //this.goGetPagoLiquidacionEnchufateUseCase.dispose();
        //this.goPayLiquidationView = null;
    }


    /**
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    public void initialize(String customerEmail,
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
        this.getPayLiquidacion(customerEmail,
                numeroLiquidacion,
                nisRad,
                amount,
                TRANSACTION_ID,
                ACTION_CODE,
                STATUS,
                TRANSACTION_DATE,
                ACTION_DESCRIPTION,
                TRACE_NUMBER,
                CARD,
                BRAND,
                authCorreo,
                flagChannel,
                listaRegistros);
    }

    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @param psBaseUri
     * @param psTokenGenerado
     * @param poNisRad
     * @since 24/04/19
     */
    private String customerEmail;
    private String numeroLiquidacion;
    private String nisRad;
    private String amount;
    private String TRANSACTION_ID;
    private String ACTION_CODE;
    private String STATUS;
    private String TRANSACTION_DATE;
    private String ACTION_DESCRIPTION;
    private String TRACE_NUMBER;
    private String CARD;
    private String BRAND;
    private String authCorreo;
    private String flagChannel;
    private String listaRegistros;

    private void getPayLiquidacion(String customerEmail,
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

        this.customerEmail = customerEmail;
        this.numeroLiquidacion = numeroLiquidacion;
        this.nisRad = nisRad;
        this.amount = amount;
        this.TRANSACTION_ID = TRANSACTION_ID;
        this.ACTION_CODE = ACTION_CODE;
        this.STATUS = STATUS;
        this.TRANSACTION_DATE = TRANSACTION_DATE;
        this.ACTION_DESCRIPTION = ACTION_DESCRIPTION;
        this.TRACE_NUMBER = TRACE_NUMBER;
        this.CARD = CARD;
        this.BRAND = BRAND;
        this.authCorreo = authCorreo;
        this.flagChannel = flagChannel;
        this.listaRegistros = listaRegistros;

        this.goGetPagoLiquidacion();
    }

    private void sendBroadcast(boolean pbSuccess, PayLiquidation loPayLiquidation) {
        PayLiquidationModel loPayLiquidationModel = goLiquidacionModelMapper.transform(loPayLiquidation);
        this.goPayLiquidationView.sendBroadcast(pbSuccess, loPayLiquidationModel);
    }

    private void goGetPagoLiquidacion() {
        giCountRetry++;
        GetPagoLiquidacionEnchufateUseCase.Params loParams =
                GetPagoLiquidacionEnchufateUseCase.Params.forValidate(
                        customerEmail,
                        numeroLiquidacion,
                        nisRad,
                        amount,
                        TRANSACTION_ID,
                        ACTION_CODE,
                        STATUS,
                        TRANSACTION_DATE,
                        ACTION_DESCRIPTION,
                        TRACE_NUMBER,
                        CARD,
                        BRAND,
                        authCorreo,
                        flagChannel,
                        listaRegistros);
        this.goGetPagoLiquidacionEnchufateUseCase.execute(new PayLiquidacionObserver(), loParams);
    }

    /**
     * Observer pagar liquidacion enchufate webservices
     */

    private final class PayLiquidacionObserver extends DefaultObserver<PayLiquidation> {

        @Override
        public void onComplete() {
            LogUtil.i("Pay Liquidacion", "Complete");
        }

        @Override
        public void onError(Throwable poException) {
            LogUtil.i("Pay Liquidacion", "Error");
            if (giCountRetry < 1) {
                goGetPagoLiquidacion();
            } else {
                sendBroadcast(false, new PayLiquidation());
            }
        }

        @Override
        public void onNext(PayLiquidation poData) {
            LogUtil.i("Pay Liquidacion", "Next");
            sendBroadcast(true, poData);
        }
    }

}
