package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class UpdateDataUserDataStoreFactory {

    private static final String TAG = UpdateDataUserDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    UpdateDataUserDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public UpdateDataUserDataStore createCloud() {
        return new CloudUpdateDataUserDataStore(goContext);
    }
}
