package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link MasterDataStore}.
 * <p>
 * Created by jsaenz on 29/07/2020
 */
@Singleton
public class VersionDataStoreFactory {

    private static final String TAG = VersionDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link VersionDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 9/04/2019
     */
    @Inject
    VersionDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 9/04/2019
     */
    public VersionDataStore createCloud() {
        return new CloudVersionDataStore(goContext);
    }

}
