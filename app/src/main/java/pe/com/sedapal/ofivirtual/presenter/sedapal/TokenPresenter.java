package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.SyncTokenUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.TokenView;

/**
 * Created by jsaenz on 04,diciembre,2018
 */
public class TokenPresenter implements Presenter {

    private TokenView goTokenView;
    private final SyncTokenUseCase goSyncTokenUseCase;

    @Inject
    public TokenPresenter(SyncTokenUseCase poSyncTokenUseCase) {
        this.goSyncTokenUseCase = poSyncTokenUseCase;
    }

    public void setView(@NonNull TokenView poTokenView) {
        this.goTokenView = poTokenView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goSyncTokenUseCase.dispose();
        this.goTokenView = null;
    }

    public void initialize() {
        this.sync();
    }

    private void sync() {
        this.hideViewRetry();
        this.showViewLoading();
        this.doSync();
    }

    private void showViewLoading() {
        this.goTokenView.showLoadingProgress();
    }

    private void hideViewLoading() {
        this.goTokenView.hideLoadingProgress();
    }

    private void showViewRetry() {
        this.goTokenView.showRetry();
    }

    private void hideViewRetry() {
        this.goTokenView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        if(goTokenView != null)
        this.goTokenView.showError(psErrorMessage);
    }

    private void showSyncSuccessToView(String strToken) {
        this.goTokenView.showSucessGetToken(strToken);
    }

    private void doSync() {
        this.goSyncTokenUseCase.execute(new TokenPresenter.GetTokenObserver(), null);
    }

    private final class GetTokenObserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            //TokenPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            TokenPresenter.this.hideViewLoading();
            TokenPresenter.this.showErrorMessage(poException.getMessage());
            TokenPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(String poData) {
            TokenPresenter.this.showSyncSuccessToView(poData);
        }
    }
}
