package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.SecretQuestion;
import pe.com.sedapal.ofivirtual.model.SecretQuestionModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SecretQuestionModelMapper {

    @Inject
    public SecretQuestionModelMapper() {
    }

    public List<SecretQuestionModel> transform(List<SecretQuestion> paoSecretQuestion){
        List<SecretQuestionModel> laoSecretQuestionModel = null;
        if(paoSecretQuestion != null && !paoSecretQuestion.isEmpty()){
            laoSecretQuestionModel = new ArrayList<>(paoSecretQuestion.size());
            for(SecretQuestion loSecretQuestion : paoSecretQuestion){
                final SecretQuestionModel loSecretQuestionModel = transform(loSecretQuestion);
                if(loSecretQuestionModel != null){
                    laoSecretQuestionModel.add(loSecretQuestionModel);
                }
            }
        }
        return laoSecretQuestionModel;
    }


    public SecretQuestionModel transform(SecretQuestion poSecretQuestion) {

        SecretQuestionModel loSecretQuestionModel = null;
        if (poSecretQuestion != null) {
            loSecretQuestionModel = ParseUtil.parseObject(poSecretQuestion, SecretQuestionModel.class);
        }
        return loSecretQuestionModel;
    }

}
