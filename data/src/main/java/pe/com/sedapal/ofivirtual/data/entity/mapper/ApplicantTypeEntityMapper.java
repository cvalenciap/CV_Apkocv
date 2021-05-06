package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.ApplicantTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ApplicantType;
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
public class ApplicantTypeEntityMapper {

    /**
     * Constructs a {@link ApplicantTypeEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ApplicantTypeEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poApplicantTypeEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ApplicantType mapToEntity(ApplicantTypeEntity poApplicantTypeEntity) {
        ApplicantType loApplicantType = null;

        if (poApplicantTypeEntity != null) {
            loApplicantType = ParseUtil.parseObject(poApplicantTypeEntity, ApplicantType.class);

        }
        return loApplicantType;
    }

    public List<ApplicantType> mapToEntity(List<ApplicantTypeEntity> poListDocumentTypeEntity) {

        final List<ApplicantType> loDocumentType;

        if (poListDocumentTypeEntity != null && !poListDocumentTypeEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poListDocumentTypeEntity.size());
            for (ApplicantTypeEntity loApplicantTypeTypeEntity : poListDocumentTypeEntity) {
                final ApplicantType loApplicantTypeDto = mapToEntity(loApplicantTypeTypeEntity);
                if (loApplicantTypeDto != null) {
                    loDocumentType.add(loApplicantTypeDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
