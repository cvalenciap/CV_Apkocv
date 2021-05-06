package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;

public interface ValidateInvoicePreviousView extends LoadDataView {
    void showSucessValidateInvoicePrevious(String mensaje);
    void showErrorMessageValidateInvoicePrevious(String poErrorMessage);
}
