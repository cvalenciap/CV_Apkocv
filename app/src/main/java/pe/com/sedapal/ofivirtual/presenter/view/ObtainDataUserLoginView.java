package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.ObtainDataUserLoginModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface ObtainDataUserLoginView extends LoadDataView {

    void showSucessObtainDatauser(ObtainDataUserLoginModel poObtainDataUserLoginModel);

    void showProgessDialogObtainDataUser();

    void hideProgessDialogObtainDataUser();
}
