package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link MasterDataStore}.
 * <p>
 * Created by jsaenz on 9/04/2019.
 */
@Singleton
public class EnchufateDataStoreFactory {

    private static final String TAG = EnchufateDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link EnchufateDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 9/04/2019
     */
    @Inject
    EnchufateDataStoreFactory(@NonNull Context poContext) {
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
    public EnchufateDataStore createCloudLiquidacionEnchufate() {
        return new CloudLiquidacionEnchufateDataStore(goContext);
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 9/04/2019
     */
    public EnchufateDataStore createCloudPagoLiquidacionEnchufate() {
        return new CloudPagoLiquidacionEnchufateDataStore(goContext);
    }
}
