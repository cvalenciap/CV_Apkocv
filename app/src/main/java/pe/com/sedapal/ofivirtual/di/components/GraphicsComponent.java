package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.CommercialOfficeModule;
import pe.com.sedapal.ofivirtual.di.module.GraphicsModule;
import pe.com.sedapal.ofivirtual.ui.activity.GraphicsActivity;
import pe.com.sedapal.ofivirtual.ui.fragment.HomeFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PayInvoicesFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PendingInvoicesFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, GraphicsModule.class})
public interface GraphicsComponent extends ActivityComponent {

    void inject(GraphicsActivity poGraphicsActivity);
}
