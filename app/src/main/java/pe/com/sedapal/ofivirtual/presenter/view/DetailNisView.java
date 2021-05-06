package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.NisDetailModel;
import pe.com.sedapal.ofivirtual.model.NisModel;

public interface DetailNisView extends LoadDataView {
    void showSucessDetailNis(NisDetailModel poNisDetailModel);
    void showErrorMessageDetailNis(String poErrorMessage);
}
