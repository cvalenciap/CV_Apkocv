package pe.com.sedapal.ofivirtual.model;

import java.io.Serializable;
import java.util.List;

public class ListPendingInvoicesModel implements Serializable {
    private List<PendingInvoicesModel> listPendingInvoicesModel;

    public List<PendingInvoicesModel> getListPendingInvoicesModel() {
        return listPendingInvoicesModel;
    }

    public void setListPendingInvoicesModel(List<PendingInvoicesModel> listPendingInvoicesModel) {
        this.listPendingInvoicesModel = listPendingInvoicesModel;
    }
}
