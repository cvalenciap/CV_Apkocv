package pe.com.sedapal.ofivirtual.data.entity.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.LiquidacionEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayLiquidationEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Liquidacion;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.PayLiquidation;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link LiquidacionEnchufateEntityMapper} (in the data layer) to {@link Nis} in the
 * domain layer.
 */
@Singleton
public class LiquidacionEnchufateEntityMapper {

    /**
     * Constructs a {@link LiquidacionEnchufateEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    LiquidacionEnchufateEntityMapper() {
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @param poLiquidacionEntity Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    public Liquidacion mapToEntity(LiquidacionEntity poLiquidacionEntity) {
        Liquidacion loLiquidacionEntity = null;

        if (poLiquidacionEntity != null) {
            loLiquidacionEntity = ParseUtil.parseObject(poLiquidacionEntity, Liquidacion.class);
        }
        return loLiquidacionEntity;
    }

    public PayLiquidation mapToEntity(PayLiquidationEntity poPayLiquidationEntity) {
        PayLiquidation loPayLiquidation = null;

        if (poPayLiquidationEntity != null) {
            loPayLiquidation = ParseUtil.parseObject(poPayLiquidationEntity, PayLiquidation.class);
        }
        return loPayLiquidation;
    }

}
