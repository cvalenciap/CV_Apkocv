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
public class PayPlaceDataStoreFactory {

    private static final String TAG = PayPlaceDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link PayPlaceDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    PayPlaceDataStoreFactory(@NonNull Context poContext) {
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
    public PayPlaceDataStore createCloudListAgency() {
        return new CloudListAgencyDataStore(goContext);
    }

    public PayPlaceDataStore createCloudListSubsidiary() {
        return new CloudListSubsidiaryDataStore(goContext);
    }
}
