package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link MasterDataStore}.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */
@Singleton
public class MasterDataStoreFactory {

    private static final String TAG = MasterDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link MasterDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    MasterDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public MasterDataStore createCloud() {
        return new CloudMasterDataStore(goContext);
    }

    /**
     * //     * Create {@link MasterDataStore} to retrieve data from the Local.
     * //     *
     * //     * @return {@link CloudMasterDataStore} of the Local.
     * //     * @author jsaenz
     * //     * @version 1.0
     * //     * @since 7/02/2017
     * //
     */
    public MasterDataStore createLocal() {
        return new LocalMasterDataStore();
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public MasterDataStore createCloudDatosVisa() {
        return new CloudDatosVisaDataStore(goContext);
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public MasterDataStore createCloudDatosImagenes() {
        return new CloudDatosImagenesDataStore(goContext);
    }
}
