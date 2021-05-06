package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.SubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Subsidiary;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 19/03/2019
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class SubsidiaryEntityMapper {

    /**
     * Constructs a {@link SubsidiaryEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 19/03/2019
     */
    @Inject
    SubsidiaryEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsenz
     * @version 1.0
     * @since 19/03/2019
     */
    public Subsidiary mapToEntity(SubsidiaryEntity poUserEntity) {
        Subsidiary loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, Subsidiary.class);

        }
        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 19/03/2019
     */
    public List<Subsidiary> mapToEntity(List<SubsidiaryEntity> poListSubsidiaryEntity) {

        final List<Subsidiary> loSubsidiary;

        if (poListSubsidiaryEntity != null && !poListSubsidiaryEntity.isEmpty()) {
            loSubsidiary = new ArrayList<>(poListSubsidiaryEntity.size());
            for (SubsidiaryEntity loSubsidiaryTypeEntity : poListSubsidiaryEntity) {
                final Subsidiary loSubsidiaryDto = mapToEntity(loSubsidiaryTypeEntity);
                if (loSubsidiaryDto != null) {
                    loSubsidiary.add(loSubsidiaryDto);
                }
            }
        } else {
            loSubsidiary = Collections.emptyList();
        }

        return loSubsidiary;
    }


}
