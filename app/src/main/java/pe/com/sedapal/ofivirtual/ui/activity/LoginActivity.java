package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerUserComponent;
import pe.com.sedapal.ofivirtual.di.components.UserComponent;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.presenter.user.LoginNewUserPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginNewUserView;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

public class LoginActivity extends BaseActivity implements HasComponent<UserComponent>,
        LoginNewUserView {

    @BindView(R.id.edtxtCorreo)
    EditText edtxtCorreo;
    @BindView(R.id.edtxtContrasenia)
    EditText edtxtContrasenia;
    @BindView(R.id.btnIniciarSesion)
    Button btnIniciarSesion;
    @BindView(R.id.txtOlvidasteContrasenia)
    TextView txtOlvidasteContrasenia;
    @BindView(R.id.txtRegistrate)
    TextView txtRegistrate;
    @BindView(R.id.llCircleProgressBarWhite)
    LinearLayout llCircleProgressBarWhite;
    @BindView(R.id.txtCircleProgessBarMessageLoad)
    TextView txtCircleProgessBarMessageLoad;
    @BindView(R.id.rlParamLogueo)
    RelativeLayout rlParamLogueo;

    private static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * Call start activty to {@link LoginActivity}
     *
     * @param poContext {@link Context} to application
     * @author jsaenz
     * @version 1.0
     * @since 09/02/2017
     */
    public static Intent getCallingIntent(Context poContext) {
        Bundle loBundle = new Bundle();
        Intent loIntent = new Intent(poContext, LoginActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }


    private UserComponent goUserComponent;

    @Inject
    LoginNewUserPresenter goLoginNewUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initializeInjector();
        setUpView();
    }

    private void initializeInjector() {
        this.goUserComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goUserComponent.inject(LoginActivity.this);
    }

    private void setUpView() {
        this.goLoginNewUserPresenter.setView(this);

        edtxtContrasenia.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    handled = true;
                    onClickIniciarSesion();
                }
                return handled;
            }
        });
    }

    @OnClick(R.id.btnIniciarSesion)
    public void onClickIniciarSesion(){
        hideKeyboard();
        validateNewUserLogin();
    }

    @OnClick(R.id.txtOlvidasteContrasenia)
    public void onClickOlvideContrasenia(){
        navigator.navigateToOlvContraCorreoActivity(this);
    }

    @OnClick(R.id.txtRegistrate)
    public void onClickTxtRegistrate(){
        navigator.navigateToRegistroUsuario(this);
    }

    public void validateNewUserLogin(){
        if(isValid()) {
            goLoginNewUserPresenter.initialize(edtxtCorreo.getText().toString(), edtxtContrasenia.getText().toString());
        }
    }

    public boolean isValid(){
        boolean response;
        if(ValidationUtil.esValidoCorreo(edtxtCorreo.getText().toString())){
            response = true;

            if(edtxtContrasenia.getText().toString().length() == 0){
                showDialogError(getString(R.string.mensaje_ingresepassword));
                return false;
            }

        }else {
            response = false;
            showDialogError(getString(R.string.mensaje_ingrese_correo_valido));
        }



        return response;
    }

    @Override
    public UserComponent getComponent() {
        return this.goUserComponent;
    }

    /**
     * Redireccionar a otras pantallas
     */

    /**
     * Para un usuario nuevo
     *
     * @param poUserModel
     */
    @Override
    public void showLoginNewUserSuccess(LoginNewUserModel poUserModel) {
        navigator.navigateToCommercialOfficeActivity(this, poUserModel);
    }

    //Permite al usuario validar su cuenta al ingresar a la aplicación
    @Override
    public void isPendingConfirmRegister(LoginNewUserModel poUserModel) {
        navigator.navigateToVerificationCodeActivity(this, edtxtCorreo.getText().toString(), poUserModel);
    }

    @Override
    public void showLoginNotRegisterEmail(String message) {
        showDialogEventButtonRegistro(new CallbackDialogRegistro() {
            @Override
            public void onClickOptionIntentarNuevamente() {
                onClickBtnIntentarNuevamente();
            }

            @Override
            public void onClickBtnRegistro() {
                onClickBtnRegistroUsuario();
            }
        },getResources().getString(R.string.lbl_correo_incorrecto),message,getResources().getString(R.string.lbl_intentar_nuevamente));
    }

    public void onClickBtnIntentarNuevamente(){
        validateNewUserLogin();
    }

    public void onClickBtnRegistroUsuario(){
        navigator.navigateToRegistroUsuario(this);
    }


    @Override
    public void showProgessDialog() {
        rlParamLogueo.setVisibility(View.GONE);
        llCircleProgressBarWhite.setVisibility(View.VISIBLE);
        txtCircleProgessBarMessageLoad.setText(getResources().getText(R.string.lbl_progressdialog_validando_usuario));
    }

    @Override
    public void hideProgessDialog() {
        rlParamLogueo.setVisibility(View.VISIBLE);
        llCircleProgressBarWhite.setVisibility(View.GONE);
    }

    @Override
    public void hideProgessDialogError() {
        rlParamLogueo.setVisibility(View.VISIBLE);
        llCircleProgressBarWhite.setVisibility(View.GONE);
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

    /**
     * Error
     *
     * @param
     */

    @Override
    public void errorLoginUser(String message) {
        showDialogError(message);
    }

    @Override
    public void showError(String psMessage) {
        LogUtil.i(TAG, psMessage);

//        String isUpdateRequiredDialog =psMessage.substring(0,7);
//
//        Log.e("TOMCAS",isUpdateRequiredDialog);
//        if(isUpdateRequiredDialog.equals(Constants.PARAMETROS_BD.UPDATE_APP_PARAM_CODE)){
//            Log.e("TOMCAS","Enviamos:"+psMessage.substring(isUpdateRequiredDialog.length()));
//            showDialogUpdateApp(psMessage.substring(isUpdateRequiredDialog.length()), new CallbackDialogUpdateApplication() {
//                @Override
//                public void onClickActualizarApp() {
//                    final String appPackageName = getPackageName();
//                    try {
//
//                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        i.putExtra("EXIT", true);
//                        startActivity(i);
//                        finish();
//                    } catch (android.content.ActivityNotFoundException anfe) {
//
//                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=pe.com.sedapal.ofivirtual&hl=es"));
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        i.putExtra("EXIT", true);
//                        startActivity(i);
//                        finish();
//                    }
//                }
//            });
//        }else {
        showDialogError(psMessage);
//        }
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goLoginNewUserPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goLoginNewUserPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goLoginNewUserPresenter.destroy();
    }


}
