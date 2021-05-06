package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface MunicipalitiesView extends LoadDataView {
    void showSucessListMunicipalities(List<MunicipalitiesModel> poListMunicipalities);

    void showLoadingPersonalized();
    void hideLoadingPersonalized();
}
