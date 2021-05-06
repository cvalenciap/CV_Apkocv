package pe.com.sedapal.ofivirtual.model.mapper;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ApplicantType;
import pe.com.sedapal.ofivirtual.model.ApplicantTypeModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ApplicantTypeModelMapper {

    @Inject
    public ApplicantTypeModelMapper() {
    }

    public List<ApplicantTypeModel> transform(List<ApplicantType> paoApplicantType){
        List<ApplicantTypeModel> laoApplicantTypeModel = null;
        if(paoApplicantType != null && !paoApplicantType.isEmpty()){
            laoApplicantTypeModel = new ArrayList<>(paoApplicantType.size());
            for(ApplicantType loApplicantType : paoApplicantType){
                final ApplicantTypeModel loApplicantTypeModel = transform(loApplicantType);
                if(loApplicantTypeModel != null){
                    laoApplicantTypeModel.add(loApplicantTypeModel);
                }
            }
        }
        return laoApplicantTypeModel;
    }


    public ApplicantTypeModel transform(ApplicantType poApplicantType) {

        ApplicantTypeModel loApplicantTypeModel = null;
        if (poApplicantType != null) {
            loApplicantTypeModel = ParseUtil.parseObject(poApplicantType, ApplicantTypeModel.class);
        }
        return loApplicantTypeModel;
    }

}
