package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.telecom.Call;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.entity.DetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.DetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.PayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.PendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestDetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPayInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestPendingInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestUpdateDataUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateInvoicePreviousEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */

public interface InvoicesDataStore {


    /**
     * Get list pending invoices  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link SyncEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018.
     */
    void getPendingInvoices(CallbackDataStore<List<PendingInvoicesEntity>> poCallback, String token, RequestPendingInvoicesEntity poRequest);

    void getPayInvoices(CallbackDataStore<List<PayInvoicesEntity>> poCallback, String token, RequestPayInvoicesEntity poRequest);

    void getDetailInvoices(CallbackDataStore<List<DetailInvoicesEntity>> poCallback, String token, RequestDetailInvoicesEntity poRequest);

    void getDetailPayInvoice(CallbackDataStore<List<DetailPayInvoiceEntity>> poCallback, String token, RequestDetailPayInvoiceEntity poRequest);

    void validateInvoicePrevious(CallbackDataStore<String> poCallback, String poToken, RequestValidateInvoicePreviousEntity poRequest);
}
