package pe.com.sedapal.ofivirtual.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.domain.entity.PayInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.PendingInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface InvoicesRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */

    Observable<List<PendingInvoices>> getListPendingInvoices(long psNisRad, int psPageNum, int psPageSize);

    Observable<List<PayInvoices>> getListPayInvoices(long psNisRad, int psPageNum, int psPageSize);

    Observable<List<DetailInvoices>> getListDetailInvoices(long psRecibo);

    Observable<List<DetailPayInvoice>> getListDetailPayInvoice(String psRecibo, String psNumFact);

    Observable<String> validateInvoicePrevious(long psNisRad, long psRecibo);
}
