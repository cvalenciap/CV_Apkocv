package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.DetailInvoiceModule;
import pe.com.sedapal.ofivirtual.di.module.IncidentsMunicipalitiesModule;
import pe.com.sedapal.ofivirtual.domain.entity.IncidentsMunicipalities;
import pe.com.sedapal.ofivirtual.ui.activity.DetailInvoceActivity;
import pe.com.sedapal.ofivirtual.ui.activity.IncidentsMunicipalitiesActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 20/03/2019
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, IncidentsMunicipalitiesModule.class})
public interface IncidentsMunicipalitiesComponent extends ActivityComponent {

    void inject(IncidentsMunicipalitiesActivity poIncidentsMunicipalitiesActivity);
}
