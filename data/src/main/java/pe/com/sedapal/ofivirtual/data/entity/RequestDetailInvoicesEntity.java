package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jsaenz on 10,diciembre,2018
 */
public class RequestDetailInvoicesEntity {
    @JsonProperty("recibo")
    private long recibo;

    public long getRecibo() {
        return recibo;
    }

    public void setRecibo(long recibo) {
        this.recibo = recibo;
    }
}
