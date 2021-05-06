package pe.com.sedapal.ofivirtual.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequestSyncEntity {

    @JsonProperty("fechaSincronizacion")
    private String syncDate;

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }
}
