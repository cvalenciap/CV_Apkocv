package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import pe.com.sedapal.ofivirtual.data.entity.TokenEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.preference.PreferenceBase;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class LocalTokenDataStore extends PreferenceBase implements TokenDataStore {

    public LocalTokenDataStore(Context context) {
        super(context);
    }

    @Override
    public void obtainToken(CallbackDataStore<TokenEntity> poCallback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveToken(String psName, String psValue) {
        saveOnSharePreferences(psName,psValue);
    }

    @Override
    public String getToken(String psName) {
        return getPreference(psName);
    }
}
