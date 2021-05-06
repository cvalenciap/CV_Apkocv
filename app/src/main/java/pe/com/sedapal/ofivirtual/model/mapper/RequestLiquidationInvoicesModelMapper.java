package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.RequestLiquidacionEnchufate;
import pe.com.sedapal.ofivirtual.model.RequestLiquidacionEnchufateModel;

public class RequestLiquidationInvoicesModelMapper {

    @Inject
    RequestLiquidationInvoicesModelMapper() {
    }

    public RequestLiquidacionEnchufate transform(RequestLiquidacionEnchufateModel poRequestModel) {

        RequestLiquidacionEnchufate poRequest = new RequestLiquidacionEnchufate();

        List<RequestLiquidacionEnchufate.RequestLiquidationInvoices> poRequestDocuments = new ArrayList<>();
        for (RequestLiquidacionEnchufateModel.RequestLiquidationInvoicesModel itemModel : poRequestModel.getDocumentos()) {
            RequestLiquidacionEnchufate.RequestLiquidationInvoices itemInvoice = new RequestLiquidacionEnchufate.RequestLiquidationInvoices();
            itemInvoice.setNumeroDoc(itemModel.getNumeroDoc());
            itemInvoice.setFechaEmision(itemModel.getFechaEmision());
            itemInvoice.setFechaVencimiento(itemModel.getFechaVencimiento());
            itemInvoice.setDeuda(itemModel.getDeuda());

            poRequestDocuments.add(itemInvoice);
        }

        poRequest.setNisRad(poRequestModel.getNisRad());
        poRequest.setDocumentos(poRequestDocuments);

        return poRequest;
    }

}
