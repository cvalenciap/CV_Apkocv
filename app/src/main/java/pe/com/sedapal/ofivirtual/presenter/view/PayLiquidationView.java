package pe.com.sedapal.ofivirtual.presenter.view;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;

/**
 * Created by jsaenz on 23/04/19
 */

public interface PayLiquidationView {

    void sendBroadcast(boolean pbSuccess, PayLiquidationModel loPayLiquidationModel);
}
