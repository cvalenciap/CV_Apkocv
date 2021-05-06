package pe.com.sedapal.ofivirtual.di.components;

import android.app.Application;
import pe.com.sedapal.ofivirtual.di.module.ApplicationModule;

/**
 * Created by sedapal on 9/06/17.
 */
public class ComponentFactory {
    public static final MainComponent create(Application context) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build();
    }
}