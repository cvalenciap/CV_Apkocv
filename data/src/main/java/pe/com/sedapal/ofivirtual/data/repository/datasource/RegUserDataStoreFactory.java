package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class RegUserDataStoreFactory {

    private static final String TAG = RegUserDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    RegUserDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public RegUserDataStore createCloud() {
        return new CloudRegUserDataStore(goContext);
    }
}
