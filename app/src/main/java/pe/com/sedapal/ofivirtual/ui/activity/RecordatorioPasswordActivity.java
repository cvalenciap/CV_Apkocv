package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.ForgetPasswordSupplyModel;

public class RecordatorioPasswordActivity extends BaseActivity {

    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;
    @BindView(R.id.edtxtUsuarioRecup)
    EditText edtxtUsuarioRecup;
    @BindView(R.id.edtxtContraseniaRecup)
    EditText edtxtContraseniaRecup;
    @BindView(R.id.btnVolverInicioRecup)
    Button btnVolverInicioRecup;
    @BindView(R.id.txtCopiar)
    TextView txtCopiar;

    private static final String BUNDLE_FORGET_PASSWORD_SUPPLY = "FORGET_PASSWORD_SUPPLY";
    private static final String BUNDLE_USER_SUPPLY = "USER_SUPPLY";
    private ForgetPasswordSupplyModel goForgetPasswordSupplyModel;
    private String goUserNumberSupply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio_password);

        ButterKnife.bind(this);
        setUpView();
    }

    private void setUpView() {
        configToolbar();
        Bundle loBundle = getIntent().getExtras();
        goForgetPasswordSupplyModel = (ForgetPasswordSupplyModel)loBundle.getSerializable(BUNDLE_FORGET_PASSWORD_SUPPLY);
        goUserNumberSupply = getIntent().getExtras().getString(BUNDLE_USER_SUPPLY);

        if(!goUserNumberSupply.equalsIgnoreCase("")){
            edtxtUsuarioRecup.setText(goUserNumberSupply);
        }

        if(!goForgetPasswordSupplyModel.getPassword().equalsIgnoreCase("")){
            edtxtContraseniaRecup.setText(goForgetPasswordSupplyModel.getPassword());
        }

        edtxtUsuarioRecup.setEnabled(false);
        edtxtContraseniaRecup.setEnabled(false);
    }

    private void configToolbar(){
        setSupportActionBar(toolbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_logo.setNavigationIcon(R.drawable.ic_back_toolbar);
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

    public static Intent getCallingIntent(Context poContext, ForgetPasswordSupplyModel poForgetPasswordSupplyModel, String poSupply) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_FORGET_PASSWORD_SUPPLY,poForgetPasswordSupplyModel);
        loBundle.putString(BUNDLE_USER_SUPPLY,poSupply);
        Intent loIntent = new Intent(poContext, RecordatorioPasswordActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    @OnClick(R.id.btnVolverInicioRecup)
    public void onClickVolverInicio(){
        navigator.navigateToLogin(this);
        finishAffinity();
    }

    @OnClick(R.id.txtCopiar)
    public void copyTextPassword(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("GET_PASSWORD",edtxtContraseniaRecup.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, getString(R.string.lbl_copiar_contrasenia), Toast.LENGTH_SHORT).show();
    }
}
