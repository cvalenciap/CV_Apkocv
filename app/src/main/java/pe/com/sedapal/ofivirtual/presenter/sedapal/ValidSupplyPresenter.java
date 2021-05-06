package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.ValidSupplyUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ValidSupplyView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class ValidSupplyPresenter implements Presenter {

    private ValidSupplyView goValidSupplyView;

    private final ValidSupplyUseCase goValidSupplyUseCase;

    @Inject
    public ValidSupplyPresenter(ValidSupplyUseCase poValidSupplyUseCase) {
        this.goValidSupplyUseCase = poValidSupplyUseCase;
    }

    public void setView(@NonNull ValidSupplyView poValidSupplyView) {
        this.goValidSupplyView = poValidSupplyView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goValidSupplyView = null;
        this.goValidSupplyUseCase.dispose();
    }

    public void initialize(String supply) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doValid(supply);
    }

    private void showViewLoading() {
        //this.goValidSupplyView.showLoading(this.goValidSupplyView.context().getResources().getString(R.string.lbl_progressdialog_suministro_verif));
        this.goValidSupplyView.showLoadingSupply();
    }

    private void hideViewLoading() {
        //this.goValidSupplyView.hideLoading();
        this.goValidSupplyView.hideLoadingSupply();
    }

    private void showViewRetry() {
        this.goValidSupplyView.showRetry();
    }

    private void hideViewRetry() {
        this.goValidSupplyView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goValidSupplyView.showErrorMessageSupply(psErrorMessage);
    }

    private void showValidSupplySuccessToView(String message) {
        this.goValidSupplyView.showSucessValidSupply(message);
    }

    /**
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doValid(String strSupply) {
        ValidSupplyUseCase.Params params = ValidSupplyUseCase.Params.forValid(Integer.parseInt(strSupply));
        this.goValidSupplyUseCase.execute(new ValidSupplyObserver(),params);

    }

    private final class ValidSupplyObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            ValidSupplyPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ValidSupplyPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goValidSupplyView.onSessionExpired(poException.getMessage());
            }else {
                ValidSupplyPresenter.this.showErrorMessage(poException.getMessage());
                ValidSupplyPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(String poData) {
            ValidSupplyPresenter.this.showValidSupplySuccessToView(poData);
        }
    }
}
