package pe.com.sedapal.ofivirtual.domain.repository;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.Liquidacion;
import pe.com.sedapal.ofivirtual.domain.entity.PayLiquidation;
import pe.com.sedapal.ofivirtual.domain.entity.RequestLiquidacionEnchufate;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 09-04-19
 */

public interface EnchufateRepository {
    Observable<Liquidacion> getCloudLiquidacionEnchufate(RequestLiquidacionEnchufate poRequestLiquidacionEnchufate);

    Observable<PayLiquidation> getCloudPagoLiquidacionEnchufate(String customerEmail,
                                                                String numeroLiquidacion,
                                                                String nisRad,
                                                                String amount,
                                                                String TRANSACTION_ID,
                                                                String ACTION_CODE,
                                                                String STATUS,
                                                                String TRANSACTION_DATE,
                                                                String ACTION_DESCRIPTION,
                                                                String TRACE_NUMBER,
                                                                String CARD,
                                                                String BRAND,
                                                                String authCorreo,
                                                                String flagChannel,
                                                                String listaRegistros);
}
