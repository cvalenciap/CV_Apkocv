package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.ForgetPasswordEmailModule;
import pe.com.sedapal.ofivirtual.ui.activity.OlvContraCorreoActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ForgetPasswordEmailModule.class})
public interface ForgetPasswordEmailComponent extends ActivityComponent {

    void inject(OlvContraCorreoActivity poOlvContraCorreoActivity);


}
