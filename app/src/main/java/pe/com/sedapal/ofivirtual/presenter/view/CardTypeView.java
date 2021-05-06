package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.CardTypeModel;

/**
 * Created by jsaenz on 02-04-2019
 */

public interface CardTypeView extends LoadDataView {

    void showIdCardType(List<CardTypeModel> listCardTypeModel);

}
