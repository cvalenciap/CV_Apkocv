package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 * <p>
 * Created by jsaenz on 7/02/2019.
 */
@Singleton
public class UserDataStoreFactory {

    private static final String TAG = UserDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link UserDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2019
     */
    @Inject
    UserDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    public UserDataStore createCloud() {
        return new CloudUserDataStore(goContext);
    }

    public UserDataStore createCloudNewUser() {
        return new CloudNewUserDataStore(goContext);
    }

    public UserDataStore createCloudSupply() {
        return new CloudOldUserDataStore(goContext);
    }

    public UserDataStore createLocal() {
        return new LocalUserSPDataStore(goContext);
    }

    public UserDataStore createCloudObtainDataUser() {
        return new CloudObtainDataUserDataStore(goContext);
    }
}
