package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.AndroidApplication;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.ApplicationComponent;
import pe.com.sedapal.ofivirtual.di.components.MainComponent;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.ui.component.dialog.DatePickerDialogFragment;
import pe.com.sedapal.ofivirtual.ui.component.dialog.EventosDialogFragment;
import pe.com.sedapal.ofivirtual.ui.component.dialog.LoadingDialogFragment;
import pe.com.sedapal.ofivirtual.ui.component.dialog.UdpateAppDialogFragment;
import pe.com.sedapal.ofivirtual.ui.widget.ToastCustom;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity implements DatePickerDialogFragment.OnClickCallbackDateDialog, EventosDialogFragment.OnClickCallbackDialogEventos, UdpateAppDialogFragment.OnClickCallbackDialogEventos {

    /**
     * Informacion de sesion del usuario logueado
     * Informacion general del usuario logueado que se puede utilizar desde
     * los otros activities
     */
    public static LoginNewUserModel goUsuarioLogueado;

    /**
     * Urls de las imagenes del onboarding
     */

    public static BitmapDrawable BITMAP_IMG_ONBOARDING_1_test;
    public static Bitmap BITMAP_IMG_ONBOARDING_1;
    public static Bitmap BITMAP_IMG_ONBOARDING_2;
    public static Bitmap BITMAP_IMG_ONBOARDING_3;
    public static Bitmap BITMAP_IMG_ONBOARDING_4;
    public static Bitmap BITMAP_ICON_ONBOARDING_1;
    public static Bitmap BITMAP_ICON_ONBOARDING_2;
    public static Bitmap BITMAP_ICON_ONBOARDING_3;
    public static Bitmap BITMAP_ICON_ONBOARDING_4;
    public static String DESC_ONBOARDING_1 = "";
    public static String DESC_ONBOARDING_2 = "";
    public static String DESC_ONBOARDING_3 = "";
    public static String DESC_ONBOARDING_4 = "";

    @Inject
    public Navigator navigator;

    LoadingDialogFragment loadingDialogFragment;
    DatePickerDialogFragment datePickerDialogFragment;
    EventosDialogFragment eventosDialogFragment = null;
    UdpateAppDialogFragment udpateAppDialogFragment = null;
    CallbackDialogDate callbackDialogDate = null;
    CallbackDialogRegistro callbackDialogRegistro;
    CallbackDialogCorrecto callbackDialogCorrecto;
    CallbackDialogCloseDialog callbackDialogCloseDialog;
    //    CallbackDialogRelogin callbackDialogRelogin;
    CallbackDialogUpdateApp callbackDialogUpdateApp;
    boolean eventDialogAfterClose = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.remove("android:support:fragments");
        }
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Transitions activities
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Replace a {@link Fragment} to this activity's layout.
     *
     * @param piContainerViewId The container view to where replace the fragment.
     * @param poFragment        The fragment to be replaced.
     */
    protected void replaceFragment(int piContainerViewId, Fragment poFragment) {
        replaceFragment(piContainerViewId, poFragment, null);

    }

    /**
     * Replace a {@link Fragment} to this activity's layout.
     *
     * @param piContainerViewId The container view to where replace the fragment.
     * @param poFragment        The fragment to be replaced.
     * @param psAddToBackStack  An optional name for this back stack state, or null.
     */
    protected void replaceFragment(int piContainerViewId, Fragment poFragment, String psAddToBackStack) {
        FragmentTransaction loTransaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
//        loTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left,
//                android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        loTransaction.replace(piContainerViewId, poFragment);
        loTransaction.addToBackStack(psAddToBackStack);
        loTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        loTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link MainComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();

    }

    /**
     * Keyboad
     */
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View focusedView = this.getCurrentFocus();

        if (focusedView != null) {
            inputManager.hideSoftInputFromWindow(
                    this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void showToast(String psMessage) {
        Toast.makeText(this, psMessage, Toast.LENGTH_SHORT).show();
    }

    public void showLoading(String descripcion) {
        //FragmentManager fm = getSupportFragmentManager();
        loadingDialogFragment = LoadingDialogFragment.newInstance(descripcion);
        //loadingDialogFragment.show(fm, "fragment_dialog_loading");
        loadingDialogFragment.setCancelable(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(loadingDialogFragment, "fragment_dialog_loading");
        ft.commitAllowingStateLoss();
    }

    public void hideLoading() {
        if (loadingDialogFragment != null) {
            loadingDialogFragment.dismiss();
        }
    }

    public interface CallbackDialogDate {
        void fecha(String fecha);
    }

    public void showDatePicker(CallbackDialogDate callbackDialogDate) {
        this.callbackDialogDate = callbackDialogDate;
        FragmentManager fm = getSupportFragmentManager();
        if (datePickerDialogFragment == null) {
            datePickerDialogFragment = new DatePickerDialogFragment();
            datePickerDialogFragment.show(fm, "fragment_dialog_date_picker");
            datePickerDialogFragment.setCancelable(false);
        }
    }

    /**
     * Toast personalizados
     */

    public void showToastError(String message) {
        CommonUtil.mostrarToast(getApplicationContext(), message, ToastCustom.TYPE_ERROR);
    }

    public void showToastCorrect(String message) {
        CommonUtil.mostrarToast(getApplicationContext(), message, ToastCustom.TYPE_CORRECTO);
    }


    @Override
    public void onClickCloseDialog() {
        datePickerDialogFragment.dismiss();
        datePickerDialogFragment = null;
    }

    /**
     * Generando diálogo para seleccionar el tiempo
     *
     * @param fechaObtenida
     */

    @Override
    public void onClickSeleccionar(String fechaObtenida) {
        datePickerDialogFragment.dismiss();
        datePickerDialogFragment = null;
        callbackDialogDate.fecha(fechaObtenida);
    }

    public void showDialogEventImage(int imagen) {
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(imagen, false, false, "", "", "", 0, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_eventos");
        eventosDialogFragment.setCancelable(false);
    }

    /**
     * Generando diálogo para la opción que contiene el botón REGISTRATE
     */

    public interface CallbackDialogRegistro {
        void onClickOptionIntentarNuevamente();

        void onClickBtnRegistro();
    }

    public void showDialogEventButtonRegistro(CallbackDialogRegistro callbackDialogRegistro, String tittle, String description, String opcionInferior) {
        this.callbackDialogRegistro = callbackDialogRegistro;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, true, true, tittle, description, opcionInferior, 0, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_eventos");
        eventosDialogFragment.setCancelable(false);
    }

    /**
     * Generando dialogo para mostrar actualizacion de versión del aplicativo
     */
//    public void showDialogUpdateApp(String mensaje,CallbackDialogUpdateApplication callbackDialogUpdateApplication){
//        this.callbackDialogUpdateApplication = callbackDialogUpdateApplication;
//        FragmentManager fm = getSupportFragmentManager();
//
//        if(eventosDialogFragment == null) {
//            eventosDialogFragment = EventosDialogFragment.newInstance(0, true, false, getResources().getString(R.string.lbl_ocurrio_error), mensaje, "", R.drawable.ic_incidents_operat, false, true);
//            eventosDialogFragment.show(fm, "fragment_dialog_error");
//            eventosDialogFragment.setCancelable(false);
//            Log.d("prueba","showDialogUpdateApp");
//        }
//    }


    public interface CallbackDialogUpdateApp {
        void onClickButtonUpdateAppDialog();

        void onClickContinueUpdateAppDialog();
    }

    public void showDialogEventButtonUpdateApp(CallbackDialogUpdateApp callbackDialogUpdateApp, boolean isOpcional, String tittle, String description, String txtButton, String opcionInferior) {
        this.callbackDialogUpdateApp = callbackDialogUpdateApp;
        FragmentManager fm = getSupportFragmentManager();
        udpateAppDialogFragment = UdpateAppDialogFragment.newInstance(isOpcional, tittle, description, txtButton, opcionInferior, true);
        udpateAppDialogFragment.show(fm, "fragment_dialog_update_app");
        udpateAppDialogFragment.setCancelable(false);
    }


    /**
     * Generando diálogo para mostrar error
     */

    public void showDialogError(String mensaje) {
        FragmentManager fm = getSupportFragmentManager();

        if (eventosDialogFragment == null) {
            eventosDialogFragment = EventosDialogFragment.newInstance(0, true, false, getResources().getString(R.string.lbl_ocurrio_error), mensaje, "", 0, false, true);
            eventosDialogFragment.show(fm, "fragment_dialog_error");
            eventosDialogFragment.setCancelable(false);
            Log.d("prueba", "showDialogError");
        }


    }

    public void showDialogErrorException(String mensaje) {
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, true, false, getResources().getString(R.string.lbl_ocurrio_error), mensaje, "", 0, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_error");
        eventosDialogFragment.setCancelable(false);
        Log.d("prueba", "showDialogError");

    }

    public void showDialogAdvertencia(String mensaje) {
        FragmentManager fm = getSupportFragmentManager();

        if (eventosDialogFragment == null) {
            eventosDialogFragment = EventosDialogFragment.newInstance(0, true, false, getResources().getString(R.string.lbl_ocurrio_advertencia), mensaje, "", 0, false, true);
            eventosDialogFragment.show(fm, "fragment_dialog_error");
            eventosDialogFragment.setCancelable(false);
        }
    }


    /**
     * Generando diálogo para mostrar mensaje correcto
     */
    public interface CallbackDialogCorrecto {
        void onClickBtnOption();
    }

    public void showDialogCorrecto(String titulo, String descripcion, String lblOpcion, CallbackDialogCorrecto callbackDialogCorrecto) {
        this.callbackDialogCorrecto = callbackDialogCorrecto;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, 0, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_correcto");
        eventosDialogFragment.setCancelable(false);


    }

    /**
     * Generando diálogo para cerrar dialogo y realizar acción
     */
    public interface CallbackDialogCloseDialog {
        void onClickEventAfterCloseDialog();
    }

//    /**
//     * Generando dialogo para redireccionar Login
//     */
//     public interface CallbackDialogRelogin{
//         void onClickBtnReIntentarOption();
//         void onClickReloginOption();
//     }
//
//     public interface CallbackDialogUpdateApplication{
//         void onClickActualizarApp();
//     }

    public void showDialogCorrecto(String titulo, String descripcion, String lblOpcion, CallbackDialogCorrecto callbackDialogCorrecto, CallbackDialogCloseDialog callbackDialogCloseDialog) {
        this.callbackDialogCorrecto = callbackDialogCorrecto;
        this.callbackDialogCloseDialog = callbackDialogCloseDialog;
        this.eventDialogAfterClose = true;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, 0, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_correcto");
        eventosDialogFragment.setCancelable(false);
    }

    /**
     * Generando diálogo para mostrar mensaje correcto con imagen personalizada
     */
    public void showDialogCorrecto(String titulo, String descripcion, String lblOpcion, int iconoDialog, CallbackDialogCorrecto callbackDialogCorrecto) {
        this.callbackDialogCorrecto = callbackDialogCorrecto;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, iconoDialog, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_correcto");
        eventosDialogFragment.setCancelable(false);
    }

    /**
     * Generando diálogo para mostrar mensaje correcto con imagen personalizada no permite cerrar dialogo
     */
    public void showDialogCorrectoNoCloseDialog(String titulo, String descripcion, String lblOpcion, int iconoDialog, CallbackDialogCorrecto callbackDialogCorrecto) {
        this.callbackDialogCorrecto = callbackDialogCorrecto;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, iconoDialog, true, true);
        eventosDialogFragment.show(fm, "fragment_dialog_correcto");
        eventosDialogFragment.setCancelable(false);
    }

    /**
     * Dialogo sin opcion inferior
     */
    public void showDialogCorrecto(String titulo, String descripcion) {
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, "", 0, false, true);
        eventosDialogFragment.show(fm, "fragment_dialog_correcto");
        eventosDialogFragment.setCancelable(false);
    }

    /**
     * Generando diálogo para el fin de sesion
     */
    public void showDialogEndSession(String descripcion, String lblOpcion, CallbackDialogCorrecto callbackDialogEndSession) {
        this.callbackDialogCorrecto = callbackDialogEndSession;
        //FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, getResources().getString(R.string.lbl_sesion_finalizada), descripcion, lblOpcion, R.drawable.ic_error_dialog, true, false);
        //eventosDialogFragment.show(fm, "fragment_dialog_end_session");
        eventosDialogFragment.setCancelable(false);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(eventosDialogFragment, "fargment_dialog_end_session");
        ft.commitAllowingStateLoss();
    }

    public void showDialogErrorOption(String titulo, String descripcion, String lblOpcion, CallbackDialogCorrecto callbackDialogEndSession) {
        this.callbackDialogCorrecto = callbackDialogEndSession;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, R.drawable.ic_error_dialog, true, true);
        eventosDialogFragment.show(fm, "fragment_dialog_error_option");
        eventosDialogFragment.setCancelable(false);
    }

//    public void showDialogErrorLoginOption(String titulo, String descripcion, String lblOpcion,CallbackDialogRelogin callbackDialogRelogin){
//        this.callbackDialogRelogin = callbackDialogRelogin;
//        FragmentManager fm = getSupportFragmentManager();
//        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, R.drawable.ic_error_dialog, true, true);
//        eventosDialogFragment.show(fm, "fragment_dialog_error_option");
//        eventosDialogFragment.setCancelable(false);
//    }


    public void showDialogWarningOption(String titulo, String descripcion, String lblOpcion, CallbackDialogCorrecto callbackDialogEndSession) {
        this.callbackDialogCorrecto = callbackDialogEndSession;
        FragmentManager fm = getSupportFragmentManager();
        eventosDialogFragment = EventosDialogFragment.newInstance(0, false, false, titulo, descripcion, lblOpcion, R.drawable.ic_incidents_operat, true, true);
        eventosDialogFragment.show(fm, "fragment_dialog_error_option");
        eventosDialogFragment.setCancelable(true);
    }

    /**
     * Callbacks pertenecientes a EventosDialogFragment
     */
    @Override
    public void onClickCloseDialogEventos() {
        if (eventosDialogFragment != null) {
            if (!eventDialogAfterClose) {
                eventosDialogFragment.dismiss();
                eventosDialogFragment = null;
            } else {
                callbackDialogCloseDialog.onClickEventAfterCloseDialog();
            }
        }
    }

    @Override
    public void onClickDialogBtnRegistro() {
        callbackDialogRegistro.onClickBtnRegistro();
        eventosDialogFragment.dismiss();
    }

    @Override
    public void onClickOptionDialogIntentarNuevamente() {
        callbackDialogRegistro.onClickOptionIntentarNuevamente();
        eventosDialogFragment.dismiss();
    }

    @Override
    public void onClickOptionDialog() {
        callbackDialogCorrecto.onClickBtnOption();
        eventosDialogFragment.dismiss();
    }

    @Override
    public void onClickCloseDialogEndSession() {
        callbackDialogCorrecto.onClickBtnOption();
        eventosDialogFragment.dismiss();
    }

    @Override
    public void onClickOptionDialogError() {
        eventosDialogFragment.dismiss();
    }

    public void runAnimationRecyclerView(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void onClickButtonUpdateAppDialog() {
        callbackDialogUpdateApp.onClickButtonUpdateAppDialog();
        udpateAppDialogFragment.dismiss();
    }

    @Override
    public void onClickContinueUpdateAppDialog() {
        callbackDialogUpdateApp.onClickContinueUpdateAppDialog();
        udpateAppDialogFragment.dismiss();
    }

    /**
     * Anadiedo permisos del dispositivo
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        List<String> lasPermissionPending = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                lasPermissionPending.add(permissions[i]);
            }
        }
        if (lasPermissionPending.isEmpty()) {
            //if (Navigator.Type.values()[requestCode].equals(Navigator.Type.LOGIN)) {
            //    finish();
            //}
            Navigator.Type.values()[requestCode].go(BaseActivity.this, navigator);
        } else {
            showDialogCorrecto("", "Debe aceptar los permisos para continuar", "Aceptar", new CallbackDialogCorrecto() {
                @Override
                public void onClickBtnOption() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return;
                    navigator.navigateToPermission(BaseActivity.this);//requestPermissions(permissions, requestCode);

                }
            });
        }
    }


    /**
     * SESION ACTIVA DEL USUARIO EN EL APLICATIVO
     */

    public void onSessionExpired(String psMessage) {
        if (eventosDialogFragment == null) {
            showDialogEndSession(psMessage, getResources().getString(R.string.lbl_volver_a_ingresar), new CallbackDialogCorrecto() {
                @Override
                public void onClickBtnOption() {
                    navigator.navigateToSplash(BaseActivity.this);
                }
            });
        }

        //if (goMaterialDialogSession == null) {
        //    goMaterialDialogSession = DialogView.create(BaseActivity.this)
        //            .showBasicDialog(null,
        //                    psMessage,
        //                    null,
        //                    poDialog -> {
        //                        poDialog.dismiss();
        //                        goToLogin();
        //                    },
        //                    false);
        //} else if (!goMaterialDialogSession.isShowing()) {
        //    goMaterialDialogSession.show();
        //}
    }
}
