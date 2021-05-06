package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.LiquidacionEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayLiquidationEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPagoLiquidacionEnchufateEntity;
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
 * Created by jsaenz
 */

public class CloudLiquidacionEnchufateDataStore extends RestBase implements EnchufateDataStore {

    private static final String TAG = CloudLiquidacionEnchufateDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public CloudLiquidacionEnchufateDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void generarLiquidacionEnchufateEntity(CallbackDataStore<LiquidacionEntity> poCallback, String poTokenGenerado, RequestLiquidacionEnchufateEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Generar liquidacion enchufate");
        String lsEndpoint = getContext().getString(R.string.endpoint_ocv_generar_liquidacion);
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_enchufate);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<LiquidacionEntity>> loCall = getRestApi().generarLiquidacion(lsUrl,
                poTokenGenerado,
                poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<LiquidacionEntity> loRestReceptor = new RestReceptor<LiquidacionEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<LiquidacionEntity>() {
            @Override
            public void onResponse(LiquidacionEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Generar Liquidacion enchufate: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Generar Liquidacion enchufate: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void generarPagoLiquidacionEnchufateEntity(CallbackDataStore<PayLiquidationEntity> poCallback, String tokenSync, RequestPagoLiquidacionEnchufateEntity poRequestPagoLiquidacionEnchufateEntity) {
        throw new UnsupportedOperationException();
    }
}




