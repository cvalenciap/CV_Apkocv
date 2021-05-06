package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.DaggerUserComponent;
import pe.com.sedapal.ofivirtual.di.components.UserComponent;
import pe.com.sedapal.ofivirtual.presenter.user.VerificationCodePresenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoadDataView;
import pe.com.sedapal.ofivirtual.presenter.view.VerificationCodeView;

public class RegisterUserSuccessActivity extends BaseActivity implements VerificationCodeView, LoadDataView {

    @BindView(R.id.toolbar_close)
    Toolbar toolbar_close;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.txtTittle)
    TextView txtTittle;
    @BindView(R.id.txtDescripcionMessage)
    TextView txtDescripcionMessage;

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtSendConfirmCode)
    TextView txtSendConfirmCode;

    private static final String TAG = RegisterUserSuccessActivity.class.getSimpleName();
    private static final String BUNDLE_EMAIL = "BUNDLE_EMAIL";
    private static final String BUNDLE_MESSAGE = "BUNDLE_MESSAGE";

    String psBundleMessage;
    String psBundleEmail;

    private UserComponent goDaggerUserComponent;

    @Inject
    VerificationCodePresenter goVerificationCodePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_sucess);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();
    }

    private void initializeInjector() {
        this.goDaggerUserComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goDaggerUserComponent.inject(RegisterUserSuccessActivity.this);
    }

    public static Intent getCallingIntent(Context poContext, String poMessage, String poEmail) {
        Bundle loBundle = new Bundle();
        loBundle.putString(BUNDLE_MESSAGE, poMessage);
        loBundle.putString(BUNDLE_EMAIL, poEmail);
        Intent loIntent = new Intent(poContext, RegisterUserSuccessActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    private void setUpView() {
        this.goVerificationCodePresenter.setView(this);

        configToolbar();
        configNotificationBar();
        Bundle loBundle = getIntent().getExtras();
        psBundleMessage = loBundle.getString(BUNDLE_MESSAGE);
        psBundleEmail = loBundle.getString(BUNDLE_EMAIL);

        String[] paMessageSeparated = psBundleMessage.split("\\|");
        String tittleMessage = paMessageSeparated[0].toString();
        String bodyMessage = paMessageSeparated[1];

        txtTittle.setText(tittleMessage);
        txtDescripcionMessage.setText(Html.fromHtml(
                getResources().getString(R.string.lbl_success_message_part1) +
                        "<b>" + psBundleEmail + "</b>" +
                        getResources().getString(R.string.lbl_success_message_part2)));
    }

    private void configToolbar() {
        setSupportActionBar(toolbar_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_close.setNavigationIcon(R.drawable.ic_close_toolbar_gray);
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
                btnGoToLogin();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btnLogin})
    public void btnGoToLogin() {
        navigator.navigateToLogin(this);
    }

    @OnClick(R.id.txtSendConfirmCode)
    public void onClickSendVerificationCode() {
        goVerificationCodePresenter.initializeSendConfirmationCode(psBundleEmail);
    }

    @Override
    public void showVerificationCodeSuccessToView(String psMessage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showSendCodeSuccessToView(String psMessage) {
        /**
         * Permite obtener un nuevo código de confirmaci;on
         */
        showDialogCorrecto("", psMessage);
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
}
