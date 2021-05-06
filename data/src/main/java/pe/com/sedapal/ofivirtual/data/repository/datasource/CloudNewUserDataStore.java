package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.SessionUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
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
 * Created by jsaenz on 23/01/2017.
 */

public class CloudNewUserDataStore extends RestBase implements UserDataStore {

    private static final String TAG = CloudNewUserDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public CloudNewUserDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public SessionUserEntity obtainUserSession() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveUserSession(RequestNewUserLoginEntity poNewUserLoginEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean deleteUserSession() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loginNewUser(CallbackDataStore<NewUserLoginEntity> poCallback, String poToken, RequestNewUserLoginEntity poRequest) {
        LogUtil.i(TAG, "INICIO - Autenticacion de nuevo usuario");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_autenticacion_usuario);
        String lsEndpoint = getContext().getString(R.string.endpoint_aut_nuevo_usu);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<NewUserLoginEntity>> loCall =
                getRestApi().loginNewUser(lsUrl, poToken, poRequest);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<NewUserLoginEntity> loRestReceptor = new RestReceptor<NewUserLoginEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<NewUserLoginEntity>() {
            @Override
            public void onResponse(NewUserLoginEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN -  Autenticacion de nuevo usuario: onResponse");
                poData.setDescRespuesta(poBaseResponseEntity.getMsg());
                poData.setPendingConfirmRegister(poBaseResponseEntity.getCodResult() == 1 ? false : true);
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN -  Autenticacion de nuevo usuario: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void loginSupply(CallbackDataStore<SupplyLoginEntity> poCallback, String poToken, RequestSupplyLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dataUser(CallbackDataStore<ObtainDataUserLoginEntity> poCallback, String poToken, RequestObtainDataUserLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

}
