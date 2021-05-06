package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.LiquidacionModel;

/**
 * Created by jsaenz on 02-04-2019
 */

public interface LiquidacionEnchufateView extends LoadDataView {

    void showGenerateLiquidacionSuccess(LiquidacionModel poLiquidacionModel);

    void showPayError();
}
