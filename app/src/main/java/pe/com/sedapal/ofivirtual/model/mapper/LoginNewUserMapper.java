package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;

import javax.inject.Inject;

/**
 * Mapper class used to transformCostData {@link LoginNewUserModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class LoginNewUserMapper {

    /**
     * Constructs a {@link LoginNewUserMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public LoginNewUserMapper() {

    }

    /**
     * Transform a {@link LoginNewUserModel} into an {@link LoginNewUser}.
     *
     * @param poLoginNewUser Object to be transformed.
     * @return {@link LoginNewUser}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public LoginNewUserModel transform(LoginNewUser poLoginNewUser) {

        LoginNewUserModel loLoginNewUserModel = null;
        if (poLoginNewUser != null) {
            loLoginNewUserModel = ParseUtil.parseObject(poLoginNewUser, LoginNewUserModel.class);
        }
        return loLoginNewUserModel;
    }
}
