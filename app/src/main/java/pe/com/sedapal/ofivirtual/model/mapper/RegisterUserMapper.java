package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.RegisterUser;
import pe.com.sedapal.ofivirtual.model.RegisterUserModel;

import javax.inject.Inject;

/**
 * Mapper class used to transformCostData {@link RegisterUserModel} (in the domain layer) to {@link RegisterUser} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class RegisterUserMapper {

    /**
     * Constructs a {@link RegisterUserMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public RegisterUserMapper() {

    }

    /**
     * Transform a {@link RegisterUserModel} into an {@link RegisterUser}.
     *
     * @param poRegisterUserModel Object to be transformed.
     * @return {@link RegisterUser}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public RegisterUser transform(RegisterUserModel poRegisterUserModel) {

        RegisterUser loRegisterUser = null;
        if (poRegisterUserModel != null) {
            loRegisterUser = ParseUtil.parseObject(poRegisterUserModel, RegisterUser.class);
        }
        return loRegisterUser;
    }
}
