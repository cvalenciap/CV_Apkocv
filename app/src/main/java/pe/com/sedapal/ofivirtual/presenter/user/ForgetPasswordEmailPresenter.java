package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.ForgetPasswordEmailUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ForgetPasswordEmailView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class ForgetPasswordEmailPresenter implements Presenter {

    private ForgetPasswordEmailView goForgetPasswordEmailView;

    private final ForgetPasswordEmailUseCase goForgetPasswordEmailUseCase;

    /**
     * Constructs a {@link ForgetPasswordEmailPresenter}.
     *
     * @param poForgetPasswordEmailUseCase
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ForgetPasswordEmailPresenter(ForgetPasswordEmailUseCase poForgetPasswordEmailUseCase) {
        this.goForgetPasswordEmailUseCase = poForgetPasswordEmailUseCase;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poLoginNewUserView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull ForgetPasswordEmailView poLoginNewUserView) {
        this.goForgetPasswordEmailView = poLoginNewUserView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goForgetPasswordEmailUseCase.dispose();
        this.goForgetPasswordEmailView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void initialize(String psEmail) {
        this.login(psEmail);
    }


    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void login(String psEmail) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doValidate(psEmail);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewLoading() {
        this.goForgetPasswordEmailView.showLoading(this.goForgetPasswordEmailView.context().getString(R.string.lbl_progressdialog_recuperacion_contraseña));
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewLoading() {
        this.goForgetPasswordEmailView.hideLoading();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewRetry() {
        this.goForgetPasswordEmailView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewRetry() {
        this.goForgetPasswordEmailView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goForgetPasswordEmailView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poMessage {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void showLoginSuccessToView(String poMessage) {
        this.goForgetPasswordEmailView.showForgetPasswordEmailSuccess(poMessage);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doValidate(String psEmail) {
        ForgetPasswordEmailUseCase.Params loPrams = ForgetPasswordEmailUseCase.Params.forUser(psEmail);
        this.goForgetPasswordEmailUseCase.execute(new ForgetPasswordEmailObserver(), loPrams);
    }

    private final class ForgetPasswordEmailObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            ForgetPasswordEmailPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ForgetPasswordEmailPresenter.this.hideViewLoading();
            ForgetPasswordEmailPresenter.this.showErrorMessage(poException.getMessage());
            ForgetPasswordEmailPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String poData) {
            ForgetPasswordEmailPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
