package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.CardTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.CardTypeDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;


/**
 * Created by jsaenz on 13/01/2017.
 * <p>
 * Mapper class used to mapFromDto {@link CardTypeDto} (in the data database) to {@link CardTypeEntity} in the
 * data layer.
 */
@Singleton
public class CardTypeDtoMapper {

    /**
     * Constructs a {@link CardTypeDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public CardTypeDtoMapper() {
    }


    /**
     * Transform a {@link CardTypeDto} into an {@link CardTypeEntity}.
     *
     * @param poCardTypeDto Object to be transformed.
     * @return {@link CardTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public CardTypeEntity mapFromDto(CardTypeDto poCardTypeDto) {
        CardTypeEntity loCardTypeEntity = null;

        if (poCardTypeDto != null) {
            loCardTypeEntity = ParseUtil.parseObject(poCardTypeDto, CardTypeEntity.class);
        }
        return loCardTypeEntity;
    }

    /**
     * Transform a {@link CardTypeDto} into an {@link CardTypeEntity}.
     *
     * @param paoCardTypeDto List of Object to be transformed.
     * @return {@link CardTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<CardTypeEntity> mapFromDto(List<CardTypeDto> paoCardTypeDto) {
        final List<CardTypeEntity> laoCardTypeEntity;

        if (paoCardTypeDto != null && !paoCardTypeDto.isEmpty()) {
            laoCardTypeEntity = new ArrayList<>(paoCardTypeDto.size());
            for (CardTypeDto loCardTypeDto : paoCardTypeDto) {
                final CardTypeEntity loCardTypeEntity = mapFromDto(loCardTypeDto);
                if (loCardTypeEntity != null) {
                    laoCardTypeEntity.add(loCardTypeEntity);
                }
            }
        } else {
            laoCardTypeEntity = Collections.emptyList();
        }

        return laoCardTypeEntity;
    }


    /**
     * Transform a {@link CardTypeEntity} into an {@link CardTypeDto}.
     *
     * @param poCardTypeEntity Object to be transformed.
     * @return {@link CardTypeDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public CardTypeDto mapToDto(CardTypeEntity poCardTypeEntity) {
        CardTypeDto loCardTypeDto = null;

        if (poCardTypeEntity != null) {
            loCardTypeDto = ParseUtil.parseObject(poCardTypeEntity, CardTypeDto.class);
        }
        return loCardTypeDto;
    }

    /**
     * Transform a {@link CardTypeEntity} into an {@link CardTypeDto}.
     *
     * @param paoCardTypeEntity List of Object to be transformed.
     * @return {@link CardTypeDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<CardTypeDto> mapToDto(List<CardTypeEntity> paoCardTypeEntity) {
        final List<CardTypeDto> laoCardTypeDto;

        if (paoCardTypeEntity != null && !paoCardTypeEntity.isEmpty()) {
            laoCardTypeDto = new ArrayList<>(paoCardTypeEntity.size());
            for (CardTypeEntity loCardTypeEntity : paoCardTypeEntity) {
                final CardTypeDto loCardTypeDto = mapToDto(loCardTypeEntity);
                if (loCardTypeDto != null) {
                    laoCardTypeDto.add(loCardTypeDto);
                }
            }
        } else {
            laoCardTypeDto = Collections.emptyList();
        }

        return laoCardTypeDto;
    }
}
