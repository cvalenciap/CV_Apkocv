package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;
import android.util.Log;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.ApplicantTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.CardTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosVisaEntity;
import pe.com.sedapal.ofivirtual.data.entity.DistrictEntity;
import pe.com.sedapal.ofivirtual.data.entity.DocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.ImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.ImagenesOnboardingEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestParamCategoryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SecretQuestionEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
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

public class CloudMasterDataStore extends RestBase implements MasterDataStore {

    private static final String TAG = CloudMasterDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public CloudMasterDataStore(Context poContext) {
        super(poContext);
    }


    @Override
    public void sync(CallbackDataStore<SyncEntity> poCallback) {
        LogUtil.i(TAG, "INICIO - sync");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_general);
        String lsEndpoint = getContext().getString(R.string.endpoint_carga_pre_definida);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<SyncEntity>> loCall =
                getRestApi().sync(lsUrl);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<SyncEntity> loRestReceptor = new RestReceptor<SyncEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<SyncEntity>() {
            @Override
            public void onResponse(SyncEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - sync: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - sync: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void saveDistricts(List<DistrictEntity> paoDistrictEntities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveDocumentTypes(List<DocumentTypeEntity> paoDocumentTypeEntities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveApplicantType(List<ApplicantTypeEntity> paoApplicantTypeEntities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveSecretQuestions(List<SecretQuestionEntity> paoSecretQuestionEntityEntities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveCardType(List<CardTypeEntity> paoCardTypeEntities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void saveImagenesOnboarding(ImagenesEntity paoImagenesEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DistrictEntity> getListDistricts() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DocumentTypeEntity> getListDocumentTypeEntity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ApplicantTypeEntity> getListApplicantTypeEntity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SecretQuestionEntity> getListSecretQuestionEntity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<CardTypeEntity> getListCardTypeEntity() {
        return null;
    }

    @Override
    public List<ImagenesOnboardingEntity> getListImagenesOnboardingEntity() {
        return null;
    }

    @Override
    public void getDatosVisaEntity(CallbackDataStore<DatosVisaEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void getDatosImagenesEntity(CallbackDataStore<DatosImagenesEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity) {
        throw new UnsupportedOperationException();
    }

}
