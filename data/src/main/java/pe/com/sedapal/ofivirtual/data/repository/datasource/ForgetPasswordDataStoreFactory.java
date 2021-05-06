package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class ForgetPasswordDataStoreFactory {

    private static final String TAG = ForgetPasswordDataStoreFactory.class.getSimpleName();

    private final Context goContext;
    /**
     *
     * @param poContext
     */
    @Inject
    ForgetPasswordDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     *
     */
    public ForgetPasswordDataStore createCloudEmail() {
        return new CloudForgetPasswordEmailDataStore(goContext);
    }

    public ForgetPasswordDataStore createCloudSupply() {
        return new CloudForgetPasswordSupplyDataStore(goContext);
    }
}
