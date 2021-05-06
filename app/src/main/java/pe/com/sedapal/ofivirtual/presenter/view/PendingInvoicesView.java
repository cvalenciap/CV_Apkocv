package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;

public interface PendingInvoicesView extends LoadDataView {
    void showSucessListPendingInvoices(List<PendingInvoicesModel> poPendingInvoicesModel);
    void showErrorMessagePending(String poErrorMessage);
}
