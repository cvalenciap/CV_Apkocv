package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ValidateVersion;
import pe.com.sedapal.ofivirtual.model.ValidateVersionModel;

/**
 * Mapper class used to transformCostData {@link ValidateVersion} (in the domain layer) to {@link ValidateVersionModel} in the
 * presentation layer.
 * <p>
 * Created by jsaenz on 29/07/2020
 */
public class ValidateVersionModelMapper {

    /**
     * Constructs a {@link ValidateVersionModelMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ValidateVersionModelMapper() {

    }

    /**
     * Transform a {@link ValidateVersion} into an {@link ValidateVersionModel}.
     *
     * @param poValidateVersion Object to be transformed.
     * @return {@link ValidateVersionModel}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public ValidateVersionModel transform(ValidateVersion poValidateVersion) {

        ValidateVersionModel loValidateVersionModel = null;
        if (poValidateVersion != null) {
            loValidateVersionModel = ParseUtil.parseObject(poValidateVersion, ValidateVersionModel.class);
        }
        return loValidateVersionModel;
    }
}
