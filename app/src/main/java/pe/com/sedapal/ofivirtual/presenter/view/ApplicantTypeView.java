package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.ApplicantTypeModel;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;

import java.util.List;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface ApplicantTypeView extends LoadDataView {

    void showSucessApplicantType(List<ApplicantTypeModel> poListApplicantTypeModel);
}
