package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Agency;
import pe.com.sedapal.ofivirtual.model.AgencyModel;

public class ListAgencyModelMapper {

    @Inject
    public ListAgencyModelMapper() {
    }

    public List<AgencyModel> transform(List<Agency> paoAgency){
        List<AgencyModel> laoAgencyModel = null;
        if(paoAgency != null && !paoAgency.isEmpty()){
            laoAgencyModel = new ArrayList<>(paoAgency.size());
            for(Agency loAgency : paoAgency){
                final AgencyModel loAgencyModel = transform(loAgency);
                if(loAgencyModel != null){
                    laoAgencyModel.add(loAgencyModel);
                }
            }
        }
        return laoAgencyModel;
    }


    public AgencyModel transform(Agency poAgency) {

        AgencyModel loAgencyModel = null;
        if (poAgency != null) {
            loAgencyModel = ParseUtil.parseObject(poAgency, AgencyModel.class);
        }
        return loAgencyModel;
    }

}
