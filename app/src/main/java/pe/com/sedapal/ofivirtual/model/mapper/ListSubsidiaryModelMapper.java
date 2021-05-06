package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Subsidiary;
import pe.com.sedapal.ofivirtual.model.SubsidiaryModel;

public class ListSubsidiaryModelMapper {

    @Inject
    public ListSubsidiaryModelMapper() {
    }

    public List<SubsidiaryModel> transform(List<Subsidiary> paoSubsidiary){
        List<SubsidiaryModel> laoSubsidiaryModel = null;
        if(paoSubsidiary != null && !paoSubsidiary.isEmpty()){
            laoSubsidiaryModel = new ArrayList<>(paoSubsidiary.size());
            for(Subsidiary loSubsidiary : paoSubsidiary){
                final SubsidiaryModel loSubsidiaryModel = transform(loSubsidiary);
                if(loSubsidiaryModel != null){
                    laoSubsidiaryModel.add(loSubsidiaryModel);
                }
            }
        }
        return laoSubsidiaryModel;
    }


    public SubsidiaryModel transform(Subsidiary poSubsidiary) {

        SubsidiaryModel loSubsidiaryModel = null;
        if (poSubsidiary != null) {
            loSubsidiaryModel = ParseUtil.parseObject(poSubsidiary, SubsidiaryModel.class);
        }
        return loSubsidiaryModel;
    }

}
