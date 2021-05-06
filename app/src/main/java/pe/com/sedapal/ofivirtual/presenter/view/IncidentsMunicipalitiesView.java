package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;

/**
 * Created by JSAENZ ON 20/03/2019
 */

public interface IncidentsMunicipalitiesView extends LoadDataView {
    void showSucessListIncidentsMunicipalities(List<IncidentsMunicipalitiesModel> poIncidentsMunicipalitiesModel);

    void showLoadingPersonalized();
    void hideLoadingPersonalized();
}
