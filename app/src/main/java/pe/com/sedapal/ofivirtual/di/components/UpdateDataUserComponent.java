package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.UpdateDataUserModule;
import pe.com.sedapal.ofivirtual.ui.activity.UpdateDataUserActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UpdateDataUserModule.class})
public interface UpdateDataUserComponent extends ActivityComponent {
    void inject(UpdateDataUserActivity poUpdateDataUserActivity);
}
