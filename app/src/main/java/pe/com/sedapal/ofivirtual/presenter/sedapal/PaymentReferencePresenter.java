package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.ValidPayRefUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.PaymentReferenceView;
import pe.com.sedapal.ofivirtual.util.Constants;

public class PaymentReferencePresenter implements Presenter {

    private PaymentReferenceView goPaymentReferenceView;
    private final ValidPayRefUseCase goValidPayRefUseCase;

    @Inject
    public PaymentReferencePresenter(ValidPayRefUseCase poValidPayRefUseCase) {
        this.goValidPayRefUseCase = poValidPayRefUseCase;
    }

    public void setView(@NonNull PaymentReferenceView poPaymentReferenceView) {
        this.goPaymentReferenceView = poPaymentReferenceView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goPaymentReferenceView = null;
        this.goValidPayRefUseCase.dispose();
    }

    public void initialize(long numSupply, long psPayRef) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doValidate(numSupply,psPayRef);
    }

    private void showViewLoading() {
        //this.goPaymentReferenceView.showLoading(this.goPaymentReferenceView.context().getString(R.string.lbl_progressdialog_referencia_cobro));
        this.goPaymentReferenceView.showLoadingPaymentReference();
    }

    private void hideViewLoading() {
        //this.goPaymentReferenceView.hideLoading();
        this.goPaymentReferenceView.hideLoadingPaymentReference();
    }

    private void showViewRetry() {
        this.goPaymentReferenceView.showRetry();
    }

    private void hideViewRetry() {
        this.goPaymentReferenceView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goPaymentReferenceView.showErrorMessagePayReference(psErrorMessage);
    }

    private void showSuministroSuccessToView(String poMessage) {
        this.goPaymentReferenceView.showSucessValidPaymentReference(poMessage);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doValidate(long numSupply, long psReferencia) {
        ValidPayRefUseCase.Params loParams = ValidPayRefUseCase.Params.forValid(numSupply,psReferencia);
        this.goValidPayRefUseCase.execute(new PayRefObserver(), loParams);
    }

    private final class PayRefObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            PaymentReferencePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            PaymentReferencePresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goPaymentReferenceView.onSessionExpired(poException.getMessage());
            }else {
                PaymentReferencePresenter.this.showErrorMessage(poException.getMessage());
                PaymentReferencePresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(String poData) {
            PaymentReferencePresenter.this.showSuministroSuccessToView(poData);
        }
    }
}
