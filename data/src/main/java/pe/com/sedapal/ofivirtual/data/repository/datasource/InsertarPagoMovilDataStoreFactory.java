package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class InsertarPagoMovilDataStoreFactory {

    private static final String TAG = InsertarPagoMovilDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     *
     * @param poContext
     */
    @Inject
    InsertarPagoMovilDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public InsertarPagoMovilDataStore createCloudInsertarPagoMovil() {
        return new CloudInsertarPagoMovilDataStore(goContext);
    }


}
