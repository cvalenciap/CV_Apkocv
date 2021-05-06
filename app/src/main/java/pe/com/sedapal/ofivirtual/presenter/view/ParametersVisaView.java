package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.DatosVisaModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;

/**
 * Created by jsaenz on 02-04-2019
 */

public interface ParametersVisaView extends LoadDataView {

    void showDatosVisaSuccess(DatosVisaModel poDatosVisaModel);

    void showProgressLoading();

    void hideProgressLoading();

}
