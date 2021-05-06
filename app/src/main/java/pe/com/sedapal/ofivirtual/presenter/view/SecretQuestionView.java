package pe.com.sedapal.ofivirtual.presenter.view;

import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;
import pe.com.sedapal.ofivirtual.model.SecretQuestionModel;
import pe.com.sedapal.ofivirtual.model.ValidDocumentTypeModel;

import java.util.List;

/**
 * Created by jsaenz on 17/02/2017.
 */

public interface SecretQuestionView extends LoadDataView {

    void showSucessListSecretQuestion(List<SecretQuestionModel> poListSecretQuestionModel);

}
