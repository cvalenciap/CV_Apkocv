package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.UserModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface LoginView extends LoadDataView {

    void showLoginSuccess(UserModel poUserModel);
}
