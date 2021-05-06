package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.model.DetailPayInvoiceModel;

public class DetailPayInvoiceModelMapper {

    @Inject
    public DetailPayInvoiceModelMapper() {
    }

    public List<DetailPayInvoiceModel> transform(List<DetailPayInvoice> paoDetailPayInvoice){
        List<DetailPayInvoiceModel> laoDetailPayInvoiceModel = null;
        if(paoDetailPayInvoice != null && !paoDetailPayInvoice.isEmpty()){
            laoDetailPayInvoiceModel = new ArrayList<>(paoDetailPayInvoice.size());
            for(DetailPayInvoice loDetailPayInvoice : paoDetailPayInvoice){
                final DetailPayInvoiceModel loDetailPayInvoiceModel = transform(loDetailPayInvoice);
                if(loDetailPayInvoiceModel != null){
                    laoDetailPayInvoiceModel.add(loDetailPayInvoiceModel);
                }
            }
        }
        return laoDetailPayInvoiceModel;
    }


    public DetailPayInvoiceModel transform(DetailPayInvoice poDetailPayInvoice) {

        DetailPayInvoiceModel loDetailPayInvoiceModel = null;
        if (poDetailPayInvoice != null) {
            loDetailPayInvoiceModel = ParseUtil.parseObject(poDetailPayInvoice, DetailPayInvoiceModel.class);
        }
        return loDetailPayInvoiceModel;
    }

}
