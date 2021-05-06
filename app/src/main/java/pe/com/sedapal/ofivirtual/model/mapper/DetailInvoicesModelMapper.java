package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
import pe.com.sedapal.ofivirtual.model.DetailInvoicesModel;

public class DetailInvoicesModelMapper {

    @Inject
    public DetailInvoicesModelMapper() {
    }

    public List<DetailInvoicesModel> transform(List<DetailInvoices> paoDetailInvoices){
        List<DetailInvoicesModel> laoDetailInvoicesModel = null;
        if(paoDetailInvoices != null && !paoDetailInvoices.isEmpty()){
            laoDetailInvoicesModel = new ArrayList<>(paoDetailInvoices.size());
            for(DetailInvoices loDetailInvoices : paoDetailInvoices){
                final DetailInvoicesModel loDetailInvoicesModel = transform(loDetailInvoices);
                if(loDetailInvoicesModel != null){
                    laoDetailInvoicesModel.add(loDetailInvoicesModel);
                }
            }
        }
        return laoDetailInvoicesModel;
    }


    public DetailInvoicesModel transform(DetailInvoices poDetailInvoices) {

        DetailInvoicesModel loDetailInvoicesModel = null;
        if (poDetailInvoices != null) {
            loDetailInvoicesModel = ParseUtil.parseObject(poDetailInvoices, DetailInvoicesModel.class);
        }
        return loDetailInvoicesModel;
    }

}
