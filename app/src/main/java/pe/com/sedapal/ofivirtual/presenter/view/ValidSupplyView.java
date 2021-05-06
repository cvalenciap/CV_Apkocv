package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;

import java.util.List;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface ValidSupplyView extends LoadDataView {

    void showSucessValidSupply(String message);

    void showLoadingSupply();
    void hideLoadingSupply();
    void showErrorMessageSupply(String message);
}
