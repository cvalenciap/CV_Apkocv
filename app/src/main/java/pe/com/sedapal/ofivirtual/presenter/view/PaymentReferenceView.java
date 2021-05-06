package pe.com.sedapal.ofivirtual.presenter.view;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface PaymentReferenceView extends LoadDataView {

    void showSucessValidPaymentReference(String poMessage);

    void showLoadingPaymentReference();
    void hideLoadingPaymentReference();
    void showErrorMessagePayReference(String message);
}
