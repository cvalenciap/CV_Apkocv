package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.domain.entity.ForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.ForgetPasswordSupplyUseCase;
import pe.com.sedapal.ofivirtual.model.ReqForgetPasswordSupplyModel;
import pe.com.sedapal.ofivirtual.model.mapper.ForgetPasswordSupplyModelMapper;
import pe.com.sedapal.ofivirtual.model.mapper.ReqForgetPasswordSupplyModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ForgetPasswordSupplyView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class ForgetPasswordSupplyPresenter implements Presenter {

    private ForgetPasswordSupplyView goForgetPasswordSupplyView;

    private final ForgetPasswordSupplyUseCase goForgetPasswordSupplyUseCase;
    private final ForgetPasswordSupplyModelMapper goForgetPasswordSupplyModelMapper;
    private final ReqForgetPasswordSupplyModelMapper goReqForgetPasswordSupplyModelMapper;

    /**
     * Constructs a {@link ForgetPasswordSupplyPresenter}.
     *
     * @param poForgetPasswordSupplyUseCase
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ForgetPasswordSupplyPresenter(ForgetPasswordSupplyUseCase poForgetPasswordSupplyUseCase, ReqForgetPasswordSupplyModelMapper poReqForgetPasswordSupplyModelMapper, ForgetPasswordSupplyModelMapper poForgetPasswordSupplyModelMapper) {
        this.goForgetPasswordSupplyUseCase = poForgetPasswordSupplyUseCase;
        this.goReqForgetPasswordSupplyModelMapper = poReqForgetPasswordSupplyModelMapper;
        this.goForgetPasswordSupplyModelMapper = poForgetPasswordSupplyModelMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poLoginNewUserView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull ForgetPasswordSupplyView poLoginNewUserView) {
        this.goForgetPasswordSupplyView = poLoginNewUserView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goForgetPasswordSupplyUseCase.dispose();
        this.goForgetPasswordSupplyView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void initialize(int nis, int idTypeUser, int idQuestion, String response) {
        this.hideViewRetry();
        this.showViewLoading();

        ReqForgetPasswordSupplyModel poReqForgetPasswordSupplyModel = new ReqForgetPasswordSupplyModel();
        poReqForgetPasswordSupplyModel.setNis(nis);
        poReqForgetPasswordSupplyModel.setIdTipoUsuario(idTypeUser);
        poReqForgetPasswordSupplyModel.setIdPregunta(idQuestion);
        poReqForgetPasswordSupplyModel.setRespuesta(response);

        this.login(poReqForgetPasswordSupplyModel);
    }


    /**
     * The user login.
     *
     * @param psReqForgetPasswordSupplyModel
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void login(ReqForgetPasswordSupplyModel psReqForgetPasswordSupplyModel) {
        this.doValidate(psReqForgetPasswordSupplyModel);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewLoading() {
        this.goForgetPasswordSupplyView.showLoading(this.goForgetPasswordSupplyView.context().getString(R.string.lbl_progressdialog_recuperacion_contraseña));
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewLoading() {
        this.goForgetPasswordSupplyView.hideLoading();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewRetry() {
        this.goForgetPasswordSupplyView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewRetry() {
        this.goForgetPasswordSupplyView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goForgetPasswordSupplyView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poForgetPasswordSupply {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void showForgetPasswordSupplySuccessToView(ForgetPasswordSupply poForgetPasswordSupply) {
        this.goForgetPasswordSupplyView.showForgetPasswordSupplySuccess(goForgetPasswordSupplyModelMapper.transform(poForgetPasswordSupply));
    }

    /**
     * Execute sign in.
     *
     * @param psReqForgetPasswordSupplyModel
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doValidate(ReqForgetPasswordSupplyModel psReqForgetPasswordSupplyModel) {
        ForgetPasswordSupplyUseCase.Params loPrams = ForgetPasswordSupplyUseCase.Params.forUser(goReqForgetPasswordSupplyModelMapper.transform(psReqForgetPasswordSupplyModel));
        this.goForgetPasswordSupplyUseCase.execute(new ForgetPasswordSupplyObserver(), loPrams);
    }

    private final class ForgetPasswordSupplyObserver extends DefaultObserver<ForgetPasswordSupply> {

        @Override
        public void onComplete() {
            ForgetPasswordSupplyPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ForgetPasswordSupplyPresenter.this.hideViewLoading();
            ForgetPasswordSupplyPresenter.this.showErrorMessage(poException.getMessage());
            ForgetPasswordSupplyPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(ForgetPasswordSupply poData) {
            ForgetPasswordSupplyPresenter.this.showForgetPasswordSupplySuccessToView(poData);
        }
    }
}
