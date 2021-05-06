package pe.com.sedapal.ofivirtual.presenter.view;
import pe.com.sedapal.ofivirtual.model.SessionUserModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface SessionUserView extends LoadDataView {

    void showSessionUserObtain(SessionUserModel poUserModel);

    void showDeleteSession(boolean poResponse);

    void hideLoadingProgress();
}
