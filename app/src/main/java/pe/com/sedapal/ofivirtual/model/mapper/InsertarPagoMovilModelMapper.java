package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.InsertarPagoMovil;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.model.InsertarPagoMovilModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;

/**
 * Mapper class used to transformCostData {@link LoginNewUserModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by jsaenz on 09/04/2019.
 */
public class InsertarPagoMovilModelMapper {

    /**
     * Constructs a {@link InsertarPagoMovilModelMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 09/04/2019
     */
    @Inject
    public InsertarPagoMovilModelMapper() {

    }


    public InsertarPagoMovilModel transform(InsertarPagoMovil poInsertarPagoMovil) {

        InsertarPagoMovilModel loInsertarPagoMovilModel = null;
        if (poInsertarPagoMovil != null) {
            loInsertarPagoMovilModel = ParseUtil.parseObject(poInsertarPagoMovil, InsertarPagoMovilModel.class);
        }
        return loInsertarPagoMovilModel;
    }
}
