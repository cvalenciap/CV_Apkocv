package pe.com.sedapal.ofivirtual.data.repository.datasource;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.entity.AgencyEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.SubsidiaryEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 19/03/2019.
 */

public interface PayPlaceDataStore {

    /**
     * Get list pending invoices  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link SyncEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 19/03/2019
     */
    void getListAgency(CallbackDataStore<List<AgencyEntity>> poCallback, String token);

    void getListSubsidiary(CallbackDataStore<List<SubsidiaryEntity>> poCallback, String token, RequestSubsidiaryEntity poRequest);
}
