package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.CommercialOfficeModule;
import pe.com.sedapal.ofivirtual.di.module.DetailInvoiceModule;
import pe.com.sedapal.ofivirtual.ui.activity.DetailInvoceActivity;
import pe.com.sedapal.ofivirtual.ui.fragment.HomeFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PendingInvoicesFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, DetailInvoiceModule.class})
public interface DetailInvoiceComponent extends ActivityComponent {

    void inject(DetailInvoceActivity poDetailInvoceActivity);
}
