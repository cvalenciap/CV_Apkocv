package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.RequestLiquidacionEnchufateEntity;
import pe.com.sedapal.ofivirtual.domain.entity.RequestLiquidacionEnchufate;

@Singleton
public class RequestLiquidationInvoicesEntityMapper {

    @Inject
    RequestLiquidationInvoicesEntityMapper() {
    }

    public RequestLiquidacionEnchufateEntity transform(RequestLiquidacionEnchufate poRequest) {

        RequestLiquidacionEnchufateEntity poRequestEntity = new RequestLiquidacionEnchufateEntity();

        List<RequestLiquidacionEnchufateEntity.RequestLiquidationInvoicesEntity> poRequestDocumentsEntity = new ArrayList<>();
        for (RequestLiquidacionEnchufate.RequestLiquidationInvoices item : poRequest.getDocumentos()) {
            RequestLiquidacionEnchufateEntity.RequestLiquidationInvoicesEntity itemInvoiceEntity = new RequestLiquidacionEnchufateEntity.RequestLiquidationInvoicesEntity();
            itemInvoiceEntity.setNumeroDoc(item.getNumeroDoc());
            itemInvoiceEntity.setFechaEmision(item.getFechaEmision());
            itemInvoiceEntity.setFechaVencimiento(item.getFechaVencimiento());
            itemInvoiceEntity.setDeuda(item.getDeuda());

            poRequestDocumentsEntity.add(itemInvoiceEntity);
        }

        poRequestEntity.setNisRad(poRequest.getNisRad());
        poRequestEntity.setDocumentos(poRequestDocumentsEntity);

        return poRequestEntity;
    }

}
