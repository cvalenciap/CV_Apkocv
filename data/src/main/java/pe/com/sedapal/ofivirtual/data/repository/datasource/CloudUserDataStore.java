package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.SessionUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;

/**
 * * {@link UserDataStore} implementation based on connections to the api (Cloud).
 * <p>
 * Created by jsaenz on 23/01/2017.
 */

public class CloudUserDataStore extends RestBase implements UserDataStore {

    private static final String TAG = CloudUserDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public CloudUserDataStore(Context poContext) {
        super(poContext);
    }


    @Override
    public SessionUserEntity obtainUserSession() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveUserSession(RequestNewUserLoginEntity poNewUserLoginEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean deleteUserSession() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loginNewUser(CallbackDataStore<NewUserLoginEntity> poCallback, String token, RequestNewUserLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loginSupply(CallbackDataStore<SupplyLoginEntity> poCallback, String poToken, RequestSupplyLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dataUser(CallbackDataStore<ObtainDataUserLoginEntity> poCallback, String poToken, RequestObtainDataUserLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

}
