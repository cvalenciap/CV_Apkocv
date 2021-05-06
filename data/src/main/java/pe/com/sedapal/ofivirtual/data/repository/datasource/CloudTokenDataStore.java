package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.TokenEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.net.RestCallback;
import pe.com.sedapal.ofivirtual.data.net.RestReceptor;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;

/**
 * * {@link CloudTokenDataStore} implementation based on connections to the api (Cloud).
 * <p>
 */

public class CloudTokenDataStore extends RestBase implements TokenDataStore {

    private static final String TAG = CloudTokenDataStore.class.getSimpleName();

    public CloudTokenDataStore(Context poContext) {
        super(poContext);
    }


    @Override
    public void obtainToken(CallbackDataStore<TokenEntity> poCallback) {
        LogUtil.i(TAG, "TOKEN - sync");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsEndpoint = getContext().getString(R.string.endponit_login_token);
        String lsUrl = String.format("%s%s", lsContext, lsEndpoint);

        Call<BaseResponseEntity<TokenEntity>> loCall =
                getRestApi().getToken(lsUrl, getContext().getString(R.string.username_token),getContext().getString(R.string.password_token));

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<TokenEntity> loRestReceptor = new RestReceptor<TokenEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<TokenEntity>() {
            @Override
            public void onResponse(TokenEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - sync: onResponse");
                poCallback.onSuccess(poData);

            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - sync: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void saveToken(String psName, String psValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getToken(String psName) {
        throw new UnsupportedOperationException();
    }
}
