package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DocumentTypeModelMapper {

    @Inject
    public DocumentTypeModelMapper() {
    }

    public List<DocumentTypeModel> transform(List<DocumentType> paoDocumentType){
        List<DocumentTypeModel> laoDocumentTypeModel = null;
        if(paoDocumentType != null && !paoDocumentType.isEmpty()){
            laoDocumentTypeModel = new ArrayList<>(paoDocumentType.size());
            for(DocumentType loDocumentType : paoDocumentType){
                final DocumentTypeModel loDocumentTypeModel = transform(loDocumentType);
                if(loDocumentTypeModel != null){
                    laoDocumentTypeModel.add(loDocumentTypeModel);
                }
            }
        }
        return laoDocumentTypeModel;
    }


    public DocumentTypeModel transform(DocumentType poDocumentType) {

        DocumentTypeModel loDocumentTypeModel = null;
        if (poDocumentType != null) {
            loDocumentTypeModel = ParseUtil.parseObject(poDocumentType, DocumentTypeModel.class);
        }
        return loDocumentTypeModel;
    }

}
