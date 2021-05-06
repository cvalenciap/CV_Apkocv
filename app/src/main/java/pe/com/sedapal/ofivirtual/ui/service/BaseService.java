package pe.com.sedapal.ofivirtual.ui.service;

import android.app.Service;
import pe.com.sedapal.ofivirtual.AndroidApplication;
import pe.com.sedapal.ofivirtual.di.components.ApplicationComponent;
import pe.com.sedapal.ofivirtual.di.module.ServiceModule;

/**
 * Created by Hernan Pareja on 3/03/2017.
 */

public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        this.getApplicationComponent().inject(this);
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link MainComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return (ApplicationComponent) ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Service module for dependency injection.
     *
     * @return {@link ServiceModule}
     */
    protected ServiceModule getServiceModule() {
        return new ServiceModule(this);
    }
}
