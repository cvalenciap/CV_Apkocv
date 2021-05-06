package pe.com.sedapal.ofivirtual.data.repository.datasource;

import pe.com.sedapal.ofivirtual.data.entity.LiquidacionEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayLiquidationEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPagoLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz
 */

public interface EnchufateDataStore {


    /**
     * Login user in request  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @author jsaenz
     * @version 1.0
     * @since 23/01/2017.
     */

    void generarLiquidacionEnchufateEntity(CallbackDataStore<LiquidacionEntity> poCallback,
                                           String token,
                                           RequestLiquidacionEnchufateEntity poDocumentos);

    void generarPagoLiquidacionEnchufateEntity(CallbackDataStore<PayLiquidationEntity> poCallback,
                                               String tokenSync, RequestPagoLiquidacionEnchufateEntity poRequestPagoLiquidacionEnchufateEntity);
}
