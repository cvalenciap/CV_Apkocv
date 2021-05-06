package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.SubsidiaryModel;

/**
 * Created by jsaenz on 21/03/2019
 */

public interface ListIncidentsMunicipalitiesView extends LoadDataView {
    void showSucessListIncidentsMunicipalitiesView(List<IncidentsMunicipalitiesModel> poListIncidentsMunicipalitiesModel);

    void showLoadingPersonalizedSubsidiary();
    void hideLoadingPersonalizedSubsidiary();
}
