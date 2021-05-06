package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidSupplyEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.net.RestCallback;
import pe.com.sedapal.ofivirtual.data.net.RestReceptor;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;

/**
 * * {@link UserDataStore} implementation based on connections to the api (Cloud).
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

public class CloudValidSupplyDataStore extends RestBase implements ValidSupplyDataStore {

    private static final String TAG = CloudValidSupplyDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 05/12/2018
     */
    public CloudValidSupplyDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void valid(CallbackDataStore<String> poCallback, String token, RequestValidSupplyEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Valida Suministro");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_registro_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_valida_suministro);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<String>> loCall =
                getRestApi().validSupply(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<String> loRestReceptor = new RestReceptor<String>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<String>() {
            @Override
            public void onResponse(String poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Valida Suministro: onResponse");
                poCallback.onSuccess(String.valueOf(poBaseResponseEntity.getMsg()));
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Valida Suministro: onError");
                poCallback.onError(poException);
            }
        });
    }
}




