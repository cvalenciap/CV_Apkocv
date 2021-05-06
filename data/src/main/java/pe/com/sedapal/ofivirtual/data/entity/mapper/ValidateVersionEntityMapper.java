package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.ValidateVersionEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ValidateVersion;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link ValidateVersionEntity} (in the data layer) to {@link ValidateVersion} in the
 * domain layer.
 */
@Singleton
public class ValidateVersionEntityMapper {

    /**
     * Constructs a {@link ValidateVersionEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ValidateVersionEntityMapper() {
    }


    /**
     * Transform a {@link ValidateVersionEntity} into an {@link ValidateVersion}.
     *
     * @param poValidateVersionEntity Object to be transformed.
     * @return {@link ValidateVersion}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ValidateVersion mapToEntity(ValidateVersionEntity poValidateVersionEntity) {
        ValidateVersion loValidateVersion = null;

        if (poValidateVersionEntity != null) {
            loValidateVersion = ParseUtil.parseObject(poValidateVersionEntity, ValidateVersion.class);

        }
        return loValidateVersion;
    }

    /**
     * Transform a {@link ValidateVersion} into an {@link ValidateVersionEntity}.
     *
     * @param poValidateVersion Object to be transformed.
     * @return {@link ValidateVersionEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ValidateVersionEntity mapToEntity(ValidateVersion poValidateVersion) {
        ValidateVersionEntity loValidateVersionEntity = null;

        if (poValidateVersion != null) {
            loValidateVersionEntity = ParseUtil.parseObject(poValidateVersion, ValidateVersionEntity.class);
        }
        return loValidateVersionEntity;
    }
}
