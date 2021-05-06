package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.DatosImagenesEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DatosImagenes;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link DatosImagenesEntityMapper} (in the data layer) to {@link Nis} in the
 * domain layer.
 */
@Singleton
public class DatosImagenesEntityMapper {

    /**
     * Constructs a {@link DatosImagenesEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    DatosImagenesEntityMapper() {
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poDatosImagenesEntity Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public DatosImagenes mapToEntity(DatosImagenesEntity poDatosImagenesEntity) {
        DatosImagenes loDatosImagenesEntity = null;

        if (poDatosImagenesEntity != null) {
            loDatosImagenesEntity = ParseUtil.parseObject(poDatosImagenesEntity, DatosImagenes.class);
        }
        return loDatosImagenesEntity;
    }

}
