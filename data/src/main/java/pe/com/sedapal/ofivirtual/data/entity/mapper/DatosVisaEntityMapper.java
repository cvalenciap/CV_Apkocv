package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.DatosVisaEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.DatosVisa;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link DatosVisaEntityMapper} (in the data layer) to {@link Nis} in the
 * domain layer.
 */
@Singleton
public class DatosVisaEntityMapper {

    /**
     * Constructs a {@link DatosVisaEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    DatosVisaEntityMapper() {
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poDatosVisaEntity Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public DatosVisa mapToEntity(DatosVisaEntity poDatosVisaEntity) {
        DatosVisa loDatosVisaEntity = null;

        if (poDatosVisaEntity != null) {
            loDatosVisaEntity = ParseUtil.parseObject(poDatosVisaEntity, DatosVisa.class);
        }
        return loDatosVisaEntity;
    }

}
