package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.AgencyEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Agency;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 19/02/2019
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class AgencyEntityMapper {

    /**
     * Constructs a {@link AgencyEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 09/03/2019
     */
    @Inject
    AgencyEntityMapper() {
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
    public Agency mapToEntity(AgencyEntity poUserEntity) {
        Agency loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, Agency.class);

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
    public List<Agency> mapToEntity(List<AgencyEntity> poListAgencyEntity) {

        final List<Agency> loAgency;

        if (poListAgencyEntity != null && !poListAgencyEntity.isEmpty()) {
            loAgency = new ArrayList<>(poListAgencyEntity.size());
            for (AgencyEntity loAgencyTypeEntity : poListAgencyEntity) {
                final Agency loAgencyDto = mapToEntity(loAgencyTypeEntity);
                if (loAgencyDto != null) {
                    loAgency.add(loAgencyDto);
                }
            }
        } else {
            loAgency = Collections.emptyList();
        }

        return loAgency;
    }


}
