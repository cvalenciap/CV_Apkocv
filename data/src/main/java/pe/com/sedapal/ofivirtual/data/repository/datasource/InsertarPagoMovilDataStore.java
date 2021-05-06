package pe.com.sedapal.ofivirtual.data.repository.datasource;

import pe.com.sedapal.ofivirtual.data.entity.DocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtenerDatosPagoEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSyncEntity;
import pe.com.sedapal.ofivirtual.data.entity.SyncEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;

/**
 * Interface that represents a data store from where data is retrieved.
 * <p>
 * Created by jsaenz on 06/12/2018.
 */

public interface InsertarPagoMovilDataStore {


    /**
     * Login user in request  {@link RequestSyncEntity} and response to Callback {@link CallbackDataStore}
     * to {@link DocumentTypeEntity}
     *
     * @param poCallback {@link CallbackDataStore} to {@link SyncEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018.
     */

    void obtenerDatosPago(CallbackDataStore<ObtenerDatosPagoEntity> poCallback, String token, RequestObtenerDatosPagoEntity poRequest);
}
