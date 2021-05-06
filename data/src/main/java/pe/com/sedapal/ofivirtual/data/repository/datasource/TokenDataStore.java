package pe.com.sedapal.ofivirtual.data.repository.datasource;

import pe.com.sedapal.ofivirtual.data.entity.*;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 23/01/2017.
 */

public interface TokenDataStore {

    void obtainToken(CallbackDataStore<TokenEntity> poCallback);

    void saveToken(String psName, String psValue);

    String getToken(String psName);

}
