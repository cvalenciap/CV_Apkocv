package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lib.visanet.com.pe.visanetlib.VisaNet;
import lib.visanet.com.pe.visanetlib.presentation.custom.VisaNetViewAuthorizationCustom;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.entity.dto.SessionUserDto;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerPaySummaryComponent;
import pe.com.sedapal.ofivirtual.di.components.PaySummaryComponent;
import pe.com.sedapal.ofivirtual.model.CardTypeModel;
import pe.com.sedapal.ofivirtual.model.DatosVisaModel;
import pe.com.sedapal.ofivirtual.model.LiquidacionModel;
import pe.com.sedapal.ofivirtual.model.ListPendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.VisaErrorModel;
import pe.com.sedapal.ofivirtual.model.VisaSucessModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.enchufate.GenerateLiquidacionEnchufatePresenter;
import pe.com.sedapal.ofivirtual.presenter.master.CardTypePresenter;
import pe.com.sedapal.ofivirtual.presenter.master.ParametersVisaPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.CardTypeView;
import pe.com.sedapal.ofivirtual.presenter.view.LiquidacionEnchufateView;
import pe.com.sedapal.ofivirtual.presenter.view.LoadDataView;
import pe.com.sedapal.ofivirtual.presenter.view.ParametersVisaView;
import pe.com.sedapal.ofivirtual.ui.adapter.PaySummaryAdapter;
import pe.com.sedapal.ofivirtual.util.CommonUtil;
import pe.com.sedapal.ofivirtual.util.Constants;

