package pe.com.sedapal.ofivirtual.ui.service;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerSyncComponent;
import pe.com.sedapal.ofivirtual.di.components.SyncComponent;
import pe.com.sedapal.ofivirtual.presenter.master.SyncPresenterBackground;
import pe.com.sedapal.ofivirtual.presenter.view.SyncViewBackground;


public class SyncService extends BaseService implements HasComponent<SyncComponent>, SyncViewBackground {

    private static final String TAG = SyncService.class.getSimpleName();

    @Inject
    SyncPresenterBackground goSyncPresenter;

    private SyncComponent goSyncComponent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.initializeInjector();
        this.goSyncPresenter.setView(this);
        this.goSyncPresenter.initialize();
        return START_NOT_STICKY;
    }

    public static Intent getCallingIntent(Context poContext) {
        return new Intent(poContext, SyncService.class);
    }

    @Override
    public void onCreate() {
        LogUtil.i(TAG, "INICIO - sync");
        super.onCreate();
    }

    /**
     * Initialize injector in activity
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    private void initializeInjector() {

        this.goSyncComponent = DaggerSyncComponent.builder()
                .applicationComponent(getApplicationComponent())
                .serviceModule(getServiceModule())
                .build();
        this.goSyncComponent.inject(SyncService.this);
    }

    @Override
    public void onDestroy() {
        LogUtil.i(TAG, "FIN - sync");
        super.onDestroy();
        this.goSyncPresenter.destroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void sendBroadcast(boolean pbSuccess) {
        LogUtil.i(TAG, "SEND BROADCAST - sync " + String.valueOf(pbSuccess));
        Intent loIntent = new Intent();
        loIntent.setAction(getResources().getString(R.string.broadcast_sync_completed))
                .putExtra(getString(R.string.broadcast_success), pbSuccess);
        sendBroadcast(loIntent);
        stopSelf();
    }

    @Override
    public SyncComponent getComponent() {
        return goSyncComponent;
    }

}
