package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.model.UserModel;

import javax.inject.Inject;

/**
 * Mapper class used to transformCostData {@link User} (in the domain layer) to {@link UserModel} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class UserModelMapper {

    /**
     * Constructs a {@link UserModelMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public UserModelMapper() {

    }

    /**
     * Transform a {@link User} into an {@link UserModel}.
     *
     * @param poUser Object to be transformed.
     * @return {@link UserModel}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public UserModel transform(User poUser) {

        UserModel loUserModel = null;
        if (poUser != null) {
            loUserModel = ParseUtil.parseObject(poUser, UserModel.class);
        }
        return loUserModel;
    }
}
