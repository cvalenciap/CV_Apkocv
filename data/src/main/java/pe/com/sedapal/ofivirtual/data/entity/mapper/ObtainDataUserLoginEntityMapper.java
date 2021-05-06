package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ObtainDataUserLogin;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 27/03/2019
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class ObtainDataUserLoginEntityMapper {

    /**
     * Constructs a {@link ObtainDataUserLoginEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    @Inject
    ObtainDataUserLoginEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poNewUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 27/03/2019
     */
    public ObtainDataUserLogin mapToEntity(ObtainDataUserLoginEntity poNewUserEntity) {
        ObtainDataUserLogin loObtainDataUserLogin = null;

        if (poNewUserEntity != null) {
            loObtainDataUserLogin = ParseUtil.parseObject(poNewUserEntity, ObtainDataUserLogin.class);

        }
        return loObtainDataUserLogin;
    }

    public List<ObtainDataUserLogin> mapToEntity(List<ObtainDataUserLoginEntity> poObtainDataUserLoginEntity) {

        final List<ObtainDataUserLogin> loDocumentType;

        if (poObtainDataUserLoginEntity != null && !poObtainDataUserLoginEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poObtainDataUserLoginEntity.size());
            for (ObtainDataUserLoginEntity loNewUserTypeEntity : poObtainDataUserLoginEntity) {
                final ObtainDataUserLogin loObtainDataUserLoginDto = mapToEntity(loNewUserTypeEntity);
                if (loObtainDataUserLoginDto != null) {
                    loDocumentType.add(loObtainDataUserLoginDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
