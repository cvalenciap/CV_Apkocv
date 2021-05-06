package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface LoginNewUserView extends LoadDataView {

    void showLoginNewUserSuccess(LoginNewUserModel poUserModel);

    void showLoginNotRegisterEmail(String message);

    void showProgessDialog();

    void hideProgessDialog();

    void hideProgessDialogError();

    void isPendingConfirmRegister(LoginNewUserModel poUserModel);

    void errorLoginUser(String message);
}
