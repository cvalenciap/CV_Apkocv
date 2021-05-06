package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.ForgetPasswordSupplyModule;
import pe.com.sedapal.ofivirtual.ui.activity.OlvContraSuministroActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ForgetPasswordSupplyModule.class})
public interface ForgetPasswordSupplyComponent extends ActivityComponent {
    void inject(OlvContraSuministroActivity poOlvContraSuministroActivity);
}
