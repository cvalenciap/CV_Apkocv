package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class ValidPayRefDataStoreFactory {

    private static final String TAG = ValidPayRefDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    ValidPayRefDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public ValidPayRefDataStore createCloud() {
        return new CloudValidPayRefDataStore(goContext);
    }
}
