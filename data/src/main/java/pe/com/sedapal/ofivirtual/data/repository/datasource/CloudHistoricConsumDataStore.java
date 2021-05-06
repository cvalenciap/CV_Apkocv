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
 * * {@link CloudHistoricConsumDataStore} implementation based on connections to the api (Cloud).
 * <p>
 * Created by jsaenz on 11/03/2019.
 */

public class CloudHistoricConsumDataStore extends RestBase implements NisDataStore{

    private static final String TAG = CloudHistoricConsumDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    public CloudHistoricConsumDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getListHistoricConsum(CallbackDataStore<List<HistoricConsumEntity>> poCallback, String poToken, RequestHistoricConsumEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Lista historico consumo");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_suministros);
        String lsEndpoint = getContext().getString(R.string.endpoint_historico_consumo);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<List<HistoricConsumEntity>>> loCall =
                getRestApi().listHistoricConsum(lsUrl, poToken, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<List<HistoricConsumEntity>> loRestReceptor = new RestReceptor<List<HistoricConsumEntity>>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<List<HistoricConsumEntity>>() {
            @Override
            public void onResponse(List<HistoricConsumEntity> poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN -   Historico consumo: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN -   Lista ListHistoricConsum: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getListNis(CallbackDataStore<List<NisEntity>> poCallback, String poToken, RequestNisEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getNisDetail(CallbackDataStore<NisDetailEntity> poCallback, String poToken, RequestNisDetailEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}
