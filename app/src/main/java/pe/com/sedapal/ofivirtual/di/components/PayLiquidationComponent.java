package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerService;
import pe.com.sedapal.ofivirtual.di.module.PayLiquidationModule;
import pe.com.sedapal.ofivirtual.di.module.ServiceModule;
import pe.com.sedapal.ofivirtual.ui.service.PayLiquidationService;

/**
 * A scope {@link PerService} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 23/04/19
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = {ServiceModule.class, PayLiquidationModule.class})
public interface PayLiquidationComponent extends ServiceComponent {
    void inject(PayLiquidationService poSyncService);
}
