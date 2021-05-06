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

public class CloudPagoLiquidacionEnchufateDataStore extends RestBase implements EnchufateDataStore {

    private static final String TAG = CloudPagoLiquidacionEnchufateDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public CloudPagoLiquidacionEnchufateDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void generarPagoLiquidacionEnchufateEntity(CallbackDataStore<PayLiquidationEntity> poCallback, String tokenSync, RequestPagoLiquidacionEnchufateEntity poRequestPagoLiquidacionEnchufateEntity) {
        LogUtil.i(TAG, "INICIO - Pagar liquidacion enchufate");

        String lsEndpoint = getContext().getString(R.string.endpoint_ocv_pago_liquidacion);
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_enchufate);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<PayLiquidationEntity>> loCall = getRestApi().pagarLiquidacion(lsUrl,
                tokenSync,
                poRequestPagoLiquidacionEnchufateEntity);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<PayLiquidationEntity> loRestReceptor = new RestReceptor<PayLiquidationEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<PayLiquidationEntity>() {
            @Override
            public void onResponse(PayLiquidationEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Pagar Liquidacion enchufate: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Pagar Liquidacion enchufate: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void generarLiquidacionEnchufateEntity(CallbackDataStore<LiquidacionEntity> poCallback, String token, RequestLiquidacionEnchufateEntity poDocumentos) {
        throw new UnsupportedOperationException();
    }

}