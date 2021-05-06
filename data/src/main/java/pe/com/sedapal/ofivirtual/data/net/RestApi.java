package pe.com.sedapal.ofivirtual.data.net;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.entity.AgencyEntity;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DatosVisaEntity;
import pe.com.sedapal.ofivirtual.data.entity.DetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.ForgetPasswordSupplyEntity;
import pe.com.sedapal.ofivirtual.data.entity.HistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.IncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.LiquidacionEntity;
import pe.com.sedapal.ofivirtual.data.entity.MunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayLiquidationEntity;
import pe.com.sedapal.ofivirtual.data.entity.PendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestForgetPasswordEmail;
import pe.com.sedapal.ofivirtual.data.entity.RequestForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.data.entity.RequestHistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestIncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestInsertarPagoMovil;
import pe.com.sedapal.ofivirtual.data.entity.RequestInvoicePdfEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPagoLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestParamCategoryEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPayRefEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestRegUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSendConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestUpdateDataUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidDocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidSupplyEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateInvoicePreviousEntity;
import pe.com.sedapal.ofivirtual.data.entity.SubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.TokenEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.entity.ValidDocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.ValidateVersionEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by jsaenz on 4/01/2017.
 */

public interface RestApi {

    /**
     * Service login user.
     *
     * @param psUrl  endpoint defined
     * @param poBody {@link RequestLoginEntity}
     * @return Call {@link Call} of {@link BaseResponseEntity} of {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 27/11/2018
     */
    @POST("{url}")
    Call<BaseResponseEntity<UserEntity>> login(
            @Path(value = "url", encoded = true) String psUrl, @Body RequestLoginEntity poBody);


    /**
     * Service login user.
     *
     * @param psUrl  endpoint defined
     * @return Call {@link Call} of {@link BaseResponseEntity} of {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 27/11/2018
     */
    @GET("{url}")
    Call<BaseResponseEntity<SyncEntity>> sync(
            @Path(value = "url", encoded = true) String psUrl);

    //@POST("{url}")
    //Call<BaseResponseEntity<SyncEntity>> sync(
    //        @Path(value = "url", encoded = true) String psUrl, @Body RequestSyncEntity poBody);

    @FormUrlEncoded
    @POST("{url}")
    Call<BaseResponseEntity<TokenEntity>> getToken(
            @Path(value = "url", encoded = true) String psUrl, @Field("username") String username, @Field("password") String password);

    @POST("{url}")
    Call<BaseResponseEntity<String>> validSupply(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestValidSupplyEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<String>> registerUser(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestRegUserEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<String>> validPaymentReferency(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestPayRefEntity pBody);

    //clb
    @POST("{url}")
    Call<BaseResponseEntity<String>> pagoVisaEnviarCorreo(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestInsertarPagoMovil pBody);


    @POST("{url}")
    Call<BaseResponseEntity<String>> actualizaAnt(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestUpdateDataUserEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<ValidDocumentTypeEntity>> validDocumentType(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestValidDocumentTypeEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<NewUserLoginEntity>> loginNewUser(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestNewUserLoginEntity pBody);


    @POST("{url}")
    Call<BaseResponseEntity<SupplyLoginEntity>> loginSupply(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestSupplyLoginEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<String>> forgetPasswordEmail(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestForgetPasswordEmail pBody);

    @POST("{url}")
    Call<BaseResponseEntity<ForgetPasswordSupplyEntity>> forgetPasswordSupply(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestForgetPasswordSupply pBody);

    @POST("{url}")
    Call<BaseResponseEntity<List<PendingInvoicesEntity>>> getListPendingInvoices(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestPendingInvoicesEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<List<PayInvoicesEntity>>> getListPayInvoices(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestPayInvoicesEntity pBody);


    @POST("{url}")
    Call<BaseResponseEntity<List<NisEntity>>> listNis(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestNisEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<NisDetailEntity>> detalleNis(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestNisDetailEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<List<DetailInvoicesEntity>>> detailInvoices(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestDetailInvoicesEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<List<DetailPayInvoiceEntity>>> detailPayInvoice(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestDetailPayInvoiceEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<String>> downloadPdfInvoice(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestInvoicePdfEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<List<HistoricConsumEntity>>> listHistoricConsum(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestHistoricConsumEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<List<AgencyEntity>>> getListAgency(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token);

    @POST("{url}")
    Call<BaseResponseEntity<List<SubsidiaryEntity>>> getListSubsidiary(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestSubsidiaryEntity pBody);

    @GET("{url}")
    Call<BaseResponseEntity<List<MunicipalitiesEntity>>> getListMunicipalities(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token);

    @POST("{url}")
    Call<BaseResponseEntity<List<IncidentsMunicipalitiesEntity>>> getListIncidentsMunicipalities(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestIncidentsMunicipalitiesEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<ObtainDataUserLoginEntity>> getDataUser(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token ,@Body RequestObtainDataUserLoginEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<String>> validateInvoicePrevious(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestValidateInvoicePreviousEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<DatosVisaEntity>> getDatosVisa(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestParamCategoryEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<DatosImagenesEntity>> getDatosImagenes(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestParamCategoryEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<RequestValidateConfirmationCodeEntity>> validateConfirmationCode(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestValidateConfirmationCodeEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<String>> sendConfirmationCode(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestSendConfirmationCodeEntity pBody);

    @POST("{url}")
    Call<BaseResponseEntity<ValidateVersionEntity>> validateVersion(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body ValidateVersionEntity pBody);


    @POST("{url}")
    Call<BaseResponseEntity<ObtenerDatosPagoEntity>> obtenerDatosPago(
            @Path(value = "url", encoded = true) String psUrl, @Header("Authorization") String token, @Body RequestObtenerDatosPagoEntity pBody);


    @POST("{url}")
    Call<BaseResponseEntity<LiquidacionEntity>> generarLiquidacion(
            @Path(value = "url", encoded = true) String psUrl,
            @Header("Authorization") String basicAuth,
            @Body RequestLiquidacionEnchufateEntity documentos);

    @POST("{url}")
    Call<BaseResponseEntity<PayLiquidationEntity>> pagarLiquidacion(
            @Path(value = "url", encoded = true) String psUrl,
            @Header("Authorization") String basicAuth,
            @Body RequestPagoLiquidacionEnchufateEntity documentos);

}
