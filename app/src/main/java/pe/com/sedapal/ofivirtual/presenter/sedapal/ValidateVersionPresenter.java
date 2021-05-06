package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.ValidateVersion;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.ValidateVersionUseCase;
import pe.com.sedapal.ofivirtual.model.mapper.ValidateVersionModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ValidateVersionView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 29/07/20
 */
public class ValidateVersionPresenter implements Presenter {

    private ValidateVersionView goValidateVersionView;
    private final ValidateVersionUseCase goValidateVersionUseCase;
    private final ValidateVersionModelMapper goValidateVersionModelMapper;


    @Inject
    public ValidateVersionPresenter(ValidateVersionUseCase poValidateVersionUseCase, ValidateVersionModelMapper poValidateVersionModelMapper) {
        this.goValidateVersionUseCase = poValidateVersionUseCase;
        this.goValidateVersionModelMapper = poValidateVersionModelMapper;
    }

    /**
     * Set View {@link ValidateVersionView} to listener on Fragment or Activity
     *
     * @param poValidateVersionView {@link ValidateVersionView}.
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    public void setView(@NonNull ValidateVersionView poValidateVersionView) {
        this.goValidateVersionView = poValidateVersionView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goValidateVersionUseCase.dispose();
        this.goValidateVersionView = null;
    }

    /**
     * Initialize validateVersion the presenter by validateVersion in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    public void initialize(String psFlagChannel, String psVersionMovil) {
        this.validateVersion(psFlagChannel, psVersionMovil);
    }


    /**
     * The user validateVersion.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    private void validateVersion(String psFlagChannel, String psVersionMovil) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doValidateVersion(psFlagChannel, psVersionMovil);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    private void showViewLoading() {
        //this.goValidateVersionView.showLoadingValidateVersion();
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    private void hideProgessDialogError() {
        this.goValidateVersionView.hideProgessDialogError();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    private void showViewRetry() {
        this.goValidateVersionView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    private void hideViewRetry() {
        this.goValidateVersionView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/07/2020
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goValidateVersionView.showError(psErrorMessage);
    }

    /**
     * Show result validateVersion
     *
     * @param poData
     * @author jsaenz
     * @version 1.0
     * @since 29/12/2018
     */
    private void showValidateVersionSuccessToView(ValidateVersion poData) {
        this.goValidateVersionView.showValidateVersionSuccess(goValidateVersionModelMapper.transform(poData));
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/12/2018
     */
    private void doValidateVersion(String psFlagChannel, String psVersionMovil) {
        ValidateVersionUseCase.Params params = ValidateVersionUseCase.Params.forValidate(psFlagChannel, psVersionMovil);
        this.goValidateVersionUseCase.execute(new ValidateVersionObserver(), params);
    }

    private final class ValidateVersionObserver extends DefaultObserver<ValidateVersion> {

        @Override
        public void onComplete() {
            //ValidateVersionPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ValidateVersionPresenter.this.hideProgessDialogError();
            ValidateVersionPresenter.this.showErrorMessage(poException.getMessage());
            ValidateVersionPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(ValidateVersion poData) {
            ValidateVersionPresenter.this.showValidateVersionSuccessToView(poData);
        }
    }
}
