package pe.com.sedapal.ofivirtual.presenter.view;

import java.util.List;

import pe.com.sedapal.ofivirtual.model.CardTypeModel;
import pe.com.sedapal.ofivirtual.model.ImagenesOnboardingModel;

/**
 * Created by jsaenz on 02-04-2019
 */

public interface ImagenesOnboardingView extends LoadDataView {

    void showImagenesOnboarding(List<ImagenesOnboardingModel> listImagenesOnboardingModel);

}
