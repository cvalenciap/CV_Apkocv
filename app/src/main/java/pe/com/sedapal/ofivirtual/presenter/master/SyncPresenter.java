package pe.com.sedapal.ofivirtual.presenter.master;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.master.SyncUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.SyncView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class SyncPresenter implements Presenter {

    private SyncView goSyncView;
    private final SyncUseCase goSyncUseCase;


    @Inject
    public SyncPresenter(SyncUseCase poSyncUseCase) {
        this.goSyncUseCase = poSyncUseCase;
    }

    /**
     * Set View {@link SyncView} to listener on Fragment or Activity
     *
     * @param poSyncView {@link SyncView}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void setView(@NonNull SyncView poSyncView) {
        this.goSyncView = poSyncView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goSyncUseCase.dispose();
        this.goSyncView = null;
    }

    /**
     * Initialize sync the presenter by sync in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public void initialize() {
        this.sync();
    }


    /**
     * The user sync.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void sync() {
        this.hideViewRetry();
        this.showViewLoading();
        this.doSync();
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewLoading() {
        //this.goSyncView.showLoadingSync();
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewLoading() {
        this.goSyncView.hideLoadingProgress();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showViewRetry() {
        this.goSyncView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void hideViewRetry() {
        this.goSyncView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goSyncView.showError(psErrorMessage);
    }

    /**
     * Show result sync
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/12/2018
     */
    private void showSyncSuccessToView() {
        this.goSyncView.showSyncSuccess();
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 29/12/2018
     */
    private void doSync() {
        this.goSyncUseCase.execute(new SyncObserver(), null);
    }

    private final class SyncObserver extends DefaultObserver<Void> {

        @Override
        public void onComplete() {
            //SyncPresenter.this.hideViewLoading();
            SyncPresenter.this.showSyncSuccessToView();
        }

        @Override
        public void onError(Throwable poException) {
            SyncPresenter.this.hideViewLoading();
            SyncPresenter.this.showErrorMessage(poException.getMessage());
            SyncPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Void poData) {
            //SyncPresenter.this.showSyncSuccessToView();
        }
    }
}
