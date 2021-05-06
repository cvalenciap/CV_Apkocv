package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.model.DistrictModel;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DistrictModelMapper {

    @Inject
    public DistrictModelMapper() {
    }

    public List<DistrictModel> transform(List<District> paoDistrict){
        List<DistrictModel> laoDistrictModel = null;
        if(paoDistrict != null && !paoDistrict.isEmpty()){
            laoDistrictModel = new ArrayList<>(paoDistrict.size());
            for(District loDocumentType : paoDistrict){
                final DistrictModel loDistrictModel = transform(loDocumentType);
                if(loDistrictModel != null){
                    laoDistrictModel.add(loDistrictModel);
                }
            }
        }
        return laoDistrictModel;
    }


    public DistrictModel transform(District poDistrict) {

        DistrictModel loDocumentTypeModel = null;
        if (poDistrict != null) {
            loDocumentTypeModel = ParseUtil.parseObject(poDistrict, DistrictModel.class);
        }
        return loDocumentTypeModel;
    }

}
