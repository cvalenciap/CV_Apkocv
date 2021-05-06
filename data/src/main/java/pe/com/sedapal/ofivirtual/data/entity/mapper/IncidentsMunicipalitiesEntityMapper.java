package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.IncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.MunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.IncidentsMunicipalities;
import pe.com.sedapal.ofivirtual.domain.entity.Municipalities;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class IncidentsMunicipalitiesEntityMapper {

    /**
     * Constructs a {@link IncidentsMunicipalitiesEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    IncidentsMunicipalitiesEntityMapper() {
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
    public IncidentsMunicipalities mapToEntity(IncidentsMunicipalitiesEntity poUserEntity) {
        IncidentsMunicipalities loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, IncidentsMunicipalities.class);

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
    public List<IncidentsMunicipalities> mapToEntity(List<IncidentsMunicipalitiesEntity> poIncidentsMunicipalitiesEntity) {

        final List<IncidentsMunicipalities> loDocumentType;

        if (poIncidentsMunicipalitiesEntity != null && !poIncidentsMunicipalitiesEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poIncidentsMunicipalitiesEntity.size());
            for (IncidentsMunicipalitiesEntity loIncidentsMunicipalitiesTypeEntity : poIncidentsMunicipalitiesEntity) {
                final IncidentsMunicipalities loIncidentsMunicipalitiesDto = mapToEntity(loIncidentsMunicipalitiesTypeEntity);
                if (loIncidentsMunicipalitiesDto != null) {
                    loDocumentType.add(loIncidentsMunicipalitiesDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }

}
