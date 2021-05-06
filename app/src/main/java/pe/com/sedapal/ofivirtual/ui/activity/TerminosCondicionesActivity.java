package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;

public class TerminosCondicionesActivity extends BaseActivity {

    @BindView(R.id.toolbar_close)
    Toolbar toolbar_close;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.btnAceptoTerminos)
    Button btnAceptoTerminos;
    @BindView(R.id.txtDeclinar)
    //TextView txtDeclinar;
    //@BindView(R.id.txtContenidoTerminosCondiciones)
    TextView txtContenidoTerminosCondiciones;
    @BindView(R.id.wbTermsCond)
    WebView wbTermsCond;

    boolean boolAceptoTerminos = false;

    private static final String TAG = TerminosCondicionesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos_condiciones);
        ButterKnife.bind(this);
        setUpView();
    }

    public static Intent getCallingIntent(Context poContext) {
        Bundle loBundle = new Bundle();
        Intent loIntent = new Intent(poContext, TerminosCondicionesActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    private void setUpView() {
        configToolbar();
        loadTermsCondic();
    }

    private void loadTermsCondic(){
        WebSettings webSetting = wbTermsCond.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);

        wbTermsCond.setWebViewClient(new WebViewClient());
        wbTermsCond.loadUrl("file:///android_asset/terms_conditions.html");


        //String strContentTerm = getString(R.string.contenido_terminos_condiciones_html);
        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        //    txtContenidoTerminosCondiciones.setText(Html.fromHtml(strContentTerm,Html.FROM_HTML_MODE_LEGACY));
        //} else {
        //    txtContenidoTerminosCondiciones.setText(Html.fromHtml(strContentTerm));
        //}
    }

    private void configToolbar(){
        setSupportActionBar(toolbar_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_close.setNavigationIcon(R.drawable.ic_close_toolbar);
        toolbar_title.setText(R.string.title_terminos_condiciones);
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
        if(boolAceptoTerminos) {
            setResult(RESULT_OK, mIntent);
        }else setResult(RESULT_CANCELED, mIntent);

        super.onBackPressed();
    }

    @OnClick(R.id.btnAceptoTerminos)
    public void siAcepto(){
        boolAceptoTerminos = true;
        onBackPressed();
    }

    @OnClick(R.id.txtDeclinar)
    public void niAcepto(){
        boolAceptoTerminos = false;
        onBackPressed();
    }
}
