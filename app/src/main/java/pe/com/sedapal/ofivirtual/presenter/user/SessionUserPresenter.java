package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.domain.entity.SessionUser;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.DeleteSessionUserUseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetSessionUserUseCase;
import pe.com.sedapal.ofivirtual.model.SessionUserModel;
import pe.com.sedapal.ofivirtual.model.mapper.SessionUserMapper;
import pe.com.sedapal.ofivirtual.model.mapper.UserModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.presenter.view.SessionUserView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class SessionUserPresenter implements Presenter {

    private SessionUserView goSessionUserView;
    private final GetSessionUserUseCase goGetSessionUserUseCase;
    private final DeleteSessionUserUseCase goDeleteSessionUserUseCase;
    private final SessionUserMapper goSessionUserMapper;

    /**
     * Constructs a {@link SessionUserPresenter}.
     *
     * @param poSessionUserMapper {@link UserModelMapper}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public SessionUserPresenter(GetSessionUserUseCase poGetSessionUserUseCase, DeleteSessionUserUseCase poDeleteSessionUserUseCase, SessionUserMapper poSessionUserMapper) {
        this.goGetSessionUserUseCase = poGetSessionUserUseCase;
        this.goDeleteSessionUserUseCase = poDeleteSessionUserUseCase;
        this.goSessionUserMapper = poSessionUserMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poSessionUserView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull SessionUserView poSessionUserView) {
        this.goSessionUserView = poSessionUserView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetSessionUserUseCase.dispose();
        this.goDeleteSessionUserUseCase.dispose();
        this.goSessionUserView = null;
    }

    /**
     * OBTENER LA SESION DEL USUARIO
     */
    public void initializeObtainSession() {
        this.session();
    }

    private void session() {
        this.hideViewRetry();
        this.showViewLoading();
        this.doGetSession();
    }

    private void showViewLoading() {

    }

    private void hideViewLoading() {
        this.goSessionUserView.hideLoadingProgress();
    }


    private void showViewRetry() {
        this.goSessionUserView.showRetry();
    }

    private void hideViewRetry() {
        this.goSessionUserView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goSessionUserView.showError(psErrorMessage);
    }

    private void showGetSessionToView(SessionUser poSessionUser) {
        SessionUserModel loUserModel = goSessionUserMapper.transform(poSessionUser);
        this.goSessionUserView.showSessionUserObtain(loUserModel);
    }

    private void doGetSession() {
        this.goGetSessionUserUseCase.execute(new GetSessionObserver(),null);
    }

    private final class GetSessionObserver extends DefaultObserver<SessionUser> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable poException) {
            SessionUserPresenter.this.hideViewLoading();
            SessionUserPresenter.this.showErrorMessage(poException.getMessage());
            SessionUserPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(SessionUser poData) {
            SessionUserPresenter.this.showGetSessionToView(poData);
        }
    }


    /**
     * LIMPIAR LA SESION DEL USUARIO
     */
    public void initializeDeleteSession() {
        this.deleteSession();
    }

    private void deleteSession() {
        this.hideViewRetryDS();
        this.showViewLoadingDS();
        this.doDeleteSession();
    }

    private void showViewLoadingDS() {
        this.goSessionUserView.showLoading(this.goSessionUserView.context().getString(R.string.lbl_cerrando_sesion));
    }

    private void hideViewLoadingDS() {
        this.goSessionUserView.hideLoadingProgress();
    }

    private void showViewRetryDS() {
        this.goSessionUserView.showRetry();
    }

    private void hideViewRetryDS() {
        this.goSessionUserView.hideRetry();
    }

    private void showErrorMessageDS(String psErrorMessage) {
        this.goSessionUserView.showError(psErrorMessage);
    }

    private void showDeleteSession(boolean poResp) {
        this.goSessionUserView.showDeleteSession(poResp);
    }

    private void doDeleteSession() {
        this.goDeleteSessionUserUseCase.execute(new DeleteSessionObserver(),null);
    }

    private final class DeleteSessionObserver extends DefaultObserver<Boolean> {

        @Override
        public void onComplete() {
            //SessionUserPresenter.this.hideViewLoadingDS();
        }

        @Override
        public void onError(Throwable poException) {
            SessionUserPresenter.this.hideViewLoadingDS();
            SessionUserPresenter.this.showErrorMessageDS(poException.getMessage());
            SessionUserPresenter.this.showViewRetryDS();
        }

        @Override
        public void onNext(Boolean poRespuesta) {
            SessionUserPresenter.this.hideViewLoadingDS();
            SessionUserPresenter.this.showDeleteSession(poRespuesta);
        }
    }

    /**
     * Delete session not loading
     */

    public void initializeDeleteSessionNotLoad() {
        this.deleteSessionNotLoading();
    }

    private void deleteSessionNotLoading() {
        this.hideViewRetryDS();
        this.doDeleteSessionNotLoading();
    }

    private void showDeleteSessionNotLoading(boolean poResp) {
    }

    private void doDeleteSessionNotLoading() {
        this.goDeleteSessionUserUseCase.execute(new DeleteSessionNotLoadingObserver(), null);
    }

    private final class DeleteSessionNotLoadingObserver extends DefaultObserver<Boolean> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable poException) {
            SessionUserPresenter.this.showErrorMessage(poException.getMessage());
            SessionUserPresenter.this.showViewRetryDS();
        }

        @Override
        public void onNext(Boolean poRespuesta) {
            SessionUserPresenter.this.showDeleteSessionNotLoading(poRespuesta);
        }
    }
}
