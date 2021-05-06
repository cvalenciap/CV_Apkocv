package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.SessionUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.SessionUser;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class SessionUserEntityMapper {

    /**
     * Constructs a {@link SessionUserEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    SessionUserEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poNewUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public SessionUser mapToEntity(SessionUserEntity poNewUserEntity) {
        SessionUser loSessionUser = null;

        if (poNewUserEntity != null) {
            loSessionUser = ParseUtil.parseObject(poNewUserEntity, SessionUser.class);

        }
        return loSessionUser;
    }

    public List<SessionUser> mapToEntity(List<SessionUserEntity> poSessionUserEntity) {

        final List<SessionUser> loDocumentType;

        if (poSessionUserEntity != null && !poSessionUserEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poSessionUserEntity.size());
            for (SessionUserEntity loNewUserTypeEntity : poSessionUserEntity) {
                final SessionUser loSessionUserDto = mapToEntity(loNewUserTypeEntity);
                if (loSessionUserDto != null) {
                    loDocumentType.add(loSessionUserDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


    public SessionUserEntity mapToEntity(SessionUser poNewUser) {
        SessionUserEntity loSessionUser = null;

        if (poNewUser != null) {
            loSessionUser = ParseUtil.parseObject(poNewUser, SessionUserEntity.class);

        }
        return loSessionUser;
    }

}
