package pe.com.sedapal.ofivirtual.di.components;

import android.app.Service;
import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerService;
import pe.com.sedapal.ofivirtual.di.module.ServiceModule;

/**
 * A base component upon which fragment's components may depend.
 * Service-level components should extend this component.
 * <p>
 * Subtypes of ServiceComponent should be decorated with annotation:
 * {@link PerService}
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    //Exposed to sub-graphs.
    Service service();
}
