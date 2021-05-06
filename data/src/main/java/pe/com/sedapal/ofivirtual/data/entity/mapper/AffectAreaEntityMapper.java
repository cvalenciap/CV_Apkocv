package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.AffectAreaEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.AffectArea;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class AffectAreaEntityMapper {

    /**
     * Constructs a {@link AffectAreaEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    AffectAreaEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsenz
     * @version 1.0
     * @since 07/12/2019
     */
    public AffectArea mapToEntity(AffectAreaEntity poUserEntity) {
        AffectArea loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, AffectArea.class);

        }
        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 07/12/2019
     */
    public List<AffectArea> mapToEntity(List<AffectAreaEntity> poAffectAreaEntity) {

        final List<AffectArea> loDocumentType;

        if (poAffectAreaEntity != null && !poAffectAreaEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poAffectAreaEntity.size());
            for (AffectAreaEntity loAffectAreaTypeEntity : poAffectAreaEntity) {
                final AffectArea loAffectAreaDto = mapToEntity(loAffectAreaTypeEntity);
                if (loAffectAreaDto != null) {
                    loDocumentType.add(loAffectAreaDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }

}
