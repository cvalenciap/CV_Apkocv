package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.DetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.PendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateInvoicePreviousEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.net.RestCallback;
import pe.com.sedapal.ofivirtual.data.net.RestReceptor;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;

/**
 * * {@link InvoicesDataStore} implementation based on connections to the api (Cloud).
 * <p>
 * Created by jsaenz on 11/03/2019
 */

public class CloudDetailInvoicesDataStore extends RestBase implements InvoicesDataStore {

    private static final String TAG = CloudDetailInvoicesDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudDetailInvoicesDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getDetailInvoices(CallbackDataStore<List<DetailInvoicesEntity>> poCallback, String token, RequestDetailInvoicesEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Obtener Detalle Recibos");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_recibos);
        String lsEndpoint = getContext().getString(R.string.endpoint_detalle_recibos);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<List<DetailInvoicesEntity>>> loCall =
                getRestApi().detailInvoices(lsUrl, token,poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<List<DetailInvoicesEntity>> loRestReceptor = new RestReceptor<List<DetailInvoicesEntity>>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<List<DetailInvoicesEntity>>() {
            @Override
            public void onResponse(List<DetailInvoicesEntity> poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Obtener Detalle Reciboss: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Obtener Detalle Recibos: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getDetailPayInvoice(CallbackDataStore<List<DetailPayInvoiceEntity>> poCallback, String token, RequestDetailPayInvoiceEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void validateInvoicePrevious(CallbackDataStore<String> poCallback, String poToken, RequestValidateInvoicePreviousEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getPendingInvoices(CallbackDataStore<List<PendingInvoicesEntity>> poCallback, String token, RequestPendingInvoicesEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getPayInvoices(CallbackDataStore<List<PayInvoicesEntity>> poCallback, String token, RequestPayInvoicesEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}




