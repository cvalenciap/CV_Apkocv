package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.ImagenesOnboardingEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.ImagenesOnboardingDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;


/**
 * <p>
 * Mapper class used to mapFromDto {@link ImagenesOnboardingDto} (in the data database) to {@link ImagenesOnboardingEntity} in the
 * data layer.
 */
@Singleton
public class ImagenesOnboardingDtoMapper {

    /**
     * Constructs a {@link ImagenesOnboardingDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public ImagenesOnboardingDtoMapper() {
    }


    /**
     * Transform a {@link ImagenesOnboardingDto} into an {@link ImagenesOnboardingEntity}.
     *
     * @param poImagenesOnboardingDto Object to be transformed.
     * @return {@link ImagenesOnboardingEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public ImagenesOnboardingEntity mapFromDto(ImagenesOnboardingDto poImagenesOnboardingDto) {
        ImagenesOnboardingEntity loImagenesOnboardingEntity = null;

        if (poImagenesOnboardingDto != null) {
            loImagenesOnboardingEntity = ParseUtil.parseObject(poImagenesOnboardingDto, ImagenesOnboardingEntity.class);
        }
        return loImagenesOnboardingEntity;
    }

    /**
     * Transform a {@link ImagenesOnboardingDto} into an {@link ImagenesOnboardingEntity}.
     *
     * @param paoImagenesOnboardingDto List of Object to be transformed.
     * @return {@link ImagenesOnboardingEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<ImagenesOnboardingEntity> mapFromDto(List<ImagenesOnboardingDto> paoImagenesOnboardingDto) {
        final List<ImagenesOnboardingEntity> laoImagenesOnboardingEntity;

        if (paoImagenesOnboardingDto != null && !paoImagenesOnboardingDto.isEmpty()) {
            laoImagenesOnboardingEntity = new ArrayList<>(paoImagenesOnboardingDto.size());
            for (ImagenesOnboardingDto loImagenesOnboardingDto : paoImagenesOnboardingDto) {
                final ImagenesOnboardingEntity loImagenesOnboardingEntity = mapFromDto(loImagenesOnboardingDto);
                if (loImagenesOnboardingEntity != null) {
                    laoImagenesOnboardingEntity.add(loImagenesOnboardingEntity);
                }
            }
        } else {
            laoImagenesOnboardingEntity = Collections.emptyList();
        }

        return laoImagenesOnboardingEntity;
    }


    /**
     * Transform a {@link ImagenesOnboardingEntity} into an {@link ImagenesOnboardingDto}.
     *
     * @param poImagenesOnboardingEntity Object to be transformed.
     * @return {@link ImagenesOnboardingDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public ImagenesOnboardingDto mapToDto(ImagenesOnboardingEntity poImagenesOnboardingEntity) {
        ImagenesOnboardingDto loImagenesOnboardingDto = null;

        if (poImagenesOnboardingEntity != null) {
            loImagenesOnboardingDto = ParseUtil.parseObject(poImagenesOnboardingEntity, ImagenesOnboardingDto.class);
        }
        return loImagenesOnboardingDto;
    }

    /**
     * Transform a {@link ImagenesOnboardingEntity} into an {@link ImagenesOnboardingDto}.
     *
     * @param paoImagenesOnboardingEntity List of Object to be transformed.
     * @return {@link ImagenesOnboardingDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<ImagenesOnboardingDto> mapToDto(List<ImagenesOnboardingEntity> paoImagenesOnboardingEntity) {
        final List<ImagenesOnboardingDto> laoImagenesOnboardingDto;

        if (paoImagenesOnboardingEntity != null && !paoImagenesOnboardingEntity.isEmpty()) {
            laoImagenesOnboardingDto = new ArrayList<>(paoImagenesOnboardingEntity.size());
            for (ImagenesOnboardingEntity loImagenesOnboardingEntity : paoImagenesOnboardingEntity) {
                final ImagenesOnboardingDto loImagenesOnboardingDto = mapToDto(loImagenesOnboardingEntity);
                if (loImagenesOnboardingDto != null) {
                    laoImagenesOnboardingDto.add(loImagenesOnboardingDto);
                }
            }
        } else {
            laoImagenesOnboardingDto = Collections.emptyList();
        }

        return laoImagenesOnboardingDto;
    }
}
