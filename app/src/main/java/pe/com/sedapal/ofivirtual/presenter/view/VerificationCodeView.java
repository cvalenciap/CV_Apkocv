package pe.com.sedapal.ofivirtual.presenter.view;

/**
 * Created by jsaenz on 27/07/20
 */

public interface VerificationCodeView extends LoadDataView {
    void showVerificationCodeSuccessToView(String psMessage);

    void showSendCodeSuccessToView(String psMessage);
}
