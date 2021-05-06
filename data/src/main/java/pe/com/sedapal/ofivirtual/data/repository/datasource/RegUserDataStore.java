package pe.com.sedapal.ofivirtual.data.repository.datasource;

import pe.com.sedapal.ofivirtual.data.entity.RequestRegUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSendConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 06/12/2018.
 */

public interface RegUserDataStore {


    /**
     * Login user in request  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link SyncEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018.
     */
    void register(CallbackDataStore<String> poCallback, String token, RequestRegUserEntity poRequest);

    void validateConfirmationCode(CallbackDataStore<String> poCallback, String token, RequestValidateConfirmationCodeEntity poRequest);

    void sendConfirmationCode(CallbackDataStore<String> poCallback, String token, RequestSendConfirmationCodeEntity poRequest);

}
