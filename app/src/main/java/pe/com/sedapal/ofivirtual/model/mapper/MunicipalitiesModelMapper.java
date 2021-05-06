package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Municipalities;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;

public class MunicipalitiesModelMapper {

    @Inject
    public MunicipalitiesModelMapper() {
    }

    public List<MunicipalitiesModel> transform(List<Municipalities> paoMunicipalities){
        List<MunicipalitiesModel> laoMunicipalitiesModel = null;
        if(paoMunicipalities != null && !paoMunicipalities.isEmpty()){
            laoMunicipalitiesModel = new ArrayList<>(paoMunicipalities.size());
            for(Municipalities loMunicipalities : paoMunicipalities){
                final MunicipalitiesModel loMunicipalitiesModel = transform(loMunicipalities);
                if(loMunicipalitiesModel != null){
                    laoMunicipalitiesModel.add(loMunicipalitiesModel);
                }
            }
        }
        return laoMunicipalitiesModel;
    }


    public MunicipalitiesModel transform(Municipalities poMunicipalities) {

        MunicipalitiesModel loMunicipalitiesModel = null;
        if (poMunicipalities != null) {
            loMunicipalitiesModel = ParseUtil.parseObject(poMunicipalities, MunicipalitiesModel.class);
        }
        return loMunicipalitiesModel;
    }

}
