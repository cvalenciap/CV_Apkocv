package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link MasterDataStore}.
 * <p>
 * Created by jsaenz on 7/03/2019.
 */
@Singleton
public class InvoicesDataStoreFactory {

    private static final String TAG = InvoicesDataStoreFactory.class.getSimpleName();

    private final Context goContext;

    /**
     * Construct a {@link InvoicesDataStoreFactory} that creates different implementations.
     *
     * @param poContext The {@link Context} to application.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    InvoicesDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    /**
     * Create {@link MasterDataStore} to retrieve data from the Cloud.
     *
     * @return {@link CloudMasterDataStore} of the Cloud.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    public InvoicesDataStore createCloudListPendingInvoices() {
        return new CloudPendingInvoicesDataStore(goContext);
    }

    public InvoicesDataStore createCloudListPayInvoices() {
        return new CloudPayInvoicesDataStore(goContext);
    }

    public InvoicesDataStore createCloudDetailInvoices() {
        return new CloudDetailInvoicesDataStore(goContext);
    }

    public InvoicesDataStore createCloudDetailPayInvoice() {
        return new CloudDetailPayInvoiceDataStore(goContext);
    }

    public InvoicesDataStore createCloudValidateInvoicePrevious() {
        return new CloudValidateInvoicePrevious(goContext);
    }
}
