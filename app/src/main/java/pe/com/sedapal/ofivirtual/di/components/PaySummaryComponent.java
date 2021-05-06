package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.PaySummaryModule;
import pe.com.sedapal.ofivirtual.ui.activity.PaySummaryActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 08/03/2019
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PaySummaryModule.class})
public interface PaySummaryComponent extends ActivityComponent {

    void inject(PaySummaryActivity poPaySummaryActivity);

}
