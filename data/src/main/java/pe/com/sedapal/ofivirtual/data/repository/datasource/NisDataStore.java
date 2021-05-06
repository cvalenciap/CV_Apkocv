package pe.com.sedapal.ofivirtual.data.repository.datasource;

import java.util.List;

import pe.com.sedapal.ofivirtual.data.entity.HistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.NisEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestHistoricConsumEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisDetailEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNisEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 11/02/2019
 */

public interface NisDataStore {


    /**
     * Get list nis in request  {@link RequestLoginEntity} and response to Callback {@link CallbackDataStore}
     * to {@link UserEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link RequestNisEntity}.
     * @param poRequest  Request to {@link RequestLoginEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 11/02/2019
     */
    void getListNis(CallbackDataStore<List<NisEntity>> poCallback, String poToken, RequestNisEntity poRequest);

    void getNisDetail(CallbackDataStore<NisDetailEntity> poCallback, String poToken, RequestNisDetailEntity poRequest);

    void getListHistoricConsum(CallbackDataStore<List<HistoricConsumEntity>> poCallback, String poToken, RequestHistoricConsumEntity poRequest);


}
