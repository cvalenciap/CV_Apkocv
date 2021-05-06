package pe.com.sedapal.ofivirtual.presenter.view;

import android.view.View;

import pe.com.sedapal.ofivirtual.model.UserModel;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface SyncView extends LoadDataView {

    void showSyncSuccess();

    void hideLoadingProgress();
}