public class PaySummaryActivity extends BaseActivity implements HasComponent<PaySummaryComponent>,
        LoadDataView, ParametersVisaView, LiquidacionEnchufateView, CardTypeView {
    @BindView(R.id.toolbar_back)
    Toolbar toolbar_back;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.txtNumSupply)
    TextView txtNumSupply;
    @BindView(R.id.txtSubtotal)
    TextView txtSubtotal;
    @BindView(R.id.txtUsoCanalOnline)
    TextView txtUsoCanalOnline;
    @BindView(R.id.txtCodigoLiquidacion)
    TextView txtCodigoLiquidacion;
    @BindView(R.id.txtMontoTotal)
    TextView txtMontoTotal;
    @BindView(R.id.btnContinue)
    Button btnContinue;

    @BindView(R.id.txtTerminosCondiciones)
    TextView txtTerminosCondiciones;
    @BindView(R.id.chkTerminosCondiciones)
    CheckBox chkTerminosCondiciones;

    @BindView(R.id.rcvPaySummary)
    RecyclerView rcvPaySummary;

    //Content
    @BindView(R.id.nscrollContent)
    NestedScrollView nscrollContent;

    //Load and error
    @BindView(R.id.rlProgressLoadScreeen)
    RelativeLayout rlProgressLoadScreeen;
    @BindView(R.id.pbProgressBarLoadScreen)
    ProgressBar pbProgressBarLoadScreen;
    @BindView(R.id.llError)
    LinearLayout llError;
    @BindView(R.id.txtErrorDetail)
    TextView txtErrorDetail;
    @BindView(R.id.rlUsoCanalOnline)
    RelativeLayout rlUsoCanalOnline;

    PaySummaryAdapter goPaySummaryAdapter;
    List<PendingInvoicesModel> goListPendingInvoicesModel;
    List<CardTypeModel> loListCardTypeModel;
    long GET_BUNDLE_NIS_RAD = 0;

    public static final String BUNDLE_PENDING_LIST_INVOICE_SELECTED = "BUNDLE_PENDING_LIST_INVOICE_SELECTED";
    public static final String BUNDLE_NIS_RAD_INVOICE_SELECTED = "BUNDLE_NIS_RAD_INVOICE_SELECTED";
    public static String KEY_USER_SESSION_SP = "KEY_USER_SESSION_SP";
    private static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * Send in Background Pay Liquidation
     */
    private BroadcastReceiver goPayLiquidationReceiver;
    private boolean goPayLiquidationCompleted = true;
    private PayLiquidationModel goPayLiquidationModel;
    private static final String BUNDLE_LIQUIDATION = "BUNDLE_LIQUIDATION";
    private static SessionUserDto loSessionUserDto;

    /**
     * Datos de la liquidacipn
     */
    LiquidacionModel goLiquidacionModel;

    /**
     * Datos visa
     */
    DatosVisaModel goDatosVisaModel;

    /**
     * Montos Calculados por pagar
     */
    double subTotal = 0.0;
    double usoCanalOnline = 0.0;
    double totalCalculado = 0.0;

    @Inject
    ParametersVisaPresenter goParametersVisaPresenter;

    @Inject
    GenerateLiquidacionEnchufatePresenter goGenerateLiquidacionEnchufatePresenter;

    @Inject
    CardTypePresenter goCardTypePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_summary);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();


    }

    private PaySummaryComponent goPaySummaryComponent;

    private void initializeInjector() {
        this.goPaySummaryComponent = DaggerPaySummaryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goPaySummaryComponent.inject(PaySummaryActivity.this);
    }

    private void setUpView() {
        this.goParametersVisaPresenter.setView(this);
        this.goGenerateLiquidacionEnchufatePresenter.setView(this);
        this.goCardTypePresenter.setView(this);

        ListPendingInvoicesModel goListPendingInvoicesModelObtain = (ListPendingInvoicesModel) getIntent().getSerializableExtra(BUNDLE_PENDING_LIST_INVOICE_SELECTED);
        goListPendingInvoicesModel = goListPendingInvoicesModelObtain.getListPendingInvoicesModel();
        GET_BUNDLE_NIS_RAD = getIntent().getLongExtra(BUNDLE_NIS_RAD_INVOICE_SELECTED, 0);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        loSessionUserDto = new Gson().fromJson(preferences.getString(KEY_USER_SESSION_SP, ""), SessionUserDto.class);

        configToolbar();
        setupRecyclerView();
        eventControls();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_back.setNavigationIcon(R.drawable.ic_back_toolbar);
        toolbar_title.setText(getResources().getString(R.string.lbl_ittle_pay_summary));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public PaySummaryComponent getComponent() {
        return this.goPaySummaryComponent;
    }

    public static Intent getCallingIntent(Context poContext, ListPendingInvoicesModel poListPendingInvoicesModel, long GET_BUNDLE_NIS_RAD) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_PENDING_LIST_INVOICE_SELECTED, poListPendingInvoicesModel);
        loBundle.putLong(BUNDLE_NIS_RAD_INVOICE_SELECTED, GET_BUNDLE_NIS_RAD);
        Intent loIntent = new Intent(poContext, PaySummaryActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    public void setupRecyclerView() {
        this.goPaySummaryAdapter = new PaySummaryAdapter(this);

        LinearLayoutManager loPaySummaryAdapter = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rcvPaySummary.setLayoutManager(loPaySummaryAdapter);
        this.rcvPaySummary.setNestedScrollingEnabled(true);
        this.rcvPaySummary.setAdapter(goPaySummaryAdapter);
    }

    public void eventControls() {
        txtNumSupply.setText(String.valueOf(GET_BUNDLE_NIS_RAD));

        txtTerminosCondiciones.setText(" " + getResources().getText(R.string.lbl_terminos_condiciones));
        txtTerminosCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToTerminosCondiciones(PaySummaryActivity.this, Navigator.REQUEST_TERM_CONDIC);
            }
        });

        chkTerminosCondiciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnContinue.setEnabled(true);
                } else btnContinue.setEnabled(false);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWarningOption(getResources().getString(R.string.advertencia_pago),
                        getResources().getString(R.string.contenido_aviso),
                        getResources().getString(R.string.continuar_pago), new CallbackDialogCorrecto() {
                            @Override
                            public void onClickBtnOption() {
                                initializeGenerateLiquidacion();
                            }
                        });
            }
        });

        /**
         * Obteniendo parametros desde los webservices
         * Se obtienen los parametros de visa desde la bdd
         */
        this.goParametersVisaPresenter.initializeParametersVisa();
        /**
         * Obteniendo los valores de las tarjetas desde la bdd
         */
        this.goCardTypePresenter.initialize();
    }

    //region SET DATA RECYCLERVIEW
    public void initializeDataListPaySummary() {
        if (goListPendingInvoicesModel != null) {
            if (!goListPendingInvoicesModel.isEmpty()) {
                //Ordenando la lista desde el mes mas antiguo al mes mas nuevo
                Collections.sort(goListPendingInvoicesModel, new Comparator<PendingInvoicesModel>() {
                    public int compare(PendingInvoicesModel o1, PendingInvoicesModel o2) {
                        return o1.getfFact().compareTo(o2.getfFact());
                    }
                });

                goPaySummaryAdapter.setPaySummaryList(goListPendingInvoicesModel);
            }
        }
    }

    public void calculateDataPay() {
        usoCanalOnline = goDatosVisaModel.getComision();
        for (PendingInvoicesModel goPendingInvoicesModel : goListPendingInvoicesModel) {
            subTotal = subTotal + goPendingInvoicesModel.getDeuda();
        }

        totalCalculado = usoCanalOnline + subTotal;
        /**
         * Ocultar cuando el valor del parametro comision es cero
         * Christian Loayza B. 22-10-2019
         */
        if (usoCanalOnline == 0) {
            rlUsoCanalOnline.setVisibility(View.GONE);
        } else {
            rlUsoCanalOnline.setVisibility(View.VISIBLE);
        }

        txtSubtotal.setText(getResources().getString(R.string.lbl_money_soles) + " " + CommonUtil.valueDoubleFormat(subTotal));
        txtUsoCanalOnline.setText(getResources().getString(R.string.lbl_money_soles) + " " + CommonUtil.valueDoubleFormat(usoCanalOnline));
        txtMontoTotal.setText(getResources().getString(R.string.lbl_money_soles) + " " + CommonUtil.valueDoubleFormat(totalCalculado));
    }


    public void invokeVisa() {

        Map<String, Object> data = new HashMap<>();
        data.put(VisaNet.VISANET_CHANNEL, VisaNet.Channel.MOBILE);
        data.put(VisaNet.VISANET_COUNTABLE, true);

        data.put(VisaNet.VISANET_USERNAME, goDatosVisaModel.getVisanetUsername());
        data.put(VisaNet.VISANET_PASSWORD, goDatosVisaModel.getVisanetPassword());
        data.put(VisaNet.VISANET_MERCHANT, goDatosVisaModel.getVisanetMerchant());
        data.put(VisaNet.VISANET_PURCHASE_NUMBER, goLiquidacionModel.getNumeroLiquidacion());
        data.put(VisaNet.VISANET_AMOUNT, totalCalculado);
        data.put(VisaNet.VISANET_REGISTER_EMAIL, loSessionUserDto.getEmail());

        //Especificación de endpoint (Ingreso obligatorio)
        data.put(VisaNet.VISANET_END_POINT_URL, goDatosVisaModel.getVisanetEndpointUrl());

        //Personalización (Ingreso opcional)
        VisaNetViewAuthorizationCustom custom = new VisaNetViewAuthorizationCustom();
        //Personalización 1: Configuración del nombre del comercio en el formulario de pago


        custom.setLogoTextMerchant(true);
        custom.setLogoTextMerchantText("");

        custom.setLogoImage(R.mipmap.logo_sedapal_visa);
        //Personalización 2: Configuración del color del botón pagar en el formulario de pago

        custom.setButtonColorMerchant(R.color.colorPrimary);
        custom.setInputCustom(true);

        try {
            VisaNet.authorization(PaySummaryActivity.this, data, custom);
        } catch (Exception e) {
            Log.i(TAG, "onClick: " + e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case VisaNet.VISANET_AUTHORIZATION:
                if (data != null) {
                    if (resultCode == RESULT_OK) {
                        String JSONString = data.getExtras().getString("keySuccess");
                        VisaSucessModel visaSucessModel = new Gson().fromJson(JSONString, VisaSucessModel.class);

                        /**
                         * Iniciar la liquidacion del pago cuando es correcto
                         */
                        initializePayLiquidacion(visaSucessModel, null, VisaNet.registerEmail);
                    } else {

                        String JSONStringError = data.getExtras().getString("keyError");
                        VisaErrorModel visaErrorModel = new Gson().fromJson(JSONStringError, VisaErrorModel.class);

                        /**
                         * Iniciar la liquidacion del pago cuando es erróneo
                         */

                        initializePayLiquidacion(null, visaErrorModel, VisaNet.registerEmail);
//                        showDialogErrorException(mensajeError);
                    }
                } else {
                    Log.v("VISA", "Se cancelo el pago visa");
                }
                break;

            case Navigator.REQUEST_TERM_CONDIC:
                if (resultCode == RESULT_OK) {
                    chkTerminosCondiciones.setChecked(true);
                } else {
                    chkTerminosCondiciones.setChecked(false);
                }
                break;

            case Navigator.REQUEST_PAY_SUCESS:
                if (resultCode == RESULT_OK) {
                    goToCommercialOfficeActivity();
                }
                break;
            case Navigator.REQUEST_PAY_ERROR:
                if (resultCode == RESULT_OK) {
                    goToCommercialOfficeActivity();
                }
                break;
        }

    }

    //region CALL WEBSERVICES

    /**
     * Se solicita que se genere la liquidacion
     */

    public void initializeGenerateLiquidacion() {
        this.goGenerateLiquidacionEnchufatePresenter.initialize(
                GET_BUNDLE_NIS_RAD,
                goListPendingInvoicesModel
        );
    }

    public void initializePayLiquidacion(VisaSucessModel poVisaSucessModel, VisaErrorModel visaErrorModel, String registerEmail) {
        if (poVisaSucessModel != null) {
            PaySummaryActivity.this.navigator.startPayLiquidationService(
                    context(),
                    registerEmail,
                    goLiquidacionModel.getNumeroLiquidacion(),
                    String.valueOf(GET_BUNDLE_NIS_RAD),
                    String.valueOf(goLiquidacionModel.getImporteTotal()),
                    poVisaSucessModel.getDataMap().getTRANSACTION_ID(),
                    poVisaSucessModel.getDataMap().getACTION_CODE(),
                    poVisaSucessModel.getDataMap().getSTATUS(),
                    poVisaSucessModel.getDataMap().getTRANSACTION_DATE(),
                    poVisaSucessModel.getDataMap().getACTION_DESCRIPTION(),
                    poVisaSucessModel.getDataMap().getTRACE_NUMBER(),
                    poVisaSucessModel.getDataMap().getCARD(),
                    poVisaSucessModel.getDataMap().getBRAND(),
                    loSessionUserDto.getEmail(),
                    Constants.VERSION_APP.FLAG_CHANNEL,
                    goLiquidacionModel.getListaRegistros()
            );

        } else {
            PaySummaryActivity.this.navigator.startPayLiquidationService(
                    context(),
                    registerEmail,
                    goLiquidacionModel.getNumeroLiquidacion(),
                    String.valueOf(GET_BUNDLE_NIS_RAD),
                    String.valueOf(goLiquidacionModel.getImporteTotal()),
                    visaErrorModel.getData().getTRANSACTION_ID(),
                    visaErrorModel.getData().getACTION_CODE(),
                    visaErrorModel.getData().getSTATUS(),
                    visaErrorModel.getData().getTRANSACTION_DATE(),
                    visaErrorModel.getData().getACTION_DESCRIPTION(),
                    visaErrorModel.getData().getTRACE_NUMBER(),
                    visaErrorModel.getData().getCARD(),
                    visaErrorModel.getData().getBRAND(),
                    loSessionUserDto.getEmail(),
                    Constants.VERSION_APP.FLAG_CHANNEL,
                    goLiquidacionModel.getListaRegistros()
            );
        }

        PaySummaryActivity.this.broadcastPayLiquidation();
    }

    private void broadcastPayLiquidation() {
        showLoading("Completando pago");

        IntentFilter loIntentFilter = new IntentFilter(
                getString(R.string.broadcast_pay_liquidation_completed));
        this.goPayLiquidationReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                hideLoading();

                //extract our message from intent
                LogUtil.i("PAY_SUMMARY", "receiver broadcast pay liquidation");
                try {
                    PaySummaryActivity.this.unregisterReceiver(PaySummaryActivity.this.goPayLiquidationReceiver);
                } catch (Exception poException) {

                }

                goPayLiquidationCompleted = intent.getBooleanExtra(getString(R.string.broadcast_success), false);
                goPayLiquidationModel = (PayLiquidationModel) intent.getSerializableExtra(BUNDLE_LIQUIDATION);

                if (goPayLiquidationCompleted) {
                    showPayLiquidationSuccess(goPayLiquidationModel);
                } else {
                    showPayLiquidationError();
                }
            }
        };

        this.registerReceiver(goPayLiquidationReceiver, loIntentFilter);
    }

    private void unRegisterReceiver() {
        if (PaySummaryActivity.this.goPayLiquidationReceiver != null) {
            try {
                PaySummaryActivity.this.unregisterReceiver(PaySummaryActivity.this.goPayLiquidationReceiver);
            } catch (Exception poException) {

            }
        }
    }
    //endregion

    //region RESPONSE WEBSERVICES

    @Override
    public void showDatosVisaSuccess(DatosVisaModel poDatosVisaModel) {
        initializeDataListPaySummary();
        this.goDatosVisaModel = poDatosVisaModel;

        calculateDataPay();
    }

    /**
     * MOSTRAR DATOS DE LOS TIPOS DE TARJETA DE VISA
     *
     * @return
     */

    @Override
    public void showIdCardType(List<CardTypeModel> listCardTypeModel) {
        if (listCardTypeModel != null)
            this.loListCardTypeModel = listCardTypeModel;
    }

    /**
     * Mostrar datos liquidacion obtenidos
     *
     * @param poLiquidacionModel
     */
    @Override
    public void showGenerateLiquidacionSuccess(LiquidacionModel poLiquidacionModel) {
        goLiquidacionModel = poLiquidacionModel;
        /**
         * Llamando a la API de visa para realizar el pago
         */
        invokeVisa();
    }

    /**
     * Mostrar datos al realizar el pago de la liquidacion
     *
     * @param goPayLiquidationModel
     */
    public void showPayLiquidationSuccess(PayLiquidationModel goPayLiquidationModel) {
        hideLoading();
        navigator.navigateToPaySucess(
                PaySummaryActivity.this,
                Navigator.REQUEST_PAY_SUCESS,
                goPayLiquidationModel,
                usoCanalOnline);
    }

    /**
     * Se esta haciendo uso del mostrar el activity de error de pago en los siguintes casos
     * - Error al generar el token en GenerateTokenEnchufatePresenter
     * - Error al generar la liquidacion en GenerateLiquidacionEnchufatePresenter
     */
    @Override
    public void showPayError() {
        hideLoading();
        showPayLiquidationError();
    }

    /**
     * Mostrar en caso se tenga un error desconocido
     * - Error al realizar el pago de la liquidacion luego de recibir la respuesta en background
     */
    public void showPayLiquidationError() {
        if (getApplicationContext() != null)
            navigator.navigateToPayError(PaySummaryActivity.this, Navigator.REQUEST_PAY_ERROR);
    }

    //endregion

    @Override
    public void showProgressLoading() {
        nscrollContent.setVisibility(View.INVISIBLE);
        rlProgressLoadScreeen.setVisibility(View.VISIBLE);
        pbProgressBarLoadScreen.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);
        txtErrorDetail.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressLoading() {
        nscrollContent.setVisibility(View.VISIBLE);
        rlProgressLoadScreeen.setVisibility(View.INVISIBLE);
        pbProgressBarLoadScreen.setVisibility(View.INVISIBLE);
        llError.setVisibility(View.GONE);
        txtErrorDetail.setVisibility(View.INVISIBLE);
    }

    public void goToCommercialOfficeActivity() {
        Intent intent = new Intent(PaySummaryActivity.this, CommercialOfficeActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finishAffinity();
    }

    /**
     * end
     */

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        nscrollContent.setVisibility(View.INVISIBLE);
        rlProgressLoadScreeen.setVisibility(View.VISIBLE);
        pbProgressBarLoadScreen.setVisibility(View.GONE);
        llError.setVisibility(View.VISIBLE);
        txtErrorDetail.setVisibility(View.VISIBLE);
        txtErrorDetail.setText(psMessage);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goParametersVisaPresenter.resume();
        this.goGenerateLiquidacionEnchufatePresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goParametersVisaPresenter.pause();
        this.goGenerateLiquidacionEnchufatePresenter.pause();
        unRegisterReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goParametersVisaPresenter.destroy();
        this.goGenerateLiquidacionEnchufatePresenter.destroy();
    }
}
