package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.RegisterUserModule;
import pe.com.sedapal.ofivirtual.ui.activity.RegisterUserActivity;
/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 08/03/2019
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, RegisterUserModule.class})
public interface RegisterUserComponent extends ActivityComponent {

    void inject(RegisterUserActivity poRegisterUserActivity);


}
