package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.IncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.MunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestIncidentsMunicipalitiesEntity;
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

public class CloudListMunicipalitiesDataStore extends RestBase implements IncidentsDataStore{

    private static final String TAG = CloudListMunicipalitiesDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public CloudListMunicipalitiesDataStore(Context poContext) {
        super(poContext);
    }
    
    @Override
    public void getListMunicipalities(CallbackDataStore<List<MunicipalitiesEntity>> poCallback, String token) {
        LogUtil.i(TAG, "INICIO - Obtener Lista de Municiplalidades");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_incidencias);
        String lsEndpoint = getContext().getString(R.string.endpoint_municipios_afectados);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<List<MunicipalitiesEntity>>> loCall =
                getRestApi().getListMunicipalities(lsUrl, token);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<List<MunicipalitiesEntity>> loRestReceptor = new RestReceptor<List<MunicipalitiesEntity>>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<List<MunicipalitiesEntity>>() {
            @Override
            public void onResponse(List<MunicipalitiesEntity> poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Obtener Lista de Municiplalidades: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Obtener Lista de Municiplalidades: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getListIncidentsMunicipalities(CallbackDataStore<List<IncidentsMunicipalitiesEntity>> poCallback, String token, RequestIncidentsMunicipalitiesEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}




