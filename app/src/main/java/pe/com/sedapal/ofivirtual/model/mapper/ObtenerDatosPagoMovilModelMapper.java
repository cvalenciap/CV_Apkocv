package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.ObtenerDatosPago;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.ObtenerDatosPagoModel;

/**
 * Mapper class used to transformCostData {@link LoginNewUserModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by jsaenz on 30/07/2020
 */
public class ObtenerDatosPagoMovilModelMapper {

    /**
     * Constructs a {@link ObtenerDatosPagoMovilModelMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 09/04/2019
     */
    @Inject
    public ObtenerDatosPagoMovilModelMapper() {

    }


    public ObtenerDatosPagoModel transform(ObtenerDatosPago poObtenerDatosPago) {

        ObtenerDatosPagoModel loObtenerDatosPagoModel = null;
        if (poObtenerDatosPago != null) {
            loObtenerDatosPagoModel = ParseUtil.parseObject(poObtenerDatosPago, ObtenerDatosPagoModel.class);
        }
        return loObtenerDatosPagoModel;
    }
}
