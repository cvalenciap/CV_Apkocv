package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.HistoricConsumModel;

public interface ListHistoricConsumView extends LoadDataView {
    void showSucessListHistoricConsum(List<HistoricConsumModel> poHistoricConsumModel);
    void showErrorMessageListHistoricConsum(String poErrorMessage);

    void showProgessDialog();
    void hideProgessDialog();
    void errorProgressDialog();
}
