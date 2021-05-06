package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.ValidateInvoicePreviousUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ValidateInvoicePreviousView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */
public class ValidateInvoicePreviousPresenter implements Presenter {

    private ValidateInvoicePreviousView goValidateInvoicePreviousView;

    private final ValidateInvoicePreviousUseCase goValidateInvoicePreviousUseCase;

    @Inject
    public ValidateInvoicePreviousPresenter(ValidateInvoicePreviousUseCase poValidateInvoicePreviousUseCase) {
        this.goValidateInvoicePreviousUseCase = poValidateInvoicePreviousUseCase;
    }

    public void setView(@NonNull ValidateInvoicePreviousView poValidateInvoicePreviousView) {
        this.goValidateInvoicePreviousView = poValidateInvoicePreviousView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goValidateInvoicePreviousView = null;
        this.goValidateInvoicePreviousUseCase.dispose();
    }

    public void initialize(long nisRad, long recibo) {
        this.hideViewRetry();
        this.doObtain(nisRad,recibo);
    }

    private void showViewRetry() {
        this.goValidateInvoicePreviousView.showRetry();
    }

    private void hideViewRetry() {
        this.goValidateInvoicePreviousView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goValidateInvoicePreviousView.showErrorMessageValidateInvoicePrevious(psErrorMessage);
    }

    private void showValidateInvoicePreviousToView(String respuesta) {
        this.goValidateInvoicePreviousView.showSucessValidateInvoicePrevious(respuesta);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     * @param nisRad
     */
    private void doObtain(long nisRad, long recibo) {
        ValidateInvoicePreviousUseCase.Params params = ValidateInvoicePreviousUseCase.Params.forValidate(nisRad,recibo);
        this.goValidateInvoicePreviousUseCase.execute(new ValidateInvoicePreviousObserver(),params);
    }

    private final class ValidateInvoicePreviousObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goValidateInvoicePreviousView.onSessionExpired(poException.getMessage());
            }

            ValidateInvoicePreviousPresenter.this.showErrorMessage(poException.getMessage());
            ValidateInvoicePreviousPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String poData) {
            ValidateInvoicePreviousPresenter.this.showValidateInvoicePreviousToView(poData);
        }
    }
}
