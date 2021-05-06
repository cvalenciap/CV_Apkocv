package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.LoginSupplyModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface LoginSupplyView extends LoadDataView {

    void showLoginSupplySuccess(LoginSupplyModel poUserModel);

    void showLoginIncorrectSupply(String message);

    void showProgessDialogSupply();

    void hideProgessDialogSupply();
}
