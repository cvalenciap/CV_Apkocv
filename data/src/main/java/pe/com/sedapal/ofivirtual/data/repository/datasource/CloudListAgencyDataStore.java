package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.AgencyEntity;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SubsidiaryEntity;
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

public class CloudListAgencyDataStore extends RestBase implements PayPlaceDataStore {

    private static final String TAG = CloudListAgencyDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudListAgencyDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getListAgency(CallbackDataStore<List<AgencyEntity>> poCallback, String token) {
        LogUtil.i(TAG, "INICIO - Obtener Agencias");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_lugares_pago);
        String lsEndpoint = getContext().getString(R.string.endpoint_lista_agencias);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<List<AgencyEntity>>> loCall =
                getRestApi().getListAgency(lsUrl, token);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<List<AgencyEntity>> loRestReceptor = new RestReceptor<List<AgencyEntity>>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<List<AgencyEntity>>() {
            @Override
            public void onResponse(List<AgencyEntity> poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Obtener Agencias: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Obtener Agencias: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getListSubsidiary(CallbackDataStore<List<SubsidiaryEntity>> poCallback, String token, RequestSubsidiaryEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}




