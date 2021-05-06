package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.SendConfirmationCodeUseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.ValidateConfirmationCodeUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.VerificationCodeView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 27/07/20.
 */
public class VerificationCodePresenter implements Presenter {

    private VerificationCodeView goVerificationCodeView;
    private ValidateConfirmationCodeUseCase goValidateConfirmationCodeUseCase;
    private SendConfirmationCodeUseCase goSendConfirmationCodeUseCase;

    @Inject
    public VerificationCodePresenter(ValidateConfirmationCodeUseCase poValidateConfirmationCodeUseCase, SendConfirmationCodeUseCase poSendConfirmationCodeUseCase) {
        this.goValidateConfirmationCodeUseCase = poValidateConfirmationCodeUseCase;
        this.goSendConfirmationCodeUseCase = poSendConfirmationCodeUseCase;
    }

    public void setView(@NonNull VerificationCodeView poVerificationCodeView) {
        this.goVerificationCodeView = poVerificationCodeView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goVerificationCodeView = null;
    }

    private void showViewValidateLoading() {
        this.goVerificationCodeView.showLoading(this.goVerificationCodeView.context().getString(R.string.lbl_progressdialog_verify_code));
    }

    private void showViewSendLoading() {
        this.goVerificationCodeView.showLoading(this.goVerificationCodeView.context().getString(R.string.lbl_progressdialog_send_code));
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goVerificationCodeView.showError(psErrorMessage);
    }

    private void hideViewLoading() {
        this.goVerificationCodeView.hideLoading();
    }

    private void showViewRetry() {
        this.goVerificationCodeView.showRetry();
    }

    private void hideViewRetry() {
        this.goVerificationCodeView.hideRetry();
    }

    /**
     * Validación de código de confirmación
     *
     * @param psEmailUser
     * @param psCodeVerify
     */

    public void initializeValidateConfirmationCode(String psEmailUser, String psCodeVerify) {
        this.hideViewRetry();
        this.showViewValidateLoading();
        this.doVerifyCode(psEmailUser, psCodeVerify);
    }

    private void doVerifyCode(String psEmailUser, String psCodeVerify) {
        ValidateConfirmationCodeUseCase.Params loParams = ValidateConfirmationCodeUseCase.Params.forUser(psEmailUser, psCodeVerify);
        this.goValidateConfirmationCodeUseCase.execute(new VerificationCodeObserver(), loParams);
    }


    private void showVerificationCodeToView(String poMessage) {
        this.goVerificationCodeView.showVerificationCodeSuccessToView(poMessage);
    }


    private final class VerificationCodeObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            VerificationCodePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            VerificationCodePresenter.this.hideViewLoading();

            BaseException loBaseException = (BaseException) poException;
            if (loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)) {
                goVerificationCodeView.onSessionExpired(poException.getMessage());
            }

            VerificationCodePresenter.this.showErrorMessage(poException.getMessage());
            VerificationCodePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String poData) {
            VerificationCodePresenter.this.showVerificationCodeToView(poData);
        }
    }

    /**
     * Enviar nuevamente el código de confirmación
     */

    public void initializeSendConfirmationCode(String psEmailUser) {
        this.hideViewRetry();
        this.showViewSendLoading();
        this.doSendConfirmationCode(psEmailUser);
    }

    private void doSendConfirmationCode(String psEmailUser) {
        SendConfirmationCodeUseCase.Params loParams = SendConfirmationCodeUseCase.Params.forUser(psEmailUser);
        this.goSendConfirmationCodeUseCase.execute(new SendCodeObserver(), loParams);
    }


    private void showSendCodeToView(String poMessage) {
        this.goVerificationCodeView.showSendCodeSuccessToView(poMessage);
    }


    private final class SendCodeObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            VerificationCodePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            VerificationCodePresenter.this.hideViewLoading();

            BaseException loBaseException = (BaseException) poException;
            if (loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)) {
                goVerificationCodeView.onSessionExpired(poException.getMessage());
            }

            VerificationCodePresenter.this.showErrorMessage(poException.getMessage());
            VerificationCodePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String poData) {
            VerificationCodePresenter.this.showSendCodeToView(poData);
        }
    }
}
