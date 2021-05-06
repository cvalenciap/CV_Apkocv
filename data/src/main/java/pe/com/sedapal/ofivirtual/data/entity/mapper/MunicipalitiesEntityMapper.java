package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.MunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Municipalities;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class MunicipalitiesEntityMapper {

    /**
     * Constructs a {@link MunicipalitiesEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    MunicipalitiesEntityMapper() {
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
    public Municipalities mapToEntity(MunicipalitiesEntity poUserEntity) {
        Municipalities loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, Municipalities.class);

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
    public List<Municipalities> mapToEntity(List<MunicipalitiesEntity> poMunicipalitiesEntity) {

        final List<Municipalities> loDocumentType;

        if (poMunicipalitiesEntity != null && !poMunicipalitiesEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poMunicipalitiesEntity.size());
            for (MunicipalitiesEntity loMunicipalitiesTypeEntity : poMunicipalitiesEntity) {
                final Municipalities loMunicipalitiesDto = mapToEntity(loMunicipalitiesTypeEntity);
                if (loMunicipalitiesDto != null) {
                    loDocumentType.add(loMunicipalitiesDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
