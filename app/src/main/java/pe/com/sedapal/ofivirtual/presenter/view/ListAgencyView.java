package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.AgencyModel;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface ListAgencyView extends LoadDataView {
    void showSucessListAgency(List<AgencyModel> poListAgencyModel);

    void showLoadingPersonalized();
    void hideLoadingPersonalized();
}
