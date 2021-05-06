package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.DetailInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;

public interface DetailInvoicesView extends LoadDataView {
    void showSucessListPendingInvoices(List<DetailInvoicesModel> poListDetailInvoicesModel);
}
