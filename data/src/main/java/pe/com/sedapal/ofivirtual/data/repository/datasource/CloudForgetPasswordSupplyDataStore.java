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

public class CloudForgetPasswordSupplyDataStore extends RestBase implements ForgetPasswordDataStore {

    private static final String TAG = CloudForgetPasswordSupplyDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudForgetPasswordSupplyDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void validSupply(CallbackDataStore<ForgetPasswordSupplyEntity> poCallback, String token, RequestForgetPasswordSupply poRequest) {
        LogUtil.i(TAG, "INICIO - Olvidar Contraseña Correo");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_autenticacion_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_vali_preg_secr);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<ForgetPasswordSupplyEntity>> loCall =
                getRestApi().forgetPasswordSupply(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<ForgetPasswordSupplyEntity> loRestReceptor = new RestReceptor<ForgetPasswordSupplyEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<ForgetPasswordSupplyEntity>() {
            @Override
            public void onResponse(ForgetPasswordSupplyEntity poData, BaseResponseEntity poBaseResponseEntity) {
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
    public void validEmail(CallbackDataStore<String> poCallback, String token, RequestForgetPasswordEmail poRequest) {
        throw new UnsupportedOperationException();
    }
}




