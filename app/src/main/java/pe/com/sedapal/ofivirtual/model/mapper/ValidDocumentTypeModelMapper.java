package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ValidDocumentType;
import pe.com.sedapal.ofivirtual.model.ValidDocumentTypeModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ValidDocumentTypeModelMapper {

    @Inject
    public ValidDocumentTypeModelMapper() {
    }

    public List<ValidDocumentTypeModel> transform(List<ValidDocumentType> paoValidDocumentType){
        List<ValidDocumentTypeModel> laoValidDocumentTypeModel = null;
        if(paoValidDocumentType != null && !paoValidDocumentType.isEmpty()){
            laoValidDocumentTypeModel = new ArrayList<>(paoValidDocumentType.size());
            for(ValidDocumentType loValidDocumentType : paoValidDocumentType){
                final ValidDocumentTypeModel loValidDocumentTypeModel = transform(loValidDocumentType);
                if(loValidDocumentTypeModel != null){
                    laoValidDocumentTypeModel.add(loValidDocumentTypeModel);
                }
            }
        }
        return laoValidDocumentTypeModel;
    }


    public ValidDocumentTypeModel transform(ValidDocumentType poValidDocumentType) {

        ValidDocumentTypeModel loValidDocumentTypeModel = null;
        if (poValidDocumentType != null) {
            loValidDocumentTypeModel = ParseUtil.parseObject(poValidDocumentType, ValidDocumentTypeModel.class);
        }
        return loValidDocumentTypeModel;
    }

}
