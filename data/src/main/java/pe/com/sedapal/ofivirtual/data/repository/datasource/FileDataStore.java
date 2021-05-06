package pe.com.sedapal.ofivirtual.data.repository.datasource;

import pe.com.sedapal.ofivirtual.data.entity.RequestInvoicePdfEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

public interface FileDataStore {

    void getPdf(CallbackDataStore<String> poCallback, String psUrl);

    void downloadPdf(CallbackDataStore<String> poCallback, String token, RequestInvoicePdfEntity poRequest);

    void savePdf(CallbackDataStore<String> poCallback, String poPdfFile, String poFineName);
}
