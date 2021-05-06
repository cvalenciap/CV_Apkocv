package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.BuildConfig;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerSplashComponent;
import pe.com.sedapal.ofivirtual.di.components.SplashComponent;
import pe.com.sedapal.ofivirtual.model.ImagenesOnboardingModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.SessionUserModel;
import pe.com.sedapal.ofivirtual.model.ValidateVersionModel;
import pe.com.sedapal.ofivirtual.presenter.master.ImagenesOnboardingPresenter;
import pe.com.sedapal.ofivirtual.presenter.master.SyncPresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.TokenPresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ValidateVersionPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.LoginNewUserPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.SessionUserPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.ImagenesOnboardingView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginNewUserView;
import pe.com.sedapal.ofivirtual.presenter.view.SessionUserView;
import pe.com.sedapal.ofivirtual.presenter.view.SyncView;
import pe.com.sedapal.ofivirtual.presenter.view.TokenView;
import pe.com.sedapal.ofivirtual.presenter.view.ValidateVersionView;
import pe.com.sedapal.ofivirtual.util.Constants;
import pe.com.sedapal.ofivirtual.util.DownloadImagesOnboarding;

public class SplashActivity extends BaseActivity implements HasComponent<SplashComponent>, SyncView, TokenView, SessionUserView, LoginNewUserView, ImagenesOnboardingView, ValidateVersionView {
    private SplashComponent splashComponent;
    private static final int SPLASH_DELAY = 2000;
    private TimerTask splash_task;
    private Timer timer;
    private Context ctx;

    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;

    @Inject
    TokenPresenter tokenPresenter;

    @Inject
    SyncPresenter syncPresenter;

    @Inject
    SessionUserPresenter goSessionUserPresenter;

    @Inject
    LoginNewUserPresenter goLoginNewUserPresenter;

    @Inject
    ImagenesOnboardingPresenter goImagenesOnboardingPresenter;

    @Inject
    ValidateVersionPresenter goValidateVersionPresenter;

    public static Intent getCallingIntent(Context poContext) {
        Intent loIntent = new Intent(poContext, SplashActivity.class);
        return loIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initializeInjector();
        setUpView();
    }

    private void initializeInjector() {
        this.splashComponent = DaggerSplashComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.splashComponent.inject(SplashActivity.this);
    }

    private void timerSplash() {
        ctx = this;
        timer = new Timer("SplashTimer", true);
        splash_task = new TimerTask() {
            @Override
            public void run() {
                tokenPresenter.initialize();
            }
        };
        timer.schedule(splash_task, SPLASH_DELAY);

    }

    private void setUpView() {
        this.syncPresenter.setView(this);
        this.tokenPresenter.setView(this);
        this.goSessionUserPresenter.setView(this);
        this.goLoginNewUserPresenter.setView(this);
        this.goImagenesOnboardingPresenter.setView(this);
        this.goValidateVersionPresenter.setView(this);
        //timerSplash();
    }

    private void initializeLoad() {
        /**
         * OBTIENE EL TOKEN DE LA SESION
         */
        tokenPresenter.initialize();
    }

    @Override
    public SplashComponent getComponent() {
        return splashComponent;
    }

    public void navigateToOnboarding() {
        navigator.navigateToOnboardingActivity(this);
        finish();
    }

    /**
     * OBTENIENDO IMAGENES WEBSERVICES, SE REALIZARA UNA DESCARGA PREVIA
     * PARA PODER OPTIMIZAR LA CARGA EN EL ONBOARDING
     */
    @Override
    public void showImagenesOnboarding(List<ImagenesOnboardingModel> listImagenesOnboardingModel) {
        if (listImagenesOnboardingModel != null) {
            if (!listImagenesOnboardingModel.isEmpty()) {

                DESC_ONBOARDING_1 = listImagenesOnboardingModel.get(0).getDescripcionOnboarding();
                DESC_ONBOARDING_2 = listImagenesOnboardingModel.get(1).getDescripcionOnboarding();
                DESC_ONBOARDING_3 = listImagenesOnboardingModel.get(2).getDescripcionOnboarding();
                DESC_ONBOARDING_4 = listImagenesOnboardingModel.get(3).getDescripcionOnboarding();

                new DownloadImagesOnboarding(this, listImagenesOnboardingModel, () -> goSessionUserPresenter.initializeObtainSession());
            }
        }
    }


    /**
     * WEBSERVICES GENERAR TOKEN
     *
     * @param strToken
     */
    @Override
    public void showSucessGetToken(String strToken) {
        goValidateVersionPresenter.initialize(Constants.VERSION_APP.FLAG_CHANNEL, BuildConfig.VERSION_NAME);
    }

    /**
     * WEBSERVICES OBTENER PARAMETROS SINCRONIZACION
     */

