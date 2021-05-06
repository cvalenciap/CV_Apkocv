package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.UpdateDataUser;
import pe.com.sedapal.ofivirtual.model.UpdateDataUserModel;

/**
 * Mapper class used to transformCostData {@link UpdateDataUserModel} (in the domain layer) to {@link UpdateDataUser} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class UpdateDataUserMapper {

    /**
     * Constructs a {@link UpdateDataUserMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public UpdateDataUserMapper() {

    }

    /**
     * Transform a {@link UpdateDataUserModel} into an {@link UpdateDataUser}.
     *
     * @param poUpdateDataUserModel Object to be transformed.
     * @return {@link UpdateDataUser}.
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    public UpdateDataUser transform(UpdateDataUserModel poUpdateDataUserModel) {

        UpdateDataUser loUpdateDataUser = null;
        if (poUpdateDataUserModel != null) {
            loUpdateDataUser = ParseUtil.parseObject(poUpdateDataUserModel, UpdateDataUser.class);
        }
        return loUpdateDataUser;
    }
}
