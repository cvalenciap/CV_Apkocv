package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.CardTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.CardType;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class CardTypeEntityMapper {

    /**
     * Constructs a {@link CardTypeEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    CardTypeEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poCardTypeEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public CardType mapToEntity(CardTypeEntity poCardTypeEntity) {
        CardType loCardType = null;

        if (poCardTypeEntity != null) {
            loCardType = ParseUtil.parseObject(poCardTypeEntity, CardType.class);

        }
        return loCardType;
    }

    public List<CardType> mapToEntity(List<CardTypeEntity> poListCardTypeEntity) {

        final List<CardType> loCardType;

        if (poListCardTypeEntity != null && !poListCardTypeEntity.isEmpty()) {
            loCardType = new ArrayList<>(poListCardTypeEntity.size());
            for (CardTypeEntity loCardTypeEntity : poListCardTypeEntity) {
                final CardType loCardTypeDto = mapToEntity(loCardTypeEntity);
                if (loCardTypeDto != null) {
                    loCardType.add(loCardTypeDto);
                }
            }
        } else {
            loCardType = Collections.emptyList();
        }

        return loCardType;
    }


}
