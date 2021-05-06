package pe.com.sedapal.ofivirtual.presenter.view;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface TokenView extends LoadDataView {

    void showSucessGetToken(String strToken);

    void showLoadingProgress();
    void hideLoadingProgress();
}
