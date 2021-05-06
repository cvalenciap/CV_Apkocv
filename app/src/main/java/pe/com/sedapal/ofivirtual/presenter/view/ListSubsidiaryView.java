package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.AgencyModel;
import pe.com.sedapal.ofivirtual.model.SubsidiaryModel;

/**
 * Created by jsaenz on 21/03/2019
 */

public interface ListSubsidiaryView extends LoadDataView {
    void showSucessListSubsidiary(List<SubsidiaryModel> poListSubsidiaryModel);

    void showLoadingPersonalizedSubsidiary();
    void hideLoadingPersonalizedSubsidiary();
}
