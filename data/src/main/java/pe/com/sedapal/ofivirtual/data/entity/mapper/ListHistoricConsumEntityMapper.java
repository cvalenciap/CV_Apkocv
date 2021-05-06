package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.HistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.HistoricConsum;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link ListHistoricConsumEntityMapper} (in the data layer) to {@link HistoricConsum} in the
 * domain layer.
 */
@Singleton
public class ListHistoricConsumEntityMapper {

    /**
     * Constructs a {@link ListHistoricConsumEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    ListHistoricConsumEntityMapper() {
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
    public HistoricConsum mapToEntity(HistoricConsumEntity poUserEntity) {
        HistoricConsum loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, HistoricConsum.class);

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
    public List<HistoricConsum> mapToEntity(List<HistoricConsumEntity> poListHistoricConsum) {

        final List<HistoricConsum> loDocumentType;

        if (poListHistoricConsum != null && !poListHistoricConsum.isEmpty()) {
            loDocumentType = new ArrayList<>(poListHistoricConsum.size());
            for (HistoricConsumEntity loHistoricConsumTypeEntity : poListHistoricConsum) {
                final HistoricConsum loHistoricConsumDto = mapToEntity(loHistoricConsumTypeEntity);
                if (loHistoricConsumDto != null) {
                    loDocumentType.add(loHistoricConsumDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
