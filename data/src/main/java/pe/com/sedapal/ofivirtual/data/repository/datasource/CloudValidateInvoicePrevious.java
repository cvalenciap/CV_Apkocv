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
 * * {@link CloudValidateInvoicePrevious} implementation based on connections to the api (Cloud).
 * <p>
 * Created by jsaenz on 11/03/2019.
 */

public class CloudValidateInvoicePrevious extends RestBase implements InvoicesDataStore {

    private static final String TAG = CloudValidateInvoicePrevious.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    public CloudValidateInvoicePrevious(Context poContext) {
        super(poContext);
    }

    @Override
    public void validateInvoicePrevious(CallbackDataStore<String> poCallback, String poToken, RequestValidateInvoicePreviousEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Validate Invoice Previous");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_recibos);
        String lsEndpoint = getContext().getString(R.string.endpoint_validar_recibo_anterior);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<String>> loCall =
                getRestApi().validateInvoicePrevious(lsUrl, poToken, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<String> loRestReceptor = new RestReceptor<String>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<String>() {
            @Override
            public void onResponse(String poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Validate Invoice Previous: onResponse");
                if(poData.equalsIgnoreCase("false")){
                    poCallback.onSuccess("0");
                }else {
                    poCallback.onSuccess("1");
                }
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Validate Invoice Previous: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getPendingInvoices(CallbackDataStore<List<PendingInvoicesEntity>> poCallback, String token, RequestPendingInvoicesEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getPayInvoices(CallbackDataStore<List<PayInvoicesEntity>> poCallback, String token, RequestPayInvoicesEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getDetailInvoices(CallbackDataStore<List<DetailInvoicesEntity>> poCallback, String token, RequestDetailInvoicesEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getDetailPayInvoice(CallbackDataStore<List<DetailPayInvoiceEntity>> poCallback, String token, RequestDetailPayInvoiceEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}
