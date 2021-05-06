package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.DistrictModel;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;

import java.util.List;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface DistrictView extends LoadDataView {

    void showSucessListDictrict(List<DistrictModel> poListDistrictModel);
}