    @Override
    public void showSyncSuccess() {
        goImagenesOnboardingPresenter.initialize();
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    /**
     * WEBSERVICES SESION USUARIO
     *
     * @param poUserModel
     */

    @Override
    public void showSessionUserObtain(SessionUserModel poUserModel) {
        if (poUserModel.getEmail() == null && poUserModel.getPassword() == null) {
            /**
             * Si no encuentra un usuario logueado, muestra el onboarding de manera
             * directa
             */
            navigateToOnboarding();
        } else {
            /**
             * Si encuentra un usuario logueado inicia sesion para obtener los datos del
             * usuario, una vez obtenido estos datos se mostrara el onboarding
             */
            goLoginNewUserPresenter.initialize(poUserModel.getEmail(), poUserModel.getPassword());
        }
    }

    /**
     * Cerrando la sesion del usuario
     *
     * @param poResponse
     */
    @Override
    public void showDeleteSession(boolean poResponse) {
        /**
         * Se limpia la variable global la cual contiene todos los datos de sesion
         * del usuario
         */
        BaseActivity.goUsuarioLogueado = null;
        //Se continua con el flujo de la aplicación ingresando al onboarding
        navigateToOnboarding();
    }

    /**
     * WEBSERVICES INICIAR SESION USUARIO
     */

    @Override
    public void showLoginNewUserSuccess(LoginNewUserModel poUserModel) {
        /**
         * Una vez obtenido los datos del usuario logueado se almacenara
         * en una variable global, posteriormete se obtendran estos datos
         * en el onboarding y se validara las pantallas a mostrar
         */
        goUsuarioLogueado = poUserModel;
        navigateToOnboarding();
        //navigator.navigateToCommercialOfficeActivity(this,poUserModel);
    }

    /**
     * Webservices validar version
     */
    @Override
    public void showValidateVersionSuccess(ValidateVersionModel poValidateVersionModel) {

//        syncPresenter.initialize(); //temp

        try {
            if (poValidateVersionModel.getCodigoError() != null &&
                    poValidateVersionModel.getCodigoError().equalsIgnoreCase(Constants.PARAMETROS_BD.UPDATE_APP_PARAM_CODE)) {
                //Si el flag es obligatorio = 1 (Obligatorio)
                if (poValidateVersionModel.getEsObligatorio().equals(Constants.VERSION_APP.FLAG_REQUIRED)) {
                    //Eliminando sesion del usuario ya que se realizará una actualización obligatoria
                    goSessionUserPresenter.initializeDeleteSessionNotLoad();

                    showDialogEventButtonUpdateApp(
                            callbackUpdateApp, false, "",
                            getString(R.string.msg_update_app_new_version),
                            getString(R.string.msg_update), "");
                } else {
                    //Si el flag es obligatorio = 0 (Opcional)
                    showDialogEventButtonUpdateApp(
                            callbackUpdateApp, true, "",
                            getString(R.string.msg_update_app_optional),
                            getString(R.string.msg_update),
                            getString(R.string.msg_update_other_moment));
                }
            } else {
                /**
                 * OBTENIENDO Y SINCRONIZANDO PARAMETROS
                 */
                syncPresenter.initialize();
            }
        } catch (NumberFormatException ex) {
            hideProgessDialogError();
            showDialogError(getString(R.string.msg_update_other_error));
        }
    }

    /**
     * Callback update app
     */
    BaseActivity.CallbackDialogUpdateApp callbackUpdateApp = new CallbackDialogUpdateApp() {
        @Override
        public void onClickButtonUpdateAppDialog() {
            String appPackageName = context().getPackageName();
            try {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
                startActivity(i);
            } catch (android.content.ActivityNotFoundException ex) {
                Intent i = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)
                );
                startActivity(i);
            }
        }

        @Override
        public void onClickContinueUpdateAppDialog() {
            /**
             * OBTENIENDO Y SINCRONIZANDO PARAMETROS
             */
            syncPresenter.initialize();
        }
    };

    /**
     * Errors in app
     * @param
     */

    /**
     * Error mostrado luego que un usuario en sesion ingrese al aplicativo
     *
     * @param message
     */
    @Override
    public void errorLoginUser(String message) {
        showDialogEndSession(message, getString(R.string.lbl_volver_a_ingresar), new CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                //Eliminando sesion del usuario ya que se detectó algún cambio en sus datos
                goSessionUserPresenter.initializeDeleteSession();
            }
        });
    }

    @Override
    public void showError(String psMessage) {
        Log.e("Error", psMessage);
        showDialogError(psMessage);
    }

    @Override
    public void isPendingConfirmRegister(LoginNewUserModel poUserModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showLoginNotRegisterEmail(String message) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showProgessDialog() {
        //if(llCircleProgressBar.getVisibility() != View.VISIBLE)
        //    llCircleProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgessDialog() {

    }


    @Override
    public void hideProgessDialogError() {
        if (llCircleProgressBar.getVisibility() == View.VISIBLE)
            llCircleProgressBar.setVisibility(View.GONE);
    }

    /**
     * END
     */

    @Override
    public void showLoadingProgress() {
        if (llCircleProgressBar.getVisibility() != View.VISIBLE)
            llCircleProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        if (llCircleProgressBar.getVisibility() == View.VISIBLE)
            llCircleProgressBar.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.syncPresenter.resume();
        this.tokenPresenter.resume();
        this.goSessionUserPresenter.resume();
        this.goLoginNewUserPresenter.resume();
        this.goImagenesOnboardingPresenter.resume();
        this.goValidateVersionPresenter.resume();

        initializeLoad();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.syncPresenter.pause();
        this.tokenPresenter.pause();
        this.goSessionUserPresenter.pause();
        this.goLoginNewUserPresenter.pause();
        this.goImagenesOnboardingPresenter.pause();
        this.goValidateVersionPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.syncPresenter.destroy();
        this.tokenPresenter.destroy();
        this.goSessionUserPresenter.destroy();
        this.goLoginNewUserPresenter.destroy();
        this.goImagenesOnboardingPresenter.destroy();
        this.goValidateVersionPresenter.destroy();
    }
}
