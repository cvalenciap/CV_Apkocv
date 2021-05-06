package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class TokenDataStoreFactory {

    private static final String TAG = TokenDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    TokenDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public TokenDataStore createCloud() {
        return new CloudTokenDataStore(goContext);
    }

    /**
     * //
     * //
     */
    public TokenDataStore createLocal() {
        return new LocalTokenDataStore(goContext);
    }
}
