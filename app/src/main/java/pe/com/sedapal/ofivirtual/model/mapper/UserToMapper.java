package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.model.UserModel;

import javax.inject.Inject;

/**
 * Mapper class used to transformCostData {@link UserModel} (in the domain layer) to {@link User} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class UserToMapper {

    /**
     * Constructs a {@link UserToMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public UserToMapper() {

    }

    /**
     * Transform a {@link UserModel} into an {@link User}.
     *
     * @param poUserModel Object to be transformed.
     * @return {@link User}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public User transform(UserModel poUserModel) {

        User loUser = null;
        if (poUserModel != null) {
            loUser = ParseUtil.parseObject(poUserModel, User.class);
        }
        return loUser;
    }
}
