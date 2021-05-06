package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class ValidDocumentTypeDataStoreFactory {

    private static final String TAG = ValidDocumentTypeDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    ValidDocumentTypeDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public ValidDocumentTypeDataStore createCloud() {
        return new CloudValidDocumentTypeDataStore(goContext);
    }
}
