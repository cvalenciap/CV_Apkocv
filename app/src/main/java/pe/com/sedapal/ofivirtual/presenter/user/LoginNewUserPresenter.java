package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginNewUserUseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginUseCase;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.mapper.LoginNewUserMapper;
import pe.com.sedapal.ofivirtual.model.mapper.UserModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginNewUserView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.util.Constants;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class LoginNewUserPresenter implements Presenter {

    private LoginNewUserView goLoginNewUserView;

    private final LoginNewUserUseCase goLoginNewUserUseCase;
    private final LoginNewUserMapper goLoginNewUserMapper;

    /**
     * Constructs a {@link LoginNewUserPresenter}.
     *
     * @param poLoginNewUserUseCase    {@link LoginUseCase}.
     * @param poLoginNewUserMapper {@link UserModelMapper}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public LoginNewUserPresenter(LoginNewUserUseCase poLoginNewUserUseCase, LoginNewUserMapper poLoginNewUserMapper) {
        this.goLoginNewUserUseCase = poLoginNewUserUseCase;
        this.goLoginNewUserMapper = poLoginNewUserMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poLoginNewUserView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull LoginNewUserView poLoginNewUserView) {
        this.goLoginNewUserView = poLoginNewUserView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goLoginNewUserUseCase.dispose();
        this.goLoginNewUserView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void initialize(String psEmail, String psPassword) {
        this.login(psEmail, psPassword);
    }


    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void login(String psEmail, String psPassword) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doLogin(psEmail, psPassword);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewLoading() {
        //this.goLoginNewUserView.showLoading(this.goLoginNewUserView.context().getString(R.string.lbl_progressdialog_validando_usuario));
        this.goLoginNewUserView.showProgessDialog();

    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewLoading() {
        //this.goLoginNewUserView.hideLoading();
        this.goLoginNewUserView.hideProgessDialog();
    }

    private void hideViewLoadingError(){
        this.goLoginNewUserView.hideProgessDialogError();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewRetry() {
        this.goLoginNewUserView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewRetry() {
        this.goLoginNewUserView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goLoginNewUserView.errorLoginUser(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poLoginNewUser {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void showLoginSuccessToView(LoginNewUser poLoginNewUser) {
        LoginNewUserModel loUserModel = goLoginNewUserMapper.transform(poLoginNewUser);
        if(poLoginNewUser.getFlagRespuesta().equalsIgnoreCase(ValidationUtil.FLAG_RESPUESTA_DIALOGO_REGISTRAR)){
            this.goLoginNewUserView.showLoginNotRegisterEmail(poLoginNewUser.getDescRespuesta());
        }else {
            if (loUserModel.getPendingConfirmRegister()) {
                this.goLoginNewUserView.isPendingConfirmRegister(loUserModel);
            } else {
                this.goLoginNewUserView.showLoginNewUserSuccess(loUserModel);
            }
        }
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doLogin(String psEmail, String psPassword) {
        LoginNewUserUseCase.Params loParams = LoginNewUserUseCase.Params.forUser(psEmail, psPassword);
        this.goLoginNewUserUseCase.execute(new LoginNewUserObserver(), loParams);
    }

    private final class LoginNewUserObserver extends DefaultObserver<LoginNewUser> {

        @Override
        public void onComplete() {
            LoginNewUserPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            LoginNewUserPresenter.this.hideViewLoadingError();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goLoginNewUserView.onSessionExpired(poException.getMessage());
            }else {
                LoginNewUserPresenter.this.showErrorMessage(poException.getMessage());
                LoginNewUserPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(LoginNewUser poData) {
            LoginNewUserPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
