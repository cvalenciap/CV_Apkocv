package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginUseCase;
import pe.com.sedapal.ofivirtual.model.UserModel;
import pe.com.sedapal.ofivirtual.model.mapper.UserModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class LoginPresenter implements Presenter {

    private LoginView goLoginView;

    private final LoginUseCase goLoginUseCase;
    private final UserModelMapper goUserModelMapper;

    /**
     * Constructs a {@link LoginPresenter}.
     *
     * @param poLoginUseCase    {@link LoginUseCase}.
     * @param poUserModelMapper {@link UserModelMapper}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public LoginPresenter(LoginUseCase poLoginUseCase, UserModelMapper poUserModelMapper) {
        this.goLoginUseCase = poLoginUseCase;
        this.goUserModelMapper = poUserModelMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poLoginView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull LoginView poLoginView) {
        this.goLoginView = poLoginView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goLoginUseCase.dispose();
        this.goLoginView = null;
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
        this.goLoginView.showLoading("");
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewLoading() {
        this.goLoginView.hideLoading();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewRetry() {
        this.goLoginView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewRetry() {
        this.goLoginView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goLoginView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poUser {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void showLoginSuccessToView(User poUser) {
        UserModel loUserModel = goUserModelMapper.transform(poUser);
        this.goLoginView.showLoginSuccess(loUserModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doLogin(String psEmail, String psPassword) {
        LoginUseCase.Params loParams = LoginUseCase.Params.forUser(psEmail, psPassword);
        this.goLoginUseCase.execute(new LoginObserver(), loParams);
    }

    private final class LoginObserver extends DefaultObserver<User> {

        @Override
        public void onComplete() {
            LoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            LoginPresenter.this.hideViewLoading();
            LoginPresenter.this.showErrorMessage(poException.getMessage());
            LoginPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(User poData) {
            LoginPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
