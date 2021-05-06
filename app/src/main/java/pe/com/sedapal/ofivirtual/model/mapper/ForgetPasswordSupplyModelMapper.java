package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.model.ForgetPasswordSupplyModel;

import javax.inject.Inject;

/**
 * Mapper class used to transformCostData {@link ForgetPasswordSupplyModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class ForgetPasswordSupplyModelMapper {

    /**
     * Constructs a {@link ForgetPasswordSupplyModelMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ForgetPasswordSupplyModelMapper() {

    }

    /**
     * Transform a {@link ForgetPasswordSupplyModel} into an {@link LoginNewUser}.
     *
     * @param poForgetPasswordSupply Object to be transformed.
     * @return {@link LoginNewUser}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public ForgetPasswordSupplyModel transform(ForgetPasswordSupply poForgetPasswordSupply) {

        ForgetPasswordSupplyModel loForgetPasswordSupplyModel = null;
        if (poForgetPasswordSupply != null) {
            loForgetPasswordSupplyModel = ParseUtil.parseObject(poForgetPasswordSupply, ForgetPasswordSupplyModel.class);
        }
        return loForgetPasswordSupplyModel;
    }
}
