package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.HistoricConsum;
import pe.com.sedapal.ofivirtual.model.HistoricConsumModel;

public class ListHistoricConsumModelMapper {

    @Inject
    public ListHistoricConsumModelMapper() {
    }

    public List<HistoricConsumModel> transform(List<HistoricConsum> paoHistoricConsum){
        List<HistoricConsumModel> laoHistoricConsumModel = null;
        if(paoHistoricConsum != null && !paoHistoricConsum.isEmpty()){
            laoHistoricConsumModel = new ArrayList<>(paoHistoricConsum.size());
            for(HistoricConsum loHistoricConsum : paoHistoricConsum){
                final HistoricConsumModel loHistoricConsumModel = transform(loHistoricConsum);
                if(loHistoricConsumModel != null){
                    laoHistoricConsumModel.add(loHistoricConsumModel);
                }
            }
        }
        return laoHistoricConsumModel;
    }


    public HistoricConsumModel transform(HistoricConsum poHistoricConsum) {

        HistoricConsumModel loHistoricConsumModel = null;
        if (poHistoricConsum != null) {
            loHistoricConsumModel = ParseUtil.parseObject(poHistoricConsum, HistoricConsumModel.class);
        }
        return loHistoricConsumModel;
    }

}
