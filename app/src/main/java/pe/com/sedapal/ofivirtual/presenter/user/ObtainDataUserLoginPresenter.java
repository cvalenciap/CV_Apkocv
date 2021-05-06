package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.ObtainDataUserLogin;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetDataUserLoginUseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginUseCase;
import pe.com.sedapal.ofivirtual.model.ObtainDataUserLoginModel;
import pe.com.sedapal.ofivirtual.model.mapper.ObtainDataUserLoginMapper;
import pe.com.sedapal.ofivirtual.model.mapper.UserModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.presenter.view.ObtainDataUserLoginView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 27/03/2019.
 */
public class ObtainDataUserLoginPresenter implements Presenter {

    private ObtainDataUserLoginView goObtainDataUserLoginView;

    private final GetDataUserLoginUseCase goGetDataUserLoginUseCase;
    private final ObtainDataUserLoginMapper goObtainDataUserLoginMapper;

    /**
     * Constructs a {@link ObtainDataUserLoginPresenter}.
     *
     * @param poObtainDataUserLoginUseCase    {@link LoginUseCase}.
     * @param poObtainDataUserLoginMapper {@link UserModelMapper}.
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    @Inject
    public ObtainDataUserLoginPresenter(GetDataUserLoginUseCase poObtainDataUserLoginUseCase, ObtainDataUserLoginMapper poObtainDataUserLoginMapper) {
        this.goGetDataUserLoginUseCase = poObtainDataUserLoginUseCase;
        this.goObtainDataUserLoginMapper = poObtainDataUserLoginMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poObtainDataUserLoginView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    public void setView(@NonNull ObtainDataUserLoginView poObtainDataUserLoginView) {
        this.goObtainDataUserLoginView = poObtainDataUserLoginView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetDataUserLoginUseCase.dispose();
        this.goObtainDataUserLoginView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    public void initialize(long psIdCliente) {
        this.login(psIdCliente);
    }


    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    private void login(long idCliente) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doObtain(idCliente);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    private void showViewLoading() {
        this.goObtainDataUserLoginView.showProgessDialogObtainDataUser();

    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    private void hideViewLoading() {
        this.goObtainDataUserLoginView.hideProgessDialogObtainDataUser();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    private void showViewRetry() {
        this.goObtainDataUserLoginView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    private void hideViewRetry() {
        this.goObtainDataUserLoginView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goObtainDataUserLoginView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poObtainDataUserLogin {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void showLoginSuccessToView(ObtainDataUserLogin poObtainDataUserLogin) {
        ObtainDataUserLoginModel loUserModel = goObtainDataUserLoginMapper.transform(poObtainDataUserLogin);
        this.goObtainDataUserLoginView.showSucessObtainDatauser(loUserModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain(long idCliente) {
        GetDataUserLoginUseCase.Params loParams = GetDataUserLoginUseCase.Params.forObtain(idCliente);
        this.goGetDataUserLoginUseCase.execute(new ObtainDataUserLoginObserver(), loParams);
    }

    private final class ObtainDataUserLoginObserver extends DefaultObserver<ObtainDataUserLogin> {

        @Override
        public void onComplete() {
            ObtainDataUserLoginPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ObtainDataUserLoginPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goObtainDataUserLoginView.onSessionExpired(poException.getMessage());
            }else {
                ObtainDataUserLoginPresenter.this.showErrorMessage(poException.getMessage());
                ObtainDataUserLoginPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(ObtainDataUserLogin poData) {
            ObtainDataUserLoginPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
