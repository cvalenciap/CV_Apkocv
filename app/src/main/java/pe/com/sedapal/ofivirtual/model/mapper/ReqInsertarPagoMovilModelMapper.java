package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.InsertarPagoMovil;
import pe.com.sedapal.ofivirtual.domain.entity.ReqForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.model.InsertarPagoMovilModel;
import pe.com.sedapal.ofivirtual.model.ReqForgetPasswordSupplyModel;

/**
 * Mapper class used to transformCostData {@link ReqForgetPasswordSupplyModel} (in the domain layer) to {@link ReqForgetPasswordSupply} in the
 * presentation layer.
 * <p>
 * Created by Hernan Pareja on 10/02/2017.
 */
public class ReqInsertarPagoMovilModelMapper {

    /**
     * Constructs a {@link ReqInsertarPagoMovilModelMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 10/02/2017
     */
    @Inject
    public ReqInsertarPagoMovilModelMapper() {

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
    public InsertarPagoMovil transform(InsertarPagoMovilModel poReqForgetPasswordSupplyModel) {

        InsertarPagoMovil loReqForgetPasswordSupply = null;
        if (poReqForgetPasswordSupplyModel != null) {
            loReqForgetPasswordSupply = ParseUtil.parseObject(poReqForgetPasswordSupplyModel, InsertarPagoMovil.class);
        }
        return loReqForgetPasswordSupply;
    }
}
