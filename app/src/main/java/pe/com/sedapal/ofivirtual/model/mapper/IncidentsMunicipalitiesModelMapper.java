package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.IncidentsMunicipalities;
import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;

public class IncidentsMunicipalitiesModelMapper {

    @Inject
    public IncidentsMunicipalitiesModelMapper() {
    }

    public List<IncidentsMunicipalitiesModel> transform(List<IncidentsMunicipalities> paoIncidentsMunicipalities){
        List<IncidentsMunicipalitiesModel> laoIncidentsMunicipalitiesModel = null;
        if(paoIncidentsMunicipalities != null && !paoIncidentsMunicipalities.isEmpty()){
            laoIncidentsMunicipalitiesModel = new ArrayList<>(paoIncidentsMunicipalities.size());
            for(IncidentsMunicipalities loIncidentsMunicipalities : paoIncidentsMunicipalities){
                final IncidentsMunicipalitiesModel loIncidentsMunicipalitiesModel = transform(loIncidentsMunicipalities);
                if(loIncidentsMunicipalitiesModel != null){
                    laoIncidentsMunicipalitiesModel.add(loIncidentsMunicipalitiesModel);
                }
            }
        }
        return laoIncidentsMunicipalitiesModel;
    }


    public IncidentsMunicipalitiesModel transform(IncidentsMunicipalities poIncidentsMunicipalities) {

        IncidentsMunicipalitiesModel loIncidentsMunicipalitiesModel = null;
        if (poIncidentsMunicipalities != null) {
            loIncidentsMunicipalitiesModel = ParseUtil.parseObject(poIncidentsMunicipalities, IncidentsMunicipalitiesModel.class);
        }
        return loIncidentsMunicipalitiesModel;
    }

}
