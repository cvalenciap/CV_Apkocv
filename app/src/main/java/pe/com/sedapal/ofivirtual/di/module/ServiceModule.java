package pe.com.sedapal.ofivirtual.di.module;

import android.app.Service;
import dagger.Module;
import dagger.Provides;
import pe.com.sedapal.ofivirtual.di.PerService;

/**
 * Created by Hernan Pareja on 3/03/2017.
 */
@Module
public class ServiceModule {
    private final Service goService;

    /**
     * Constructs a {@link ServiceModule}.
     *
     * @param poService {@link Service}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public ServiceModule(Service poService) {
        this.goService = poService;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerService
    Service service() {
        return this.goService;
    }
}
