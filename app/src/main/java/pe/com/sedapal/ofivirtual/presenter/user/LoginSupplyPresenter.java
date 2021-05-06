package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginSupplyUseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginUseCase;
import pe.com.sedapal.ofivirtual.model.LoginSupplyModel;
import pe.com.sedapal.ofivirtual.model.mapper.LoginSupplyMapper;
import pe.com.sedapal.ofivirtual.model.mapper.UserModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginSupplyView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class LoginSupplyPresenter implements Presenter {

    private LoginSupplyView goLoginSupplyView;

    private final LoginSupplyUseCase goLoginSupplyUseCase;
    private final LoginSupplyMapper goLoginSupplyMapper;

    /**
     * Constructs a {@link LoginSupplyPresenter}.
     *
     * @param poLoginSupplyUseCase    {@link LoginUseCase}.
     * @param poLoginSupplyMapper {@link UserModelMapper}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public LoginSupplyPresenter(LoginSupplyUseCase poLoginSupplyUseCase, LoginSupplyMapper poLoginSupplyMapper) {
        this.goLoginSupplyUseCase = poLoginSupplyUseCase;
        this.goLoginSupplyMapper = poLoginSupplyMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poLoginSupplyView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull LoginSupplyView poLoginSupplyView) {
        this.goLoginSupplyView = poLoginSupplyView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goLoginSupplyUseCase.dispose();
        this.goLoginSupplyView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void initialize(String psNisRad, String psPassword) {
        this.login(Long.parseLong(psNisRad), psPassword);
    }


    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void login(long psNisRad, String psPassword) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doLogin(psNisRad, psPassword);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewLoading() {
        this.goLoginSupplyView.showProgessDialogSupply();

    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewLoading() {
        this.goLoginSupplyView.hideProgessDialogSupply();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewRetry() {
        this.goLoginSupplyView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewRetry() {
        this.goLoginSupplyView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goLoginSupplyView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poSupply {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void showLoginSuccessToView(LoginSupply poSupply) {
        LoginSupplyModel loUserModel = goLoginSupplyMapper.transform(poSupply);
        if(poSupply.getFlagRespuesta().equalsIgnoreCase(ValidationUtil.FLAG_RESPUESTA_DIALOGO_REGISTRAR)){
            this.goLoginSupplyView.showLoginIncorrectSupply(poSupply.getDescRespuesta());
        }else {
            this.goLoginSupplyView.showLoginSupplySuccess(loUserModel);
        }
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doLogin(long nisRad, String psPassword) {
        LoginSupplyUseCase.Params loParams = LoginSupplyUseCase.Params.forUser(nisRad, psPassword);
        this.goLoginSupplyUseCase.execute(new LoginSupplyObserver(), loParams);
    }

    private final class LoginSupplyObserver extends DefaultObserver<LoginSupply> {

        @Override
        public void onComplete() {
            LoginSupplyPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            LoginSupplyPresenter.this.hideViewLoading();
            LoginSupplyPresenter.this.showErrorMessage(poException.getMessage());
            LoginSupplyPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(LoginSupply poData) {
            LoginSupplyPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
