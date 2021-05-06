package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.PendingInvoices;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;

public class PendingInvoicesModelMapper {

    @Inject
    public PendingInvoicesModelMapper() {
    }

    public List<PendingInvoicesModel> transform(List<PendingInvoices> paoPendingInvoices){
        List<PendingInvoicesModel> laoPendingInvoicesModel = null;
        if(paoPendingInvoices != null && !paoPendingInvoices.isEmpty()){
            laoPendingInvoicesModel = new ArrayList<>(paoPendingInvoices.size());
            for(PendingInvoices loPendingInvoices : paoPendingInvoices){
                final PendingInvoicesModel loPendingInvoicesModel = transform(loPendingInvoices);
                if(loPendingInvoicesModel != null){
                    laoPendingInvoicesModel.add(loPendingInvoicesModel);
                }
            }
        }
        return laoPendingInvoicesModel;
    }


    public PendingInvoicesModel transform(PendingInvoices poPendingInvoices) {

        PendingInvoicesModel loPendingInvoicesModel = null;
        if (poPendingInvoices != null) {
            loPendingInvoicesModel = ParseUtil.parseObject(poPendingInvoices, PendingInvoicesModel.class);
        }
        return loPendingInvoicesModel;
    }

}
