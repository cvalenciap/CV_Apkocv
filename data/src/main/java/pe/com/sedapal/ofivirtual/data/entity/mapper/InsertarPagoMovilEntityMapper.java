package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.entity.RequestInsertarPagoMovil;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.InsertarPagoMovil;
import pe.com.sedapal.ofivirtual.domain.entity.User;

public class InsertarPagoMovilEntityMapper {


    @Inject
    InsertarPagoMovilEntityMapper() {
    }


    public InsertarPagoMovil mapToEntity(RequestInsertarPagoMovil poPagoVisaEnvioCorreoEntity) {
        InsertarPagoMovil loUser = null;

        if (poPagoVisaEnvioCorreoEntity != null) {
            loUser = ParseUtil.parseObject(poPagoVisaEnvioCorreoEntity, InsertarPagoMovil.class);

        }


        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poRegisterUser Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public RequestInsertarPagoMovil mapToEntity(InsertarPagoMovil poRegisterUser) {
        RequestInsertarPagoMovil loRequestRegUserEntity = null;

        if (poRegisterUser != null) {
            loRequestRegUserEntity = ParseUtil.parseObject(poRegisterUser, RequestInsertarPagoMovil.class);
        }
        return loRequestRegUserEntity;
    }






}
