package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.RequestUpdateDataUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.UpdateDataUser;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class UpdateDataUserEntityMapper {

    /**
     * Constructs a {@link UpdateDataUserEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    UpdateDataUserEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsenz
     * @version 1.0
     * @since 06/12/2018
     */
    public UpdateDataUser mapToEntity(RequestUpdateDataUserEntity poUserEntity) {
        UpdateDataUser loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, UpdateDataUser.class);

        }
        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poUpdateDataUser Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public RequestUpdateDataUserEntity mapToEntity(UpdateDataUser poUpdateDataUser) {
        RequestUpdateDataUserEntity loRequestUpdateDataUserEntity = null;

        if (poUpdateDataUser != null) {
            loRequestUpdateDataUserEntity = ParseUtil.parseObject(poUpdateDataUser, RequestUpdateDataUserEntity.class);
        }
        return loRequestUpdateDataUserEntity;
    }


}
