package pe.com.sedapal.ofivirtual.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.Agency;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.domain.entity.PayInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.PendingInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.Subsidiary;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface PayPlaceRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */

    Observable<List<Agency>> getListAgency();

    Observable<List<Subsidiary>> getListSubsidiary(long psCodAgencia);
}
