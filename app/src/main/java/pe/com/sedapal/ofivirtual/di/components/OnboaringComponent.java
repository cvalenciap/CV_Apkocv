package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.DetailInvoiceModule;
import pe.com.sedapal.ofivirtual.di.module.OnboardingModule;
import pe.com.sedapal.ofivirtual.ui.activity.DetailInvoceActivity;
import pe.com.sedapal.ofivirtual.ui.activity.OnboardingActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, OnboardingModule.class})
public interface OnboaringComponent extends ActivityComponent {

    void inject(OnboardingActivity poOnboardingActivity);
}
