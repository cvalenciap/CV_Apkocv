package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidDocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.ValidDocumentTypeEntity;
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

public class CloudValidDocumentTypeDataStore extends RestBase implements ValidDocumentTypeDataStore {

    private static final String TAG = CloudValidDocumentTypeDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudValidDocumentTypeDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void valid(CallbackDataStore<ValidDocumentTypeEntity> poCallback, String token, RequestValidDocumentTypeEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Validar tipo documento");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_registro_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_val_tipo_nro_doc);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<ValidDocumentTypeEntity>> loCall =
                getRestApi().validDocumentType(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<ValidDocumentTypeEntity> loRestReceptor = new RestReceptor<ValidDocumentTypeEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<ValidDocumentTypeEntity>() {
            @Override
            public void onResponse(ValidDocumentTypeEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN -Validar tipo documento: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Validar tipo documento: onError");
                poCallback.onError(poException);
            }
        });
    }
}




