package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.DaggerForgetPasswordEmailComponent;
import pe.com.sedapal.ofivirtual.di.components.ForgetPasswordEmailComponent;
import pe.com.sedapal.ofivirtual.presenter.user.ForgetPasswordEmailPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.ForgetPasswordEmailView;
import pe.com.sedapal.ofivirtual.presenter.view.LoadDataView;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

public class OlvContraCorreoActivity extends BaseActivity implements ForgetPasswordEmailView, LoadDataView {
    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;
    @BindView(R.id.tilCorreoRecupContra)
    TextInputLayout tilCorreoRecupContra;
    @BindView(R.id.edtxtCorreoRecupContra)
    EditText edtxtCorreoRecupContra;
    @BindView(R.id.btnEnviarRecuContra)
    Button btnEnviarRecuContra;
    @BindView(R.id.txtVolverRecuContra)
    TextView txtVolverRecuContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olv_contra_correo);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();
    }

    private ForgetPasswordEmailComponent goForgetPasswordEmailComponent;

    @Inject
    ForgetPasswordEmailPresenter goForgetPasswordEmailPresenter;

    private void initializeInjector() {
        this.goForgetPasswordEmailComponent = DaggerForgetPasswordEmailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goForgetPasswordEmailComponent.inject(OlvContraCorreoActivity.this);
    }

    private void setUpView() {
        this.goForgetPasswordEmailPresenter.setView(this);
        configToolbar();
        eventosControles();
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

    public static Intent getCallingIntent(Context poContext) {
        Intent loIntent = new Intent(poContext, OlvContraCorreoActivity.class);
        return loIntent;
    }

    public void eventosControles(){
        /**
         * Desabilitando el boton enviar
         */
        btnEnviarRecuContra.setEnabled(false);

        edtxtCorreoRecupContra.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    handled = true;
                    enviarCorreoReestablecerContrasenia();
                }
                return handled;
            }
        });

        edtxtCorreoRecupContra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ValidationUtil.esValidoCorreo(edtxtCorreoRecupContra.getText().toString())) {
                    tilCorreoRecupContra.setErrorEnabled(false);
                    tilCorreoRecupContra.setError(null);
                    btnEnviarRecuContra.setEnabled(true);
                } else {
                    tilCorreoRecupContra.setErrorEnabled(true);
                    tilCorreoRecupContra.setError(getResources().getString(R.string.lbl_error_correo));
                    btnEnviarRecuContra.setEnabled(false);
                }
            }
        });

    }

    @OnClick(R.id.txtVolverRecuContra)
    public void txtVolver(){
        onBackPressed();
    }

    @OnClick(R.id.btnEnviarRecuContra)
    public void onClickEnviar() {
        enviarCorreoReestablecerContrasenia();
    }

    public void enviarCorreoReestablecerContrasenia(){
        if (!edtxtCorreoRecupContra.getText().toString().equalsIgnoreCase("") && ValidationUtil.esValidoCorreo(edtxtCorreoRecupContra.getText().toString())) {
            goForgetPasswordEmailPresenter.initialize(edtxtCorreoRecupContra.getText().toString());
        } else {
            Toast.makeText(this, R.string.mensaje_ingrese_correo, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showForgetPasswordEmailSuccess(String poMessage) {
        StringBuilder strbMensaje = new StringBuilder();
        strbMensaje.append(getResources().getString(R.string.lbl_hemos__enviado_correo))
                .append(" ")
                .append("<b>")
                .append(edtxtCorreoRecupContra.getText().toString())
                .append("</b>")
                .append(" ")
                .append(getResources().getString(R.string.lbl_con_su_usuario));

        showDialogCorrecto("",strbMensaje.toString(), getResources().getString(R.string.lbl_volver_inicio) , R.drawable.ic_correo_enviado, new CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                onBackPressed();
            }
        });
    }

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
        this.goForgetPasswordEmailPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goForgetPasswordEmailPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goForgetPasswordEmailPresenter.destroy();
    }
}
