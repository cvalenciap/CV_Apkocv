package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class UserEntityMapper {

    /**
     * Constructs a {@link UserEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    UserEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public User mapToEntity(UserEntity poUserEntity) {
        User loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, User.class);

        }
        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poUser Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public UserEntity mapToEntity(User poUser) {
        UserEntity loUserEntity = null;

        if (poUser != null) {
            loUserEntity = ParseUtil.parseObject(poUser, UserEntity.class);
        }
        return loUserEntity;
    }


}
