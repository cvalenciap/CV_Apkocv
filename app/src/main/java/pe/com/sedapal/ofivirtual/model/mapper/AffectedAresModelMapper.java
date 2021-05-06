package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.AffectArea;
import pe.com.sedapal.ofivirtual.model.AffectAreaModel;

public class AffectedAresModelMapper {

    @Inject
    public AffectedAresModelMapper() {
    }

    public List<AffectAreaModel> transform(List<AffectArea> paoAffectArea){
        List<AffectAreaModel> laoAffectAreaModel = null;
        if(paoAffectArea != null && !paoAffectArea.isEmpty()){
            laoAffectAreaModel = new ArrayList<>(paoAffectArea.size());
            for(AffectArea loAffectArea : paoAffectArea){
                final AffectAreaModel loAffectAreaModel = transform(loAffectArea);
                if(loAffectAreaModel != null){
                    laoAffectAreaModel.add(loAffectAreaModel);
                }
            }
        }
        return laoAffectAreaModel;
    }


    public AffectAreaModel transform(AffectArea poAffectArea) {

        AffectAreaModel loAffectAreaModel = null;
        if (poAffectArea != null) {
            loAffectAreaModel = ParseUtil.parseObject(poAffectArea, AffectAreaModel.class);
        }
        return loAffectAreaModel;
    }

}
