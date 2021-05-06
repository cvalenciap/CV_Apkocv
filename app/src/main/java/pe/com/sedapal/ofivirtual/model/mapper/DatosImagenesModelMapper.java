package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DatosImagenes;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.model.DatosImagenesModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;

/**
 * Mapper class used to transformCostData {@link LoginNewUserModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by jsaenz on 04/03/2019.
 */
public class DatosImagenesModelMapper {

    /**
     * Constructs a {@link DatosImagenesModelMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 04/03/2019
     */
    @Inject
    public DatosImagenesModelMapper() {

    }

    /**
     * Transform a {@link DatosImagenesModel} into an {@link DatosImagenes}.
     *
     * @param poDatosImagenes Object to be transformed.
     * @return {@link DatosImagenes}.
     * @author jsaenz
     * @version 1.0
     * @since 04/03/2019
     */
    public DatosImagenesModel transform(DatosImagenes poDatosImagenes) {

        DatosImagenesModel loDatosImagenesModel = null;
        if (poDatosImagenes != null) {
            loDatosImagenesModel = ParseUtil.parseObject(poDatosImagenes, DatosImagenesModel.class);
        }
        return loDatosImagenesModel;
    }
}
