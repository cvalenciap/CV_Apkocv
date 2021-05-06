package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ObtainDataUserLogin;
import pe.com.sedapal.ofivirtual.model.ObtainDataUserLoginModel;

/**
 * Mapper class used to transformCostData {@link ObtainDataUserLoginModel} (in the domain layer) to {@link ObtainDataUserLogin} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class ObtainDataUserLoginMapper {

    /**
     * Constructs a {@link ObtainDataUserLoginMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ObtainDataUserLoginMapper() {

    }

    /**
     * Transform a {@link ObtainDataUserLoginModel} into an {@link ObtainDataUserLogin}.
     *
     * @param poObtainDataUserLoginModel Object to be transformed.
     * @return {@link ObtainDataUserLogin}.
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    public ObtainDataUserLogin transform(ObtainDataUserLoginModel poObtainDataUserLoginModel) {

        ObtainDataUserLogin loObtainDataUserLogin = null;
        if (poObtainDataUserLoginModel != null) {
            loObtainDataUserLogin = ParseUtil.parseObject(poObtainDataUserLoginModel, ObtainDataUserLogin.class);
        }
        return loObtainDataUserLogin;
    }

    public ObtainDataUserLoginModel transform(ObtainDataUserLogin poObtainDataUserLogin) {

        ObtainDataUserLoginModel loObtainDataUserLoginModel = null;
        if (poObtainDataUserLogin != null) {
            loObtainDataUserLoginModel = ParseUtil.parseObject(poObtainDataUserLogin, ObtainDataUserLoginModel.class);
        }
        return loObtainDataUserLoginModel;
    }
}
