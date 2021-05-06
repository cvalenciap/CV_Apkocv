package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.model.LoginSupplyModel;

/**
 * Mapper class used to transformCostData {@link LoginSupplyModel} (in the domain layer) to {@link LoginSupply} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class LoginSupplyMapper {

    /**
     * Constructs a {@link LoginSupplyMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public LoginSupplyMapper() {

    }

    /**
     * Transform a {@link LoginSupplyModel} into an {@link LoginSupply}.
     *
     * @param poLoginSupply Object to be transformed.
     * @return {@link LoginSupply}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public LoginSupplyModel transform(LoginSupply poLoginSupply) {

        LoginSupplyModel loLoginSupplyModel = null;
        if (poLoginSupply != null) {
            loLoginSupplyModel = ParseUtil.parseObject(poLoginSupply, LoginSupplyModel.class);
        }
        return loLoginSupplyModel;
    }
}
