package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.DaggerUserComponent;
import pe.com.sedapal.ofivirtual.di.components.UserComponent;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.presenter.user.VerificationCodePresenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoadDataView;
import pe.com.sedapal.ofivirtual.presenter.view.VerificationCodeView;
import pe.com.sedapal.ofivirtual.ui.component.pinEntry.PinEntryEditText;

public class VerificationCodeActivity extends BaseActivity implements VerificationCodeView, LoadDataView {
    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;

    @BindView(R.id.edtxtPinVerificationCode)
    PinEntryEditText edtxtPinVerificationCode;

    @BindView(R.id.btnValidateVerificationCode)
    Button btnValidateVerificationCode;

    @BindView(R.id.txtEmailUser)
    TextView txtEmailUser;

    @Inject
    VerificationCodePresenter goVerificationCodePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();
    }

    private UserComponent goDaggerUserComponent;

    //Bundles
    private static final String BUNDLE_USER_LOGIN = "USER_LOGIN_SUPPLY";
    private static final String BUNDLE_USER_EMAIL = "BUNDLE_USER_EMAIL";
    String psBundleEmail = "";
    boolean isEnabledSendPin = false;
    public static LoginNewUserModel goUsuarioPendienteVerificar;

    String psVerificationCode = "";

    private void initializeInjector() {
        this.goDaggerUserComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goDaggerUserComponent.inject(VerificationCodeActivity.this);
    }

    private void setUpView() {
        if (getIntent().getStringExtra(BUNDLE_USER_EMAIL) != null) {
            psBundleEmail = getIntent().getStringExtra(BUNDLE_USER_EMAIL);
        }

        if ((LoginNewUserModel) getIntent().getSerializableExtra(BUNDLE_USER_LOGIN) != null) {
            goUsuarioPendienteVerificar = (LoginNewUserModel) getIntent().getSerializableExtra(BUNDLE_USER_LOGIN);
        }

        this.goVerificationCodePresenter.setView(this);
        configToolbar();
        eventosControles();
    }

    private void configToolbar() {
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

    public static Intent getCallingIntent(Context poContext, String psUserEmail, LoginNewUserModel poLoginNewUserModel) {
        Bundle loBundle = new Bundle();
        loBundle.putString(BUNDLE_USER_EMAIL, psUserEmail);
        loBundle.putSerializable(BUNDLE_USER_LOGIN, poLoginNewUserModel);
        Intent loIntent = new Intent(poContext, VerificationCodeActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    public void eventosControles() {
        txtEmailUser.setText(psBundleEmail);

        btnValidateVerificationCode.setEnabled(false);

        edtxtPinVerificationCode.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence chrVerificationCode) {
                psVerificationCode = chrVerificationCode.toString();
                btnValidateVerificationCode.setEnabled(true);
                isEnabledSendPin = true;
            }

            @Override
            public void notValidPin() {
                btnValidateVerificationCode.setEnabled(false);
                isEnabledSendPin = false;
            }

        });

        edtxtPinVerificationCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!isEnabledSendPin) {
                        showToastError(getString(R.string.msg_insert_all_numbers_pin));
                    } else {
                        onClickEnviar();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @OnClick(R.id.btnValidateVerificationCode)
    public void onClickEnviar() {
        goVerificationCodePresenter.initializeValidateConfirmationCode(psBundleEmail, psVerificationCode);
    }

    @OnClick(R.id.txtSendConfirmCode)
    public void onClickSendVerificationCode() {
        goVerificationCodePresenter.initializeSendConfirmationCode(psBundleEmail);
    }


    /**
     * Response webservices
     */

    @Override
    public void showVerificationCodeSuccessToView(String psMessage) {
        /**
         * Una vez obtenido los datos del usuario logueado se almacenara
         * en una variable global y se validara las pantallas a mostrar
         */
        goUsuarioLogueado = goUsuarioPendienteVerificar;

        //Ingresa al aplicativo
        navigator.navigateToCommercialOfficeActivity(this, goUsuarioLogueado);
    }

    @Override
    public void showSendCodeSuccessToView(String psMessage) {
        /**
         * Permite obtener un nuevo código de confirmaci;on
         */
        showDialogCorrecto("", psMessage);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        edtxtPinVerificationCode.setError(true);
        showDialogError(psMessage);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goVerificationCodePresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goVerificationCodePresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goVerificationCodePresenter.destroy();
    }
}
