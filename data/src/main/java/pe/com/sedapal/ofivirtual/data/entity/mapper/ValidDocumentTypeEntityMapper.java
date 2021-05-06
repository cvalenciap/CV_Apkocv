package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.ValidDocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ValidDocumentType;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link ValidDocumentTypeEntity} (in the data layer) to {@link ValidDocumentType} in the
 * domain layer.
 */
@Singleton
public class ValidDocumentTypeEntityMapper {
    /**
     * Constructs a {@link ValidDocumentTypeEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ValidDocumentTypeEntityMapper() {
    }


    /**
     * Transform a {@link ValidDocumentTypeEntity} into an {@link ValidDocumentType}.
     *
     * @param poValidDocumentTypeEntity Object to be transformed.
     * @return {@link ValidDocumentType}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ValidDocumentType mapToEntity(ValidDocumentTypeEntity poValidDocumentTypeEntity) {
        ValidDocumentType loValidDocumentType = null;

        if (poValidDocumentTypeEntity != null) {
            loValidDocumentType = ParseUtil.parseObject(poValidDocumentTypeEntity, ValidDocumentType.class);

        }
        return loValidDocumentType;
    }

    /**
     * Transform a {@link ValidDocumentType} into an {@link ValidDocumentTypeEntity}.
     *
     * @param poValidDocumentType Object to be transformed.
     * @return {@link ValidDocumentTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ValidDocumentTypeEntity mapToEntity(ValidDocumentType poValidDocumentType) {
        ValidDocumentTypeEntity loValidDocumentTypeEntity = null;

        if (poValidDocumentType != null) {
            loValidDocumentTypeEntity = ParseUtil.parseObject(poValidDocumentType, ValidDocumentTypeEntity.class);
        }
        return loValidDocumentTypeEntity;
    }


}
