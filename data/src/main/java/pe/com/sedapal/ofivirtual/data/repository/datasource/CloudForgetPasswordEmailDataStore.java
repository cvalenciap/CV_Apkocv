package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.ForgetPasswordSupplyEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestForgetPasswordEmail;
import pe.com.sedapal.ofivirtual.data.entity.RequestForgetPasswordSupply;
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

public class CloudForgetPasswordEmailDataStore extends RestBase implements ForgetPasswordDataStore {

    private static final String TAG = CloudForgetPasswordEmailDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudForgetPasswordEmailDataStore(Context poContext) {
        super(poContext);
    }





    @Override
    public void validEmail(CallbackDataStore<String> poCallback, String token, RequestForgetPasswordEmail poRequest) {
        LogUtil.i(TAG, "INICIO - Olvidar Contraseña Correo");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_autenticacion_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_recuperar_contrasena);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<String>> loCall =
                getRestApi().forgetPasswordEmail(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<String> loRestReceptor = new RestReceptor<String>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<String>() {
            @Override
            public void onResponse(String poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN -Olvidar Contraseña Correo: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Olvidar Contraseña Correo: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void validSupply(CallbackDataStore<ForgetPasswordSupplyEntity> poCallback, String token, RequestForgetPasswordSupply poRequest) {
        throw new UnsupportedOperationException();
    }
}




