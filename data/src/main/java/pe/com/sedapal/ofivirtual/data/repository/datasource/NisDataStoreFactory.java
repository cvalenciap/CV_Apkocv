package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 * <p>
 * Created by jsaenz on 11//03/2019
 */
@Singleton
public class NisDataStoreFactory {

    private static final String TAG = NisDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link NisDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    NisDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     * Create {@link UserDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudUserDataStore} of the Cloud.
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    public NisDataStore createCloud() {
        return new CloudListNisDataStore(goContext);
    }

    public NisDataStore createCloudDetailNis() {
        return new CloudDetailNisDataStore(goContext);
    }

    public NisDataStore createCloudListHistoricConsum() {
        return new CloudHistoricConsumDataStore(goContext);
    }

}
