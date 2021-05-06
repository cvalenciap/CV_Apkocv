package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.PayInvoices;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;

public class PayInvoicesModelMapper {

    @Inject
    public PayInvoicesModelMapper() {
    }

    public List<PayInvoicesModel> transform(List<PayInvoices> paoPayInvoices){
        List<PayInvoicesModel> laoPayInvoicesModel = null;
        if(paoPayInvoices != null && !paoPayInvoices.isEmpty()){
            laoPayInvoicesModel = new ArrayList<>(paoPayInvoices.size());
            for(PayInvoices loPayInvoices : paoPayInvoices){
                final PayInvoicesModel loPayInvoicesModel = transform(loPayInvoices);
                if(loPayInvoicesModel != null){
                    laoPayInvoicesModel.add(loPayInvoicesModel);
                }
            }
        }
        return laoPayInvoicesModel;
    }


    public PayInvoicesModel transform(PayInvoices poPayInvoices) {

        PayInvoicesModel loPayInvoicesModel = null;
        if (poPayInvoices != null) {
            loPayInvoicesModel = ParseUtil.parseObject(poPayInvoices, PayInvoicesModel.class);
        }
        return loPayInvoicesModel;
    }

}
