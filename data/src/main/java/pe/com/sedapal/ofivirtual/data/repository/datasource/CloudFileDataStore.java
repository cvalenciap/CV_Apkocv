package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestInvoicePdfEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.net.RestCallback;
import pe.com.sedapal.ofivirtual.data.net.RestReceptor;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;

/**
 * Created by jsaenz on 11/03/2019
 */

public class CloudFileDataStore extends RestBase implements FileDataStore {

    private static final String TAG = CloudDetailPayInvoiceDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudFileDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getPdf(CallbackDataStore<String> poCallback, String psUrl) {

    }

    @Override
    public void downloadPdf(CallbackDataStore<String> poCallback, String token, RequestInvoicePdfEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Obtener Detalle Pagos");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_recibos);
        String lsEndpoint = getContext().getString(R.string.endpoint_recibo_pdf);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<String>> loCall =
                getRestApi().downloadPdfInvoice(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<String> loRestReceptor = new RestReceptor<String>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<String>() {
            @Override
            public void onResponse(String poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Obtener Detalle Pagos: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Obtener Detalle Pagos: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void savePdf(CallbackDataStore<String> poCallback, String poPdfFile, String psUrl) {
        throw new UnsupportedOperationException();
    }
}
