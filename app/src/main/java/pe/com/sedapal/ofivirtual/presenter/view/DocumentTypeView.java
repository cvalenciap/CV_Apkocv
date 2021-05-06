package pe.com.sedapal.ofivirtual.presenter.view;

import android.view.View;

import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;
import pe.com.sedapal.ofivirtual.model.ValidDocumentTypeModel;

import java.util.List;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface DocumentTypeView extends LoadDataView {

    void showSucessListDocumentType(List<DocumentTypeModel> poListDocumentType);

    void showSucessValidateDocumentType(ValidDocumentTypeModel poValidDocumentTypeModel);

    void showErrorMessageDocumentType(String message);

    void showLoadingDocumentType();

    void hideLoadingDocumentType();

}
