package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.ValidateVersionModel;

/**
 * Created by jsaenz on 29/07/2020
 */

public interface ValidateVersionView extends LoadDataView {
    void showValidateVersionSuccess(ValidateVersionModel poValidateVersionModel);

    void hideProgessDialogError();
}
