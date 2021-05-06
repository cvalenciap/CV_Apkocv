package pe.com.sedapal.ofivirtual.presenter.master;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.master.SyncUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.SyncView;
import pe.com.sedapal.ofivirtual.presenter.view.SyncViewBackground;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class SyncPresenterBackground implements Presenter {

    private SyncViewBackground goSyncView;

    private final SyncUseCase goSyncUseCase;
    private int giCountRetry = 0;

    /**
     * Constructs a {@link SyncPresenter}.
     *
     * @param poSyncUseCase {@link SyncUseCase}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 28/02/2017
     */
    @Inject
    public SyncPresenterBackground(SyncUseCase poSyncUseCase) {
        this.goSyncUseCase = poSyncUseCase;
    }

    /**
     * Set View {@link SyncView} to listener on Fragment or Activity
     *
     * @param poSyncView {@link SyncView}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 28/02/2017
     */
    public void setView(@NonNull SyncViewBackground poSyncView) {
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
        //this.goSyncUseCase.dispose();
        //this.goSyncView = null;
    }

    /**
     * Initialize sign in the presenter by signing in the user.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 28/02/2017
     */
    public void initialize() {
        this.doSync();
    }

    /**
     * Show list geographic locations in view
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 28/02/2017
     */
    private void sendBroadcast(boolean pbSuccess) {
        this.goSyncView.sendBroadcast(pbSuccess);
    }

    /**
     * Execute sync.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 15/02/2017
     */
    private void doSync() {
        giCountRetry++;
        this.goSyncUseCase.execute(new SyncObserver(), null);
    }

    private final class SyncObserver extends DefaultObserver<Void> {

        @Override
        public void onNext(Void poData) {
            LogUtil.i("next", "");
        }

        @Override
        public void onError(Throwable poException) {
            if (giCountRetry < 1) {
                doSync();
            } else {
                sendBroadcast(false);
            }
        }

        @Override
        public void onComplete() {
            sendBroadcast(true);
        }
    }

}
