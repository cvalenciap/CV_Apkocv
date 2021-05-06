package pe.com.sedapal.ofivirtual.presenter.user;

import io.reactivex.annotations.NonNull;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.RegisterUserUseCase;
import pe.com.sedapal.ofivirtual.model.RegisterUserModel;
import pe.com.sedapal.ofivirtual.model.mapper.RegisterUserMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.RegisterUserView;
import pe.com.sedapal.ofivirtual.util.Constants;

import javax.inject.Inject;


public class RegisterUserPresenter implements Presenter {
    private RegisterUserView goRegisterUserView;
    private final RegisterUserUseCase goRegisterUserUseCase;
    private final RegisterUserMapper goRegisterUserMapper;

    @Inject
    public RegisterUserPresenter(RegisterUserUseCase poRegisterUserUseCase, RegisterUserMapper poRegisterUserMapper) {
        this.goRegisterUserUseCase = poRegisterUserUseCase;
        this.goRegisterUserMapper = poRegisterUserMapper;
    }

    public void setView(@NonNull RegisterUserView goRegisterUserView) {
        this.goRegisterUserView = goRegisterUserView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.goRegisterUserView = null;
        this.goRegisterUserUseCase.dispose();
    }

    public void initialize(long nisRad, long referenciaCobro, int idTipoDoc, String numDoc, String apellido1, String apellido2, String nombres, String correo, long telefono1, int aceptaTerminos, int aceptaNotificaciones, String clave) {
        this.hideViewRetry();
        this.showViewLoading();

        RegisterUserModel poRegisterUserModel = new RegisterUserModel(nisRad, referenciaCobro, idTipoDoc, numDoc, apellido1, apellido2, nombres, correo, telefono1, aceptaTerminos, aceptaNotificaciones, clave);
        this.doRegister(poRegisterUserModel);
    }

    private void showViewLoading() {
        this.goRegisterUserView.showLoading(this.goRegisterUserView.context().getString(R.string.lbl_progressdialog_registro_usuario));
    }

    private void hideViewLoading() {
        this.goRegisterUserView.hideLoading();
    }

    private void showViewRetry() {
        this.goRegisterUserView.showRetry();
    }

    private void hideViewRetry() {
        this.goRegisterUserView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goRegisterUserView.showError(psErrorMessage);
    }

    private void showRegisterUserSuccessToView(String poMessage) {
        this.goRegisterUserView.showSucessRegisterUser(poMessage);
    }

    private void doRegister(RegisterUserModel poRegisterUserModel) {
        RegisterUserUseCase.Params loParams = RegisterUserUseCase.Params.forRegister(goRegisterUserMapper.transform(poRegisterUserModel));
        this.goRegisterUserUseCase.execute(new RegisterUserOserver(), loParams);
    }

    private final class RegisterUserOserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            RegisterUserPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            RegisterUserPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goRegisterUserView.onSessionExpired(poException.getMessage());
            }else {
                RegisterUserPresenter.this.showErrorMessage(poException.getMessage());
                RegisterUserPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(String poData) {
            RegisterUserPresenter.this.showRegisterUserSuccessToView(poData);
        }
    }
}
