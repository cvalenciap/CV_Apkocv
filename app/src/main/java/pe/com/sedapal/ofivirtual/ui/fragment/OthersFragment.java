package pe.com.sedapal.ofivirtual.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.SessionUserModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.user.SessionUserPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.OthersFragmentView;
import pe.com.sedapal.ofivirtual.presenter.view.SessionUserView;
import pe.com.sedapal.ofivirtual.ui.activity.BaseActivity;

public class OthersFragment extends BaseFragment implements OthersFragmentView, SessionUserView {

    @BindView(R.id.crdActualizarMiPerfil)
    CardView crdActualizarMiPerfil;
    @BindView(R.id.crdCerrarSesion)
    CardView crdCerrarSesion;

    @Inject
    public Navigator navigator;

    @Inject
    SessionUserPresenter goSessionUserPresenter;

    private static final String TAG = OthersFragment.class.getSimpleName();
    private static final String BUNDLE_DATA_USER_LOGIN = "BUNDLE_DATA_USER_LOGIN";
    LoginNewUserModel goUsuarioLogueado;

    public OthersFragment() {
        // Required empty public constructor
    }

    public static OthersFragment newInstance(LoginNewUserModel poLoginNewUserModel) {
        OthersFragment fragment = new OthersFragment();
        Bundle args = new Bundle();
        if(poLoginNewUserModel == null){
            poLoginNewUserModel = new LoginNewUserModel();
        }
        args.putSerializable(BUNDLE_DATA_USER_LOGIN, poLoginNewUserModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CommercialOfficeComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View loView = inflater.inflate(R.layout.fragment_others, container, false);
        ButterKnife.bind(this, loView);
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.goSessionUserPresenter.setView(this);
        setUpView();
    }

    private void setUpView() {
        getBundlesBeginOpFrequent();
        eventControls();
    }

    private void getBundlesBeginOpFrequent() {
        goUsuarioLogueado = (LoginNewUserModel) getArguments().getSerializable(BUNDLE_DATA_USER_LOGIN);
    }

    public void eventControls(){

    }


    @OnClick(R.id.crdActualizarMiPerfil)
    public void goToUpdateProfileActivity(){
        navigator.navigateToUpdateDataUserActivity((Activity)getContext(), goUsuarioLogueado);
    }

    @OnClick(R.id.crdCerrarSesion)
    public void goToCloseSession(){
        initializeDeleteSession();
    }

    /**
     * CALL WEBSERVICES
     */

    public void initializeDeleteSession(){
        showDialogCorrecto("",getResources().getString(R.string.lbl_dialog_mensaje_cerrar_sesion), getResources().getString(R.string.lbl_dialog_cerrar_sesion) , R.drawable.ic_correct_dialog, new BaseActivity.CallbackDialogCorrecto() {
            @Override
            public void onClickBtnOption() {
                goSessionUserPresenter.initializeDeleteSession();
            }
        });
    }

    /**
     * WEBSERVICES RESPONSE
     * @param poSessionUserModel
     */
    @Override
    public void showSessionUserObtain(SessionUserModel poSessionUserModel) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDeleteSession(boolean poResponse) {
        /**
         * Se limpia la variable global la cual contiene todos los datos de sesion
         * del usuario
         */
        BaseActivity.goUsuarioLogueado = null;
        /**
         * Se dirige al splash el cual generara nuevamente el token
         */
        navigator.navigateToSplash((Activity)getContext());

    }

    @Override
    public void hideLoadingProgress() {

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
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.goSessionUserPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.goSessionUserPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.goSessionUserPresenter.destroy();
    }
}
