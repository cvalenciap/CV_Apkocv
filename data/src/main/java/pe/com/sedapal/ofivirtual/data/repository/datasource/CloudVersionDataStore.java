package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.ValidateVersionEntity;
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

public class CloudVersionDataStore extends RestBase implements VersionDataStore {

    private static final String TAG = CloudVersionDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 05/12/2018
     */
    public CloudVersionDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void validateVersion(CallbackDataStore<ValidateVersionEntity> poCallback, String token, ValidateVersionEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Valida version");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_autenticacion_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_valida_version);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<ValidateVersionEntity>> loCall =
                getRestApi().validateVersion(lsUrl, token, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<ValidateVersionEntity> loRestReceptor = new RestReceptor<>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<ValidateVersionEntity>() {
            @Override
            public void onResponse(ValidateVersionEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Valida version: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Valida version: onError");
                poCallback.onError(poException);
            }
        });
    }
}




