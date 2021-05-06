package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DatosVisa;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.model.DatosVisaModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;

/**
 * Mapper class used to transformCostData {@link LoginNewUserModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by jsaenz on 04/03/2019.
 */
public class DatosVisaModelMapper {

    /**
     * Constructs a {@link DatosVisaModelMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 04/03/2019
     */
    @Inject
    public DatosVisaModelMapper() {

    }

    /**
     * Transform a {@link DatosVisaModel} into an {@link DatosVisa}.
     *
     * @param poDatosVisa Object to be transformed.
     * @return {@link DatosVisa}.
     * @author jsaenz
     * @version 1.0
     * @since 04/03/2019
     */
    public DatosVisaModel transform(DatosVisa poDatosVisa) {

        DatosVisaModel loDatosVisaModel = null;
        if (poDatosVisa != null) {
            loDatosVisaModel = ParseUtil.parseObject(poDatosVisa, DatosVisaModel.class);
        }
        return loDatosVisaModel;
    }
}
