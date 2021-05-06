package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.DistrictEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class DistrictEntityMapper {

    /**
     * Constructs a {@link DistrictEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    DistrictEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poDistrictEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public District mapToEntity(DistrictEntity poDistrictEntity) {
        District loDistrict = null;

        if (poDistrictEntity != null) {
            loDistrict = ParseUtil.parseObject(poDistrictEntity, District.class);

        }
        return loDistrict;
    }

    public List<District> mapToEntity(List<DistrictEntity> poListDocumentTypeEntity) {

        final List<District> loDocumentType;

        if (poListDocumentTypeEntity != null && !poListDocumentTypeEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poListDocumentTypeEntity.size());
            for (DistrictEntity loDistrictTypeEntity : poListDocumentTypeEntity) {
                final District loDistrictDto = mapToEntity(loDistrictTypeEntity);
                if (loDistrictDto != null) {
                    loDocumentType.add(loDistrictDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
