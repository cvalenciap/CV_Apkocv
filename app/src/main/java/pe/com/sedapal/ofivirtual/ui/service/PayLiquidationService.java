package pe.com.sedapal.ofivirtual.ui.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerPayLiquidationComponent;
import pe.com.sedapal.ofivirtual.di.components.PayLiquidationComponent;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;
import pe.com.sedapal.ofivirtual.presenter.enchufate.PayLiquidacionEnchufatePresenter;
import pe.com.sedapal.ofivirtual.presenter.view.PayLiquidationView;


public class PayLiquidationService extends BaseService implements HasComponent<PayLiquidationComponent>, PayLiquidationView {

    private static final String TAG = PayLiquidationService.class.getSimpleName();

    @Inject
    PayLiquidacionEnchufatePresenter goPayLiquidacionEnchufatePresenter;

    private PayLiquidationComponent goPayLiquidationComponent;

    private static final String BUNDLE_LIQUIDATION = "BUNDLE_LIQUIDATION";

    private static final String BUNDLE_CUSTOMEREMAIL = "BUNDLE_CUSTOMEREMAIL";
    private static final String BUNDLE_NUMEROLIQUIDACION = "BUNDLE_NUMEROLIQUIDACION";
    private static final String BUNDLE_NISRAD = "BUNDLE_NISRAD";
    private static final String BUNDLE_AMOUNT = "BUNDLE_AMOUNT";
    private static final String BUNDLE_TRANSACTION_ID = "BUNDLE_TRANSACTION_ID";
    private static final String BUNDLE_ACTION_CODE = "BUNDLE_ACTION_CODE";
    private static final String BUNDLE_STATUS = "BUNDLE_STATUS";
    private static final String BUNDLE_TRANSACTION_DATE = "BUNDLE_TRANSACTION_DATE";
    private static final String BUNDLE_ACTION_DESCRIPTION = "BUNDLE_ACTION_DESCRIPTION";
    private static final String BUNDLE_TRACE_NUMBER = "BUNDLE_TRACE_NUMBER";
    private static final String BUNDLE_CARD = "BUNDLE_CARD";
    private static final String BUNDLE_BRAND = "BUNDLE_BRAND";
    private static final String BUNDLE_AUTHCORREO = "BUNDLE_AUTHCORREO";
    private static final String BUNDLE_FLAGCHANNEL = "BUNDLE_FLAGCHANNEL";
    private static final String BUNDLE_LISTAREGISTROS = "BUNDLE_LISTAREGISTROS";

    private static String customerEmail;
    private static String numeroLiquidacion;
    private static String nisRad;
    private static String amount;
    private static String TRANSACTION_ID;
    private static String ACTION_CODE;
    private static String STATUS;
    private static String TRANSACTION_DATE;
    private static String ACTION_DESCRIPTION;
    private static String TRACE_NUMBER;
    private static String CARD;
    private static String BRAND;
    private static String authCorreo;
    private static String flagChannel;
    private static String listaRegistros;

    public static Intent getCallingIntent(
            Context poContext,
            String customerEmail,
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
            String listaRegistros
    ) {
        Bundle loBundle = new Bundle();

        loBundle.putString(BUNDLE_CUSTOMEREMAIL, customerEmail);
        loBundle.putString(BUNDLE_NUMEROLIQUIDACION, numeroLiquidacion);
        loBundle.putString(BUNDLE_NISRAD, nisRad);
        loBundle.putString(BUNDLE_AMOUNT, amount);
        loBundle.putString(BUNDLE_TRANSACTION_ID, TRANSACTION_ID);
        loBundle.putString(BUNDLE_ACTION_CODE, ACTION_CODE);
        loBundle.putString(BUNDLE_STATUS, STATUS);
        loBundle.putString(BUNDLE_TRANSACTION_DATE, TRANSACTION_DATE);
        loBundle.putString(BUNDLE_ACTION_DESCRIPTION, ACTION_DESCRIPTION);
        loBundle.putString(BUNDLE_TRACE_NUMBER, TRACE_NUMBER);
        loBundle.putString(BUNDLE_CARD, CARD);
        loBundle.putString(BUNDLE_BRAND, BRAND);
        loBundle.putString(BUNDLE_AUTHCORREO, authCorreo);
        loBundle.putString(BUNDLE_FLAGCHANNEL, flagChannel);
        loBundle.putString(BUNDLE_LISTAREGISTROS, listaRegistros);

        Intent loIntent = new Intent(poContext, PayLiquidationService.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    @Override
    public void onCreate() {
        LogUtil.i(TAG, "INICIO - PAY LIQUIDATION");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.initializeInjector();

        this.customerEmail = intent.getStringExtra(BUNDLE_CUSTOMEREMAIL);
        this.numeroLiquidacion = intent.getStringExtra(BUNDLE_NUMEROLIQUIDACION);
        this.nisRad = intent.getStringExtra(BUNDLE_NISRAD);
        this.amount = intent.getStringExtra(BUNDLE_AMOUNT);
        this.TRANSACTION_ID = intent.getStringExtra(BUNDLE_TRANSACTION_ID);
        this.ACTION_CODE = intent.getStringExtra(BUNDLE_ACTION_CODE);
        this.STATUS = intent.getStringExtra(BUNDLE_STATUS);
        this.TRANSACTION_DATE = intent.getStringExtra(BUNDLE_TRANSACTION_DATE);
        this.ACTION_DESCRIPTION = intent.getStringExtra(BUNDLE_ACTION_DESCRIPTION);
        this.TRACE_NUMBER = intent.getStringExtra(BUNDLE_TRACE_NUMBER);
        this.CARD = intent.getStringExtra(BUNDLE_CARD);
        this.BRAND = intent.getStringExtra(BUNDLE_BRAND);
        this.authCorreo = intent.getStringExtra(BUNDLE_AUTHCORREO);
        this.flagChannel = intent.getStringExtra(BUNDLE_FLAGCHANNEL);
        this.listaRegistros = intent.getStringExtra(BUNDLE_LISTAREGISTROS);


        this.goPayLiquidacionEnchufatePresenter.setView(this);
        this.goPayLiquidacionEnchufatePresenter.initialize(
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
        return START_NOT_STICKY;
    }


    /**
     * Initialize injector in activity
     *
     * @author JSAENZ
     * @version 1.0
     * @since 23/04/19
     */
    private void initializeInjector() {

        this.goPayLiquidationComponent = DaggerPayLiquidationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .serviceModule(getServiceModule())
                .build();
        this.goPayLiquidationComponent.inject(PayLiquidationService.this);
    }

    @Override
    public void onDestroy() {
        LogUtil.i(TAG, "FIN - PAY LIQUIDATION");
        super.onDestroy();
        this.goPayLiquidacionEnchufatePresenter.destroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void sendBroadcast(boolean pbSuccess, PayLiquidationModel loPayLiquidationModel) {
        LogUtil.i(TAG, "SEND BROADCAST - PAY LIQUIDATION " + String.valueOf(pbSuccess));
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_LIQUIDATION, loPayLiquidationModel);

        Intent loIntent = new Intent();
        loIntent.setAction(getResources().getString(R.string.broadcast_pay_liquidation_completed))
                .putExtra(getString(R.string.broadcast_success), pbSuccess)
                .putExtras(loBundle);
        sendBroadcast(loIntent);
        stopSelf();
    }

    @Override
    public PayLiquidationComponent getComponent() {
        return goPayLiquidationComponent;
    }

}
