package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.NisModel;

public interface ListNisView extends LoadDataView {
    void showSucessListNis(List<NisModel> poNisModel);
    void showErrorMessageListNis(String poErrorMessage);
}
