package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestRegUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSendConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateConfirmationCodeEntity;
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

public class CloudRegUserDataStore extends RestBase implements RegUserDataStore {

    private static final String TAG = CloudRegUserDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudRegUserDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void register(CallbackDataStore<String> poCallback, String token, RequestRegUserEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Registro de nuevo usuario");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_registro_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_reg_usuario_nuevo);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<String>> loCall =
                getRestApi().registerUser(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<String> loRestReceptor = new RestReceptor<String>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<String>() {
            @Override
            public void onResponse(String poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Registro de nuevo usuario: onResponse");
                poCallback.onSuccess(String.valueOf(poBaseResponseEntity.getMsg()));
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Registro de nuevo usuario: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void validateConfirmationCode(CallbackDataStore<String> poCallback, String token, RequestValidateConfirmationCodeEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Validar código de confirmación");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_registro_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_valida_codigo_confirmacion);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<RequestValidateConfirmationCodeEntity>> loCall =
                getRestApi().validateConfirmationCode(lsUrl, token, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<RequestValidateConfirmationCodeEntity> loRestReceptor = new RestReceptor<RequestValidateConfirmationCodeEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<RequestValidateConfirmationCodeEntity>() {
            @Override
            public void onResponse(RequestValidateConfirmationCodeEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Validar código de confirmación: onResponse");
                poCallback.onSuccess(String.valueOf(poBaseResponseEntity.getMsg()));
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Validar código de confirmación: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void sendConfirmationCode(CallbackDataStore<String> poCallback, String token, RequestSendConfirmationCodeEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Enviar código de confirmación");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_registro_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_enviar_codigo_confirmacion);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<String>> loCall =
                getRestApi().sendConfirmationCode(lsUrl, token, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<String> loRestReceptor = new RestReceptor<String>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<String>() {
            @Override
            public void onResponse(String poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Enviar código de confirmación: onResponse");
                poCallback.onSuccess(String.valueOf(poBaseResponseEntity.getMsg()));
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Enviar código de confirmación: onError");
                poCallback.onError(poException);
            }
        });
    }
}




