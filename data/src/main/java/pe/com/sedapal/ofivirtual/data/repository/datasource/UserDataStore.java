package pe.com.sedapal.ofivirtual.data.repository.datasource;

import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.SessionUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 23/01/2017.
 */

public interface UserDataStore {


    /**
     * Login user in request  {@link RequestLoginEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     */
    SessionUserEntity obtainUserSession();

    void saveUserSession(RequestNewUserLoginEntity poNewUserLoginEntity);

    Boolean deleteUserSession();

    void loginNewUser(CallbackDataStore<NewUserLoginEntity> poCallback, String poToken, RequestNewUserLoginEntity poRequest);

    void loginSupply(CallbackDataStore<SupplyLoginEntity> poCallback, String poToken, RequestSupplyLoginEntity poRequest);

    void dataUser(CallbackDataStore<ObtainDataUserLoginEntity> poCallback, String poToken, RequestObtainDataUserLoginEntity poRequest);
}
