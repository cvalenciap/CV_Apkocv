package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.entity.ObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ObtenerDatosPago;
import pe.com.sedapal.ofivirtual.domain.entity.User;

public class ObtenerDatosPagoEntityMapper {
    @Inject
    ObtenerDatosPagoEntityMapper() {
    }

    public ObtenerDatosPago mapToEntity(ObtenerDatosPagoEntity poObtenerDatosPagoEntity) {
        ObtenerDatosPago loUser = null;

        if (poObtenerDatosPagoEntity != null) {
            loUser = ParseUtil.parseObject(poObtenerDatosPagoEntity, ObtenerDatosPago.class);
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
    public ObtenerDatosPagoEntity mapToEntity(ObtenerDatosPago poRegisterUser) {
        ObtenerDatosPagoEntity loRequestRegUserEntity = null;

        if (poRegisterUser != null) {
            loRequestRegUserEntity = ParseUtil.parseObject(poRegisterUser, ObtenerDatosPagoEntity.class);
        }
        return loRequestRegUserEntity;
    }

}
