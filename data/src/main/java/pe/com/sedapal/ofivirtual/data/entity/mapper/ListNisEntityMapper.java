package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.NisEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link ListNisEntityMapper} (in the data layer) to {@link Nis} in the
 * domain layer.
 */
@Singleton
public class ListNisEntityMapper {

    /**
     * Constructs a {@link ListNisEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    ListNisEntityMapper() {
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
    public Nis mapToEntity(NisEntity poUserEntity) {
        Nis loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, Nis.class);

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
    public List<Nis> mapToEntity(List<NisEntity> poListNis) {

        final List<Nis> loDocumentType;

        if (poListNis != null && !poListNis.isEmpty()) {
            loDocumentType = new ArrayList<>(poListNis.size());
            for (NisEntity loNisTypeEntity : poListNis) {
                final Nis loNisDto = mapToEntity(loNisTypeEntity);
                if (loNisDto != null) {
                    loDocumentType.add(loNisDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
