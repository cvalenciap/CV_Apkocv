package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtenerDatosPagoEntity;
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

public class CloudInsertarPagoMovilDataStore extends RestBase implements InsertarPagoMovilDataStore {

    private static final String TAG = CloudInsertarPagoMovilDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudInsertarPagoMovilDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void obtenerDatosPago(CallbackDataStore<ObtenerDatosPagoEntity> poCallback, String token, RequestObtenerDatosPagoEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Obtener datos");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_pago_ejecutado);
        String lsEndpoint = getContext().getString(R.string.endpoint_obtener_datos_pago);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<ObtenerDatosPagoEntity>> loCall =
                getRestApi().obtenerDatosPago(lsUrl, token, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<ObtenerDatosPagoEntity> loRestReceptor = new RestReceptor<ObtenerDatosPagoEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<ObtenerDatosPagoEntity>() {
            @Override
            public void onResponse(ObtenerDatosPagoEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Obtener datos: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Obtener datos: onError");
                poCallback.onError(poException);
            }
        });
    }
}




