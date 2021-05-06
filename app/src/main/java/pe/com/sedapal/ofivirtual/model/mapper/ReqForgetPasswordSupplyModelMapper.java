package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ReqForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.model.ReqForgetPasswordSupplyModel;

import javax.inject.Inject;

/**
 * Mapper class used to transformCostData {@link ReqForgetPasswordSupplyModel} (in the domain layer) to {@link ReqForgetPasswordSupply} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class ReqForgetPasswordSupplyModelMapper {

    /**
     * Constructs a {@link ReqForgetPasswordSupplyModelMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ReqForgetPasswordSupplyModelMapper() {

    }

    /**
     * Transform a {@link ReqForgetPasswordSupplyModel} into an {@link ReqForgetPasswordSupply}.
     *
     * @param poReqForgetPasswordSupplyModel Object to be transformed.
     * @return {@link ReqForgetPasswordSupply}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    public ReqForgetPasswordSupply transform(ReqForgetPasswordSupplyModel poReqForgetPasswordSupplyModel) {

        ReqForgetPasswordSupply loReqForgetPasswordSupply = null;
        if (poReqForgetPasswordSupplyModel != null) {
            loReqForgetPasswordSupply = ParseUtil.parseObject(poReqForgetPasswordSupplyModel, ReqForgetPasswordSupply.class);
        }
        return loReqForgetPasswordSupply;
    }
}
