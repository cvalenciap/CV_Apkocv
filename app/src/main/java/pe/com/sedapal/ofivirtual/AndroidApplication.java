package pe.com.sedapal.ofivirtual;

import android.content.Context;

import androidx.multidex.MultiDex;

import pe.com.sedapal.ofivirtual.data.DataApplication;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.components.ApplicationComponent;
import pe.com.sedapal.ofivirtual.di.components.ComponentFactory;
import pe.com.sedapal.ofivirtual.di.components.MainComponent;

/**
 * Android Main Application
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
public class AndroidApplication extends DataApplication {

    private static final String TAG = AndroidApplication.class.getSimpleName();


    private MainComponent goApplicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {

        this.initializeInjector();
        super.onCreate();
//        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        setApplicationComponent(ComponentFactory.create(this));
    }

    public ApplicationComponent getApplicationComponent() {
        return (ApplicationComponent) this.goApplicationComponent;

    }

    public void setApplicationComponent(MainComponent poApplicationComponent) {
        LogUtil.i(TAG, "APPLICATION COMPONENT ES NULO: " + (poApplicationComponent == null));
        this.goApplicationComponent = poApplicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
//            LeakCanary.install(this);
        }
    }

}
