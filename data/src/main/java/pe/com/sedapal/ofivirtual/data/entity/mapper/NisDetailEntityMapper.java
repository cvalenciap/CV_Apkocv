package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.NisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.NisDetail;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link NisDetailEntityMapper} (in the data layer) to {@link Nis} in the
 * domain layer.
 */
@Singleton
public class NisDetailEntityMapper {

    /**
     * Constructs a {@link NisDetailEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    NisDetailEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsenz
     * @version 1.0
     * @since 06/12/2018
     */
    public NisDetailEntity mapToEntity(RequestNisDetailEntity poUserEntity) {
        NisDetailEntity loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, NisDetailEntity.class);

        }
        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poNisDetailEntity Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public NisDetail mapToEntity(NisDetailEntity poNisDetailEntity) {
        NisDetail loNisDetailEntity = null;

        if (poNisDetailEntity != null) {
            loNisDetailEntity = ParseUtil.parseObject(poNisDetailEntity, NisDetail.class);
        }
        return loNisDetailEntity;
    }

}
