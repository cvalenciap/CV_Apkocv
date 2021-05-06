package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.DetailPayInvoiceModel;

public interface DetailPayInvoiceView extends LoadDataView {
    void showSucessListDetailPay(List<DetailPayInvoiceModel> poDetailPayInvoiceModel);

    void hideLoadingDetailPay();
}
