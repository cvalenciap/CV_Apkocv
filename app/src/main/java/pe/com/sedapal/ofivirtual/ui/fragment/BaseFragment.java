/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package pe.com.sedapal.ofivirtual.ui.fragment;

import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.ui.activity.BaseActivity;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 * <p>
 * Created by jsaenz on 11/01/2017.
 */
public abstract class BaseFragment extends Fragment{
    private static final String TAG = BaseFragment.class.getSimpleName();

    /***
     * Almacenara en una sola variable la configuracion de la API google maps
     * para que este peuda ser reutilizado en otros fragments
     */

    //public static GoogleApiClient goGoogleApiClient;

    /**
     * Shows a {@link Toast} message.
     *
     * @param psMessage An string representing a message to be shown.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    protected void showToastMessage(String psMessage) {
        Toast.makeText(getActivity(), psMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     *
     * @param poComponentType {@link Class<C>}
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> poComponentType) {
        return poComponentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    /**
     * Get a context to activity.
     *
     * @return Context {@link Context}
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public Context getContext() {
        return getActivity();
    }


    public void showLoading(String message) {
        ((BaseActivity) getActivity()).showLoading(message);
    }

    public void hideLoading() {
        ((BaseActivity) getActivity()).hideLoading();
    }

    public void hideKeyboard() {
        ((BaseActivity) getActivity()).hideKeyboard();
    }

    public void runAnimationRecyclerView(RecyclerView recyclerView){
        ((BaseActivity) getActivity()).runAnimationRecyclerView(recyclerView);
    }

    public void showToastError(String mensaje){
        ((BaseActivity)getActivity()).showToastError(mensaje);
    }

    /**
     * Generando diálogo para mostrar error
     */

    public void showDialogError(String mensaje) {
        ((BaseActivity)getActivity()).showDialogError(mensaje);
    }

    public void onSessionExpired(String psMessage) {
        ((BaseActivity) getActivity()).onSessionExpired(psMessage);
    }

    public void showDialogErrorOption(String titulo, String psTxtErrorOption, String psDescripcion, CallbackDialogOption callbackDialogOption){
        ((BaseActivity) getActivity()).showDialogErrorOption(titulo,psTxtErrorOption, psDescripcion, new BaseActivity.CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                callbackDialogOption.onClickBtnOption();

            }
        });
    }

    public void showDialogWarningOption(String titulo, String psTxtErrorOption, String psDescripcion, CallbackDialogOption callbackDialogOption){
        ((BaseActivity) getActivity()).showDialogWarningOption(titulo,psTxtErrorOption, psDescripcion, new BaseActivity.CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                callbackDialogOption.onClickBtnOption();

            }
        });
    }

    /**
     * Generando diálogo para mostrar mensaje correcto con imagen personalizada
     */
    public void showDialogCorrecto(String titulo, String descripcion, String lblOpcion, int iconoDialog, BaseActivity.CallbackDialogCorrecto callbackDialogCorrecto){
        ((BaseActivity)getActivity()).showDialogCorrecto(titulo, descripcion, lblOpcion, iconoDialog, new BaseActivity.CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                callbackDialogCorrecto.onClickBtnOption();
            }
        });
    }

    public interface CallbackDialogOption{
        void onClickBtnOption();
    }

}
