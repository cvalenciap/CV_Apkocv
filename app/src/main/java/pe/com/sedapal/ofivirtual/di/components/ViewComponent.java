package pe.com.sedapal.ofivirtual.di.components;

import android.view.View;
import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerView;
import pe.com.sedapal.ofivirtual.di.module.ViewModule;

/**
 * A base component upon which fragment's components may depend.
 * View-level components should extend this component.
 * <p>
 * Subtypes of ViewComponent should be decorated with annotation:
 * {@link PerView}
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerView
@Component(dependencies = MainComponent.class, modules = ViewModule.class)
public interface ViewComponent {
    //Exposed to sub-graphs.
    View view();
}
