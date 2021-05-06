package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;

public class PaySuccessActivity extends BaseActivity {

    @BindView(R.id.toolbar_close)
    Toolbar toolbar_close;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.btnRegresar)
    Button btnRegresar;
    @BindView(R.id.txtNumeroOperacion)
    TextView txtNumeroOperacion;
    @BindView(R.id.txtFechaHora)
    TextView txtFechaHora;
    @BindView(R.id.txtImporte)
    TextView txtImporte;
    @BindView(R.id.txtCorreoUsuario)
    TextView txtCorreoUsuario;
    @BindView(R.id.txtNumeroTarjeta)
    TextView txtNumeroTarjeta;
    @BindView(R.id.txtNumeroLiquidacion)
    TextView txtNumeroLiquidacion;
    @BindView(R.id.txtCodigoTransaccion)
    TextView txtCodigoTransaccion;

    private static final String TAG = PaySuccessActivity.class.getSimpleName();
    private static final String BUNDLE_LIQUIDATION = "BUNDLE_LIQUIDATION";
    private static final String BUNDLE_USO_CANAL_ONLINE = "BUNDLE_USO_CANAL_ONLINE";
    private PayLiquidationModel goLiquidacionModel;
    private double goUsoCanalOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_sucess);
        ButterKnife.bind(this);
        setUpView();
    }

    public static Intent getCallingIntent(Context poContext, PayLiquidationModel poPayLiquidationModel, double usoCanalOnline) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_LIQUIDATION, poPayLiquidationModel);
        loBundle.putDouble(BUNDLE_USO_CANAL_ONLINE, usoCanalOnline);
        Intent loIntent = new Intent(poContext, PaySuccessActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    private void setUpView() {
        configToolbar();
        configNotificationBar();
        Bundle loBundle = getIntent().getExtras();
        goLiquidacionModel = (PayLiquidationModel) loBundle.getSerializable(BUNDLE_LIQUIDATION);
        goUsoCanalOnline = loBundle.getDouble(BUNDLE_USO_CANAL_ONLINE, 0.0);

//        txtDescripcionPago.setText(Html.fromHtml(
//                        getResources().getString(R.string.lbl_tu_pago) + "<b>"+
//                        getResources().getString(R.string.lbl_money_soles)+
//                        (goLiquidacionModel.getImporteTotal() + goUsoCanalOnline) + getResources().getString(R.string.lbl_uso_canal_online_desc) + "</b>"+
//                        getResources().getString(R.string.lbl_ha_sido_procesado_correctamente) + "<b>"+
//                        goLiquidacionModel.getNumeroLiquidacion()+ "</b>"));


        txtNumeroOperacion.setText(goLiquidacionModel.getNumOperacion() != null ? goLiquidacionModel.getNumOperacion() : "");
        txtFechaHora.setText((goLiquidacionModel.getFecha() != null ? goLiquidacionModel.getFecha() : "") + " " +
                (goLiquidacionModel.getHora() != null ? goLiquidacionModel.getHora() : ""));
        txtImporte.setText(String.valueOf(goLiquidacionModel.getMonto()));
        txtCorreoUsuario.setText(goLiquidacionModel.getCorreo() != null ? goLiquidacionModel.getCorreo() : "");
        txtNumeroTarjeta.setText(goLiquidacionModel.getNumTarjeta() != null ? goLiquidacionModel.getNumTarjeta() : "");
        txtNumeroLiquidacion.setText(String.valueOf(goLiquidacionModel.getNumLiquidacion()));
        txtCodigoTransaccion.setText(goLiquidacionModel.getTrxId() != null ? goLiquidacionModel.getTrxId() : "");

    }

    private void configToolbar() {
        setSupportActionBar(toolbar_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_close.setNavigationIcon(R.drawable.ic_close_toolbar);
        toolbar_title.setText("");
    }

    private void configNotificationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
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

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent();
        //if(boolAceptoTerminos) {
        setResult(RESULT_OK, mIntent);
        //}else setResult(RESULT_CANCELED, mIntent);

        super.onBackPressed();
    }

    @OnClick(R.id.btnRegresar)
    public void btnRegresar() {
        onBackPressed();
    }

    //@OnClick(R.id.txtDeclinar)
    //public void niAcepto(){
    //    boolAceptoTerminos = false;
    //    onBackPressed();
    //}
}
