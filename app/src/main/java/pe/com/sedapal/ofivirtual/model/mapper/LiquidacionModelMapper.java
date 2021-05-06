package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Liquidacion;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.PayLiquidation;
import pe.com.sedapal.ofivirtual.model.LiquidacionModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;

/**
 * Mapper class used to transformCostData {@link LoginNewUserModel} (in the domain layer) to {@link LoginNewUser} in the
 * presentation layer.
 * <p>
 * Created by jsaenz on 09/04/2019.
 */
public class LiquidacionModelMapper {

    /**
     * Constructs a {@link LiquidacionModelMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 09/04/2019
     */
    @Inject
    public LiquidacionModelMapper() {

    }

    /**
     * Transform a {@link LiquidacionModel} into an {@link Liquidacion}.
     *
     * @param poLiquidacion Object to be transformed.
     * @return {@link Liquidacion}.
     * @author jsaenz
     * @version 1.0
     * @since 09/04/2019
     */
    public LiquidacionModel transform(Liquidacion poLiquidacion) {

        LiquidacionModel loLiquidacionModel = null;
        if (poLiquidacion != null) {
            loLiquidacionModel = ParseUtil.parseObject(poLiquidacion, LiquidacionModel.class);
        }
        return loLiquidacionModel;
    }

    public PayLiquidationModel transform(PayLiquidation loPayLiquidation) {

        PayLiquidationModel loPayLiquidationModel = null;
        if (loPayLiquidation != null) {
            loPayLiquidationModel = ParseUtil.parseObject(loPayLiquidation, PayLiquidationModel.class);
        }
        return loPayLiquidationModel;
    }


}
