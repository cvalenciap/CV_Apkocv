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
 * Created by jsaenz on 19/03/2019
 */

public class CloudListSubsidiaryDataStore extends RestBase implements PayPlaceDataStore {

    private static final String TAG = CloudListSubsidiaryDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 19/03/2019
     */
    public CloudListSubsidiaryDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getListAgency(CallbackDataStore<List<AgencyEntity>> poCallback, String token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getListSubsidiary(CallbackDataStore<List<SubsidiaryEntity>> poCallback, String token, RequestSubsidiaryEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Obtener Sucursales");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_lugares_pago);
        String lsEndpoint = getContext().getString(R.string.endpoint_lista_sucursales);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<List<SubsidiaryEntity>>> loCall =
                getRestApi().getListSubsidiary(lsUrl, token, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<List<SubsidiaryEntity>> loRestReceptor = new RestReceptor<List<SubsidiaryEntity>>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<List<SubsidiaryEntity>>() {
            @Override
            public void onResponse(List<SubsidiaryEntity> poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Obtener Sucursales: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Obtener Sucursales: onError");
                poCallback.onError(poException);
            }
        });
    }
}




