package pe.com.sedapal.ofivirtual.data.repository.datasource;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.entity.IncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.MunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestIncidentsMunicipalitiesEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */

public interface IncidentsDataStore {


    /**
     * Get list pending invoices  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link SyncEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018.
     */
    void getListMunicipalities(CallbackDataStore<List<MunicipalitiesEntity>> poCallback, String token);

    void getListIncidentsMunicipalities(CallbackDataStore<List<IncidentsMunicipalitiesEntity>> poCallback, String token, RequestIncidentsMunicipalitiesEntity poRequest);

}
