package pe.com.sedapal.ofivirtual.domain.repository;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.ObtenerDatosPago;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface InsertarPagoMovilRepository {

    Observable<ObtenerDatosPago> obtenerDatosPago(String psTrxId);

}
