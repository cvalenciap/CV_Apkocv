package pe.com.sedapal.ofivirtual.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.DaggerDetailInvoiceComponent;
import pe.com.sedapal.ofivirtual.di.components.DetailInvoiceComponent;
import pe.com.sedapal.ofivirtual.model.DetailInvoicesModel;
import pe.com.sedapal.ofivirtual.model.DetailPayInvoiceModel;
import pe.com.sedapal.ofivirtual.model.ListPendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.presenter.file.GetPdfInvoicePresenter;
import pe.com.sedapal.ofivirtual.presenter.user.DetailInvoicesPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.DetailPayInvoicePresenter;
import pe.com.sedapal.ofivirtual.presenter.user.ValidateInvoicePreviousPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.DetailInvoicesView;
import pe.com.sedapal.ofivirtual.presenter.view.DetailPayInvoiceView;
import pe.com.sedapal.ofivirtual.presenter.view.GetPdfInvoiceView;
import pe.com.sedapal.ofivirtual.presenter.view.LoadDataView;
import pe.com.sedapal.ofivirtual.presenter.view.ValidateInvoicePreviousView;
import pe.com.sedapal.ofivirtual.ui.adapter.InvoicingDetailAdapter;
import pe.com.sedapal.ofivirtual.ui.adapter.PayDetailAdapter;
import pe.com.sedapal.ofivirtual.ui.component.app.GenericFileProvider;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

