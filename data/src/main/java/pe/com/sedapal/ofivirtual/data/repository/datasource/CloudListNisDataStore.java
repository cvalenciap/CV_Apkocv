package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.HistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestHistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.net.RestCallback;
import pe.com.sedapal.ofivirtual.data.net.RestReceptor;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;

/**
 * * {@link CloudListNisDataStore} implementation based on connections to the api (Cloud).
 * <p>
 * Created by jsaenz on 11/03/2019.
 */

public class CloudListNisDataStore extends RestBase implements NisDataStore {

    private static final String TAG = CloudListNisDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    public CloudListNisDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getListNis(CallbackDataStore<List<NisEntity>> poCallback, String poToken, RequestNisEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Lista ListNis");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_suministros);
        String lsEndpoint = getContext().getString(R.string.endpoint_lista_nis);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<List<NisEntity>>> loCall =
                getRestApi().listNis(lsUrl, poToken, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<List<NisEntity>> loRestReceptor = new RestReceptor<List<NisEntity>>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<List<NisEntity>>() {
            @Override
            public void onResponse(List<NisEntity> poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN -   Lista ListNis: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN -   Lista ListNis: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getNisDetail(CallbackDataStore<NisDetailEntity> poCallback, String poToken, RequestNisDetailEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getListHistoricConsum(CallbackDataStore<List<HistoricConsumEntity>> poCallback, String poToken, RequestHistoricConsumEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}
