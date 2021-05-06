package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.SessionUser;
import pe.com.sedapal.ofivirtual.model.SessionUserModel;

/**
 * Mapper class used to transformCostData {@link SessionUserModel} (in the domain layer) to {@link SessionUser} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class SessionUserMapper {

    /**
     * Constructs a {@link SessionUserMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public SessionUserMapper() {

    }

    /**
     * Transform a {@link SessionUserModel} into an {@link SessionUser}.
     *
     * @param poSessionUser Object to be transformed.
     * @return {@link SessionUser}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public SessionUserModel transform(SessionUser poSessionUser) {

        SessionUserModel loSessionUserModel = null;
        if (poSessionUser != null) {
            loSessionUserModel = ParseUtil.parseObject(poSessionUser, SessionUserModel.class);
        }
        return loSessionUserModel;
    }
}
