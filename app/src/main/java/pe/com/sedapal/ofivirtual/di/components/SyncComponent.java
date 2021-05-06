package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerService;
import pe.com.sedapal.ofivirtual.di.module.ServiceModule;
import pe.com.sedapal.ofivirtual.di.module.SyncModule;
import pe.com.sedapal.ofivirtual.ui.service.SyncService;

/**
 * A scope {@link PerService} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class, SyncModule.class})
public interface SyncComponent extends ServiceComponent {
    void inject(SyncService poSyncService);
}
