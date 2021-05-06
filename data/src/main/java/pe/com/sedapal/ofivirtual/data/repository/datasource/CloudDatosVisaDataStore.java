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
 * Created by jsaenz
 */

public class CloudDatosVisaDataStore extends RestBase implements MasterDataStore {

    private static final String TAG = CloudDatosVisaDataStore.class.getSimpleName();

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public CloudDatosVisaDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getDatosVisaEntity(CallbackDataStore<DatosVisaEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity) {

        LogUtil.i(TAG, "INICIO - Datos visa");
        String lsContext = getContext().getString(R.string.context_multipago);
        String lsWs = getContext().getString(R.string.ws_general);
        String lsEndpoint = getContext().getString(R.string.endpoint_parametros_categoria);
        String lsUrl = String.format("%s%s%s", lsContext, lsWs, lsEndpoint);

        Call<BaseResponseEntity<DatosVisaEntity>> loCall =
                getRestApi().getDatosVisa(lsUrl, poToken, poRequestParamCategoryEntity);

        Log.i("URL Obtenida", loCall.request().url().toString());

        RestReceptor<DatosVisaEntity> loRestReceptor = new RestReceptor<DatosVisaEntity>(getContext());

        loRestReceptor.invoke(loCall, new RestCallback<DatosVisaEntity>() {
            @Override
            public void onResponse(DatosVisaEntity poData, BaseResponseEntity poBaseResponseEntity) {
                LogUtil.i(TAG, "FIN - Datos visa: onResponse");
                poCallback.onSuccess(poData);
            }

            @Override
            public void onError(BaseException poException) {
                LogUtil.i(TAG, "FIN - Datos visa: onError");
                poCallback.onError(poException);
            }
        });
    }

    @Override
    public void getDatosImagenesEntity(CallbackDataStore<DatosImagenesEntity> poCallback, String poToken, RequestParamCategoryEntity poRequestParamCategoryEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sync(CallbackDataStore<SyncEntity> poCallback) {
        throw new UnsupportedOperationException();
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

}