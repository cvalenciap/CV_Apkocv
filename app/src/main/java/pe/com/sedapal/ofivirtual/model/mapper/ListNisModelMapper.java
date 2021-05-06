package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.model.NisModel;

public class ListNisModelMapper {

    @Inject
    public ListNisModelMapper() {
    }

    public List<NisModel> transform(List<Nis> paoNis){
        List<NisModel> laoNisModel = null;
        if(paoNis != null && !paoNis.isEmpty()){
            laoNisModel = new ArrayList<>(paoNis.size());
            for(Nis loNis : paoNis){
                final NisModel loNisModel = transform(loNis);
                if(loNisModel != null){
                    laoNisModel.add(loNisModel);
                }
            }
        }
        return laoNisModel;
    }


    public NisModel transform(Nis poNis) {

        NisModel loNisModel = null;
        if (poNis != null) {
            loNisModel = ParseUtil.parseObject(poNis, NisModel.class);
        }
        return loNisModel;
    }

}