public class DetailInvoceActivity extends BaseActivity implements LoadDataView, DetailInvoicesView, DetailPayInvoiceView, GetPdfInvoiceView, ValidateInvoicePreviousView {
    @BindView(R.id.toolbar_back)
    Toolbar toolbar_back;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.txtTotalFacturado)
    TextView txtTotalFacturado;
    @BindView(R.id.txtMontoPagado)
    TextView txtMontoPagado;
    @BindView(R.id.txtDescripcionFecha)
    TextView txtDescripcionFecha;
    @BindView(R.id.txtFechVencimiento)
    TextView txtFechVencimiento;
    @BindView(R.id.imvIconOpen)
    ImageView imvIconOpen;
    @BindView(R.id.rcvDetailInvoce)
    RecyclerView rcvDetailInvoce;

    //Detalle de la facturacion
    @BindView(R.id.llVerRecibo)
    LinearLayout llVerRecibo;
    @BindView(R.id.rlDetalleFacturacion)
    RelativeLayout rlDetalleFacturacion;
    @BindView(R.id.llDetalleFacturacion)
    LinearLayout llDetalleFacturacion;
    @BindView(R.id.llVencimiento)
    LinearLayout llVencimiento;
    @BindView(R.id.txtNumSuministro)
    TextView txtNumSuministro;
    @BindView(R.id.txtReferenciaCobro)
    TextView txtReferenciaCobro;
    @BindView(R.id.txtestadorecibo)
    TextView txtestadorecibo;


    @BindView(R.id.lyestadoRecibo)
    LinearLayout lyestadoRecibo;


    @BindView(R.id.viewSeparador)
    View viewSeparador;




    //Detalle de pago
    @BindView(R.id.rlDetallePago)
    RelativeLayout rlDetallePago;
    @BindView(R.id.rcvDetailPay)
    RecyclerView rcvDetailPay;
    @BindView(R.id.llDetallePago)
    LinearLayout llDetallePago;
    @BindView(R.id.imvPayIconOpen)
    ImageView imvPayIconOpen;
    @BindView(R.id.llContenidoDetallePago)
    LinearLayout llContenidoDetallePago;

    //Mensaje de vencimiento en la parte superior
    @BindView(R.id.llInvoiceAlert)
    LinearLayout llInvoiceAlert;
    @BindView(R.id.txtDaysInvoiceVenc)
    TextView txtDaysInvoiceVenc;

    //Contenido
    @BindView(R.id.llContentInvoice)
    LinearLayout llContentInvoice;

    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;

    @BindView(R.id.btnPayInvoice)
    Button btnPayInvoice;

    InvoicingDetailAdapter goInvoicingDetailAdapter;
    PayDetailAdapter goPayDetailAdapter;

    public static final String BUNDLE_PENDING_INVOICE_SELECTED = "BUNDLE_PENDING_INVOICE_SELECTED";
    public static final String BUNDLE_PAY_INVOICE_SELECTED = "BUNDLE_PAY_INVOICE_SELECTED";
    PendingInvoicesModel goPendingInvoicesModelSelected;
    PayInvoicesModel goPayInvoicesModelSelected;

    boolean isLlDetalleFacturacionVisible = true;
    boolean isLlDetallePagoVisible = true;
    boolean isPendingInvoice; //VERIFICANDO SI ES RECIBO PENDIENTE O PAGADO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_invoice);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();
    }

    private DetailInvoiceComponent goDetailInvoiceComponent;

    @Inject
    DetailInvoicesPresenter goDetailInvoicesPresenter;

    @Inject
    DetailPayInvoicePresenter goDetailPayInvoicePresenter;

    @Inject
    GetPdfInvoicePresenter goGetPdfInvoicePresenter;

    @Inject
    ValidateInvoicePreviousPresenter goValidateInvoicePreviousPresenter;

    private void initializeInjector() {
        this.goDetailInvoiceComponent = DaggerDetailInvoiceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goDetailInvoiceComponent.inject(DetailInvoceActivity.this);
    }

    private void setUpView() {
        this.goDetailInvoicesPresenter.setView(this);
        this.goDetailPayInvoicePresenter.setView(this);
        this.goGetPdfInvoicePresenter.setView(this);
        this.goValidateInvoicePreviousPresenter.setView(this);
        goPendingInvoicesModelSelected = (PendingInvoicesModel) getIntent().getSerializableExtra(BUNDLE_PENDING_INVOICE_SELECTED);
        goPayInvoicesModelSelected = (PayInvoicesModel) getIntent().getSerializableExtra(BUNDLE_PAY_INVOICE_SELECTED);

        if(Long.parseLong(goPendingInvoicesModelSelected.getRecibo()) != 0){
            isPendingInvoice = true;
        }else {
            isPendingInvoice = false;
        }

        configToolbar();
        eventosControles();
        setupRecyclerView();
    }

    private void configToolbar(){
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_back.setNavigationIcon(R.drawable.ic_back_toolbar);
        if(isPendingInvoice) {
            toolbar_title.setText("Recibo de " + CommonUtil.getMonthAndYearValue(this, goPendingInvoicesModelSelected.getfFact()));
        }else {
            toolbar_title.setText("Recibo de " + CommonUtil.getMonthAndYearValue(this, goPayInvoicesModelSelected.getfFact()));
        }
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

    public static Intent getCallingIntent(Context poContext, PendingInvoicesModel poPendingInvoicesModel, PayInvoicesModel poPayInvoicesModel) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_PENDING_INVOICE_SELECTED, poPendingInvoicesModel);
        loBundle.putSerializable(BUNDLE_PAY_INVOICE_SELECTED, poPayInvoicesModel);
        Intent loIntent = new Intent(poContext, DetailInvoceActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    public void eventosControles(){
        if(isPendingInvoice){
            llInvoiceAlert.setVisibility(View.VISIBLE);
            //MOSTRANDO ALERTA DE DIAS VENCIDOS
            llContenidoDetallePago.setVisibility(View.GONE);//OCULTANDO EL CONTENEDOR MOSTRAR DETALLE PAGO
            //llDetallePago.setVisibility(View.GONE);//OCULTANDO LA LISTA DETALLE PAGO

            txtDaysInvoiceVenc.setText(
                    getResources().getString(R.string.lbl_recibo_vencido_dias) + " (" +
                            CommonUtil.getDaysBetween(goPendingInvoicesModelSelected.getVencimiento())
                            +" días de retraso)");

            txtDescripcionFecha.setText(getResources().getString(R.string.lbl_vencimiento_pago));

            txtTotalFacturado.setText(getResources().getString(R.string.lbl_money_soles) +" "+CommonUtil.valueDoubleFormat(goPendingInvoicesModelSelected.getDeuda()));
            txtFechVencimiento.setText(CommonUtil.getFormatDateValue(goPendingInvoicesModelSelected.getVencimiento()));
            txtNumSuministro.setText(String.valueOf(goPendingInvoicesModelSelected.getNisRad()));
            txtReferenciaCobro.setText(goPendingInvoicesModelSelected.getRecibo());
            txtestadorecibo.setText(goPayInvoicesModelSelected.getEstado());


            lyestadoRecibo.setVisibility(View.GONE);
            viewSeparador.setVisibility(View.GONE);


///////////////////////

        }else {
            llInvoiceAlert.setVisibility(View.GONE);
            llVencimiento.setVisibility(View.GONE); //DESABILITA FECHA DE VENCIMIENTO

            txtTotalFacturado.setText(getResources().getString(R.string.lbl_money_soles) +" "+  goPayInvoicesModelSelected.getTotalFact());  //   CommonUtil.valueDoubleFormat(goPayInvoicesModelSelected.getCobrado()));
            txtFechVencimiento.setText(CommonUtil.getFormatDateValue(goPayInvoicesModelSelected.getVencimiento()));
            txtNumSuministro.setText(String.valueOf(goPayInvoicesModelSelected.getNisRad()));
            txtReferenciaCobro.setText(goPayInvoicesModelSelected.getRecibo());
            txtestadorecibo.setText(goPayInvoicesModelSelected.getEstado());

            lyestadoRecibo.setVisibility(View.VISIBLE);
            viewSeparador.setVisibility(View.VISIBLE);
        }



        //txtReferenciaCobro.setText(goPendingInvoicesModelSelected);

        llVerRecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeShowInvoice();
            }
        });


        final Animation forward = AnimationUtils.loadAnimation(this, R.anim.rotate_foward);
        final Animation backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        rlDetalleFacturacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLlDetalleFacturacionVisible){
                    llDetalleFacturacion.setVisibility(View.GONE);
                    isLlDetalleFacturacionVisible = false;
                    imvIconOpen.startAnimation(forward);
                }else {
                    llDetalleFacturacion.setVisibility(View.VISIBLE);
                    isLlDetalleFacturacionVisible = true;
                    imvIconOpen.startAnimation(backward);
                }

                setAlphaAnimation(llDetalleFacturacion);
            }
        });

        rlDetallePago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLlDetallePagoVisible){
                    llDetallePago.setVisibility(View.GONE);
                    isLlDetallePagoVisible = false;
                    imvPayIconOpen.startAnimation(forward);
                }else {
                    llDetallePago.setVisibility(View.VISIBLE);
                    isLlDetallePagoVisible = true;
                    imvPayIconOpen.startAnimation(backward);
                }

                setAlphaAnimation(llDetallePago);
            }
        });

        btnPayInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializePayinvoice();
            }
        });

        llCircleProgressBar.setVisibility(View.VISIBLE);
        llContentInvoice.setVisibility(View.GONE);
        llVerRecibo.setEnabled(false);

        initializeDataDetailInvoice();
        initializeDataDetailPay();
        initializeValidateInvoicePrevious();
    }

    public void setAlphaAnimation(View v) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f);
        fadeIn.setDuration(300);
        final AnimatorSet mAnimationSet = new AnimatorSet();
        mAnimationSet.play(fadeIn);
        mAnimationSet.start();
    }

    public void setupRecyclerView(){
        this.goInvoicingDetailAdapter = new InvoicingDetailAdapter(this);
        this.goPayDetailAdapter = new PayDetailAdapter(this);

        LinearLayoutManager loDetailInvoicesAdapter = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rcvDetailInvoce.setLayoutManager(loDetailInvoicesAdapter);
        this.rcvDetailInvoce.setNestedScrollingEnabled(true);
        this.rcvDetailInvoce.setAdapter(goInvoicingDetailAdapter);

        LinearLayoutManager loPayDetailAdapter = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.rcvDetailPay.setLayoutManager(loPayDetailAdapter);
        this.rcvDetailPay.setNestedScrollingEnabled(true);
        this.rcvDetailPay.setAdapter(goPayDetailAdapter);
    }

    //region CALL WEBSERVICES
    public void initializeDataDetailInvoice(){
        if(isPendingInvoice) {
            this.goDetailInvoicesPresenter.initialize(Long.parseLong(goPendingInvoicesModelSelected.getRecibo()));
        }else {
            this.goDetailInvoicesPresenter.initialize(Long.parseLong(goPayInvoicesModelSelected.getRecibo()));
        }
    }

    public void initializeDataDetailPay(){
        /*if(isPendingInvoice) {
            this.goDetailPayInvoicePresenter.initialize(
                    goPendingInvoicesModelSelected.getNisRad(),
                    goPendingInvoicesModelSelected.getSecNis(),
                    goPendingInvoicesModelSelected.getfFact(),
                    goPendingInvoicesModelSelected.getSecRec());
        }else {
            this.goDetailPayInvoicePresenter.initialize(
                    goPayInvoicesModelSelected.getNisRad(),
                    goPayInvoicesModelSelected.getSecNis(),
                    goPayInvoicesModelSelected.getfFact(),
                    goPayInvoicesModelSelected.getSecRec());
        }*/

        if(isPendingInvoice) {
            this.goDetailPayInvoicePresenter.initialize(
                    goPendingInvoicesModelSelected.getRecibo(),
                    goPendingInvoicesModelSelected.getNroFactura());
        }else {
            this.goDetailPayInvoicePresenter.initialize(
                    goPayInvoicesModelSelected.getRecibo(),
                    goPayInvoicesModelSelected.getNroFactura());
        }
    }

    public void initializeShowInvoice(){
        if(isPendingInvoice) {
            this.goGetPdfInvoicePresenter.initialize(
                    String.valueOf(goPendingInvoicesModelSelected.getRecibo()),
                    goPendingInvoicesModelSelected.getSecRec(),
                    goPendingInvoicesModelSelected.getNisRad(),
                    goPendingInvoicesModelSelected.getSecNis(),
                    goPendingInvoicesModelSelected.getfFact());
        }else{
            this.goGetPdfInvoicePresenter.initialize(
                    goPayInvoicesModelSelected.getRecibo(),
                    goPayInvoicesModelSelected.getSecRec(),
                    goPayInvoicesModelSelected.getNisRad(),
                    goPayInvoicesModelSelected.getSecNis(),
                    goPayInvoicesModelSelected.getfFact());
        }
    }

    public void initializePayinvoice(){
        ListPendingInvoicesModel listPendingInvoicesModel = new ListPendingInvoicesModel();
        List<PendingInvoicesModel> pendingInvoicesModels = new ArrayList<>();
        pendingInvoicesModels.add(goPendingInvoicesModelSelected);

        listPendingInvoicesModel.setListPendingInvoicesModel(pendingInvoicesModels);
        navigator.navigateToPaySummaryActivity(DetailInvoceActivity.this,listPendingInvoicesModel,goPendingInvoicesModelSelected.getNisRad());
    }

    public void initializeValidateInvoicePrevious(){
        //Call validate pay previous invoice
        if(isPendingInvoice) {
            this.goValidateInvoicePreviousPresenter.initialize(
                    goPendingInvoicesModelSelected.getNisRad(),
                    Long.parseLong(goPendingInvoicesModelSelected.getRecibo()));
        }
    }
    //endregion

    //region RESPONSE WEBSERVICES

    /**
     * Se obtiene el detalle de la facturacion
     * @param poListDetailInvoicesModel
     */
    @Override
    public void showSucessListPendingInvoices(List<DetailInvoicesModel> poListDetailInvoicesModel) {
        if(poListDetailInvoicesModel!= null)
            goInvoicingDetailAdapter.setDetailInvoicesList(poListDetailInvoicesModel);

    }

    /**
     * Se obtiene el detalle de pagos realizados
     * @param poDetailPayInvoiceModel
     */

    @Override
    public void showSucessListDetailPay(List<DetailPayInvoiceModel> poDetailPayInvoiceModel) {
        setAlphaAnimation(llContentInvoice);
        if(poDetailPayInvoiceModel!= null) {
            if (!poDetailPayInvoiceModel.isEmpty()) {
                llContenidoDetallePago.setVisibility(View.VISIBLE);

                goPayDetailAdapter.setDetailPayInvoiceList(poDetailPayInvoiceModel);
                calculateTotalPay(poDetailPayInvoiceModel);
            }
        }

    }

    @Override
    public void hideLoadingDetailPay() {
        llVerRecibo.setEnabled(true);
        llCircleProgressBar.setVisibility(View.GONE);
        llContentInvoice.setVisibility(View.VISIBLE);
        setAlphaAnimation(llContentInvoice);
    }

    public void calculateTotalPay(List<DetailPayInvoiceModel> poDetailPayInvoiceModel){
        double montoTotalPagado = 0.0;
        for(DetailPayInvoiceModel poItemInvoice : poDetailPayInvoiceModel){
            montoTotalPagado = poItemInvoice.getMontoPago() + montoTotalPagado;
        }

        txtMontoPagado.setText(getResources().getString(R.string.lbl_money_soles) + " " + CommonUtil.valueDoubleFormat(montoTotalPagado));
    }

    @Override
    public void showGetPdfInvoiceSuccess(String psPath) {
        Uri loUri;
        Intent loIntent = new Intent();
        loIntent.setAction(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                loUri = GenericFileProvider.getUriForFile(context(), GenericFileProvider.class.getName(), new File(psPath));
                loIntent.setDataAndType(loUri, "application/pdf");
                loIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                loIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(loIntent);
            } catch (ActivityNotFoundException ex) {
                ex.printStackTrace();
                showError(getResources().getString(R.string.lbl_not_aplication_open_file));
            }
        } else {
            try {
                File loFile = new File(psPath);
                loUri = Uri.fromFile(loFile);
                loIntent.setDataAndType(loUri, "application/pdf");
                startActivity(loIntent);
            } catch (ActivityNotFoundException ex) {
                ex.printStackTrace();
                showError(getResources().getString(R.string.lbl_not_aplication_open_file));
            }
        }
    }

    /**
     * Se obtiene la respuesta si existe un recibo pendiente de pago
     */

    @Override
    public void showSucessValidateInvoicePrevious(String mensaje) {
        if(mensaje == "0"){
            btnPayInvoice.setVisibility(View.GONE);
        }else btnPayInvoice.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessageValidateInvoicePrevious(String poErrorMessage) {
        showError(poErrorMessage);
    }

    //endregion

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        showDialogError(psMessage);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goDetailInvoicesPresenter.resume();
        this.goGetPdfInvoicePresenter.resume();
        this.goValidateInvoicePreviousPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goDetailInvoicesPresenter.pause();
        this.goGetPdfInvoicePresenter.pause();
        this.goValidateInvoicePreviousPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goDetailInvoicesPresenter.destroy();
        this.goGetPdfInvoicePresenter.destroy();
        this.goValidateInvoicePreviousPresenter.destroy();
    }
}
