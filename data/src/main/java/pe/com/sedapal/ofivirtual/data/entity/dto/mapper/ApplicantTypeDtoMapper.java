package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import pe.com.sedapal.ofivirtual.data.entity.ApplicantTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.ApplicantTypeDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by jsaenz on 13/01/2017.
 * <p>
 * Mapper class used to mapFromDto {@link ApplicantTypeDto} (in the data database) to {@link ApplicantTypeEntity} in the
 * data layer.
 */
@Singleton
public class ApplicantTypeDtoMapper {

    /**
     * Constructs a {@link ApplicantTypeDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public ApplicantTypeDtoMapper() {
    }


    /**
     * Transform a {@link ApplicantTypeDto} into an {@link ApplicantTypeEntity}.
     *
     * @param poApplicantTypeDto Object to be transformed.
     * @return {@link ApplicantTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public ApplicantTypeEntity mapFromDto(ApplicantTypeDto poApplicantTypeDto) {
        ApplicantTypeEntity loApplicantTypeEntity = null;

        if (poApplicantTypeDto != null) {
            loApplicantTypeEntity = ParseUtil.parseObject(poApplicantTypeDto, ApplicantTypeEntity.class);
        }
        return loApplicantTypeEntity;
    }

    /**
     * Transform a {@link ApplicantTypeDto} into an {@link ApplicantTypeEntity}.
     *
     * @param paoApplicantTypeDto List of Object to be transformed.
     * @return {@link ApplicantTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<ApplicantTypeEntity> mapFromDto(List<ApplicantTypeDto> paoApplicantTypeDto) {
        final List<ApplicantTypeEntity> laoApplicantTypeEntity;

        if (paoApplicantTypeDto != null && !paoApplicantTypeDto.isEmpty()) {
            laoApplicantTypeEntity = new ArrayList<>(paoApplicantTypeDto.size());
            for (ApplicantTypeDto loApplicantTypeDto : paoApplicantTypeDto) {
                final ApplicantTypeEntity loApplicantTypeEntity = mapFromDto(loApplicantTypeDto);
                if (loApplicantTypeEntity != null) {
                    laoApplicantTypeEntity.add(loApplicantTypeEntity);
                }
            }
        } else {
            laoApplicantTypeEntity = Collections.emptyList();
        }

        return laoApplicantTypeEntity;
    }


    /**
     * Transform a {@link ApplicantTypeEntity} into an {@link ApplicantTypeDto}.
     *
     * @param poApplicantTypeEntity Object to be transformed.
     * @return {@link ApplicantTypeDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public ApplicantTypeDto mapToDto(ApplicantTypeEntity poApplicantTypeEntity) {
        ApplicantTypeDto loApplicantTypeDto = null;

        if (poApplicantTypeEntity != null) {
            loApplicantTypeDto = ParseUtil.parseObject(poApplicantTypeEntity, ApplicantTypeDto.class);
        }
        return loApplicantTypeDto;
    }

    /**
     * Transform a {@link ApplicantTypeEntity} into an {@link ApplicantTypeDto}.
     *
     * @param paoApplicantTypeEntity List of Object to be transformed.
     * @return {@link ApplicantTypeDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<ApplicantTypeDto> mapToDto(List<ApplicantTypeEntity> paoApplicantTypeEntity) {
        final List<ApplicantTypeDto> laoApplicantTypeDto;

        if (paoApplicantTypeEntity != null && !paoApplicantTypeEntity.isEmpty()) {
            laoApplicantTypeDto = new ArrayList<>(paoApplicantTypeEntity.size());
            for (ApplicantTypeEntity loApplicantTypeEntity : paoApplicantTypeEntity) {
                final ApplicantTypeDto loApplicantTypeDto = mapToDto(loApplicantTypeEntity);
                if (loApplicantTypeDto != null) {
                    laoApplicantTypeDto.add(loApplicantTypeDto);
                }
            }
        } else {
            laoApplicantTypeDto = Collections.emptyList();
        }

        return laoApplicantTypeDto;
    }
}
