package pe.com.sedapal.ofivirtual;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
@Singleton
public class UIThread implements PostExecutionThread {

    /**
     * Constructs a {@link UIThread}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    UIThread() {
    }


    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
