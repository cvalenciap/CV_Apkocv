package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link MasterDataStore}.
 * <p>
 * Created by jsaenz on 7/03/2019.
 */
@Singleton
public class IncidentsDataStoreFactory {

    private static final String TAG = IncidentsDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link IncidentsDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    IncidentsDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    public IncidentsDataStore createCloudListMunicipalities() {
        return new CloudListMunicipalitiesDataStore(goContext);
    }

    public IncidentsDataStore createCloudListIncidentsMunicipalities() {
        return new CloudListIncidentsMunicipalitiesDataStore(goContext);
    }
}
