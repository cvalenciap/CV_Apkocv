package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class ValidSupplyDataStoreFactory {

    private static final String TAG = ValidSupplyDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    ValidSupplyDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public ValidSupplyDataStore createCloud() {
        return new CloudValidSupplyDataStore(goContext);
    }
}
