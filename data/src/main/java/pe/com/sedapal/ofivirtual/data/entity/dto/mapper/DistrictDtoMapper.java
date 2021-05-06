package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import pe.com.sedapal.ofivirtual.data.entity.DistrictEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.DistrictDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by jsaenz on 13/01/2017.
 * <p>
 * Mapper class used to mapFromDto {@link DistrictDto} (in the data database) to {@link DistrictEntity} in the
 * data layer.
 */
@Singleton
public class DistrictDtoMapper {

    /**
     * Constructs a {@link DistrictDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public DistrictDtoMapper() {
    }


    /**
     * Transform a {@link DistrictDto} into an {@link DistrictEntity}.
     *
     * @param poDistrictDto Object to be transformed.
     * @return {@link DistrictEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public DistrictEntity mapFromDto(DistrictDto poDistrictDto) {
        DistrictEntity loDistrictEntity = null;

        if (poDistrictDto != null) {
            loDistrictEntity = ParseUtil.parseObject(poDistrictDto, DistrictEntity.class);
        }
        return loDistrictEntity;
    }

    /**
     * Transform a {@link DistrictDto} into an {@link DistrictEntity}.
     *
     * @param paoDistrictDto List of Object to be transformed.
     * @return {@link DistrictEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<DistrictEntity> mapFromDto(List<DistrictDto> paoDistrictDto) {
        final List<DistrictEntity> laoDistrictEntity;

        if (paoDistrictDto != null && !paoDistrictDto.isEmpty()) {
            laoDistrictEntity = new ArrayList<>(paoDistrictDto.size());
            for (DistrictDto loDistrictDto : paoDistrictDto) {
                final DistrictEntity loDistrictEntity = mapFromDto(loDistrictDto);
                if (loDistrictEntity != null) {
                    laoDistrictEntity.add(loDistrictEntity);
                }
            }
        } else {
            laoDistrictEntity = Collections.emptyList();
        }

        return laoDistrictEntity;
    }


    /**
     * Transform a {@link DistrictEntity} into an {@link DistrictDto}.
     *
     * @param poDistrictEntity Object to be transformed.
     * @return {@link DistrictDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public DistrictDto mapToDto(DistrictEntity poDistrictEntity) {
        DistrictDto loDistrictDto = null;

        if (poDistrictEntity != null) {
            loDistrictDto = ParseUtil.parseObject(poDistrictEntity, DistrictDto.class);
        }
        return loDistrictDto;
    }

    /**
     * Transform a {@link DistrictEntity} into an {@link DistrictDto}.
     *
     * @param paoDistrictEntity List of Object to be transformed.
     * @return {@link DistrictDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<DistrictDto> mapToDto(List<DistrictEntity> paoDistrictEntity) {
        final List<DistrictDto> laoDistrictDto;

        if (paoDistrictEntity != null && !paoDistrictEntity.isEmpty()) {
            laoDistrictDto = new ArrayList<>(paoDistrictEntity.size());
            for (DistrictEntity loDistrictEntity : paoDistrictEntity) {
                final DistrictDto loDistrictDto = mapToDto(loDistrictEntity);
                if (loDistrictDto != null) {
                    laoDistrictDto.add(loDistrictDto);
                }
            }
        } else {
            laoDistrictDto = Collections.emptyList();
        }

        return laoDistrictDto;
    }
}
