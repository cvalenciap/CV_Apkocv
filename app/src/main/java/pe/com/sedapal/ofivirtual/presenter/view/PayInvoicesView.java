package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;

public interface PayInvoicesView extends LoadDataView {
    void showSucessListPayInvoices(List<PayInvoicesModel> poPayInvoicesModel);
    void showErrorMessagePay(String poErrorMessage);
}
