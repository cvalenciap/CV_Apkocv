package pe.com.sedapal.ofivirtual.domain.repository;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.entity.ValidDocumentType;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface ValidDocumentTypeRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 05/12/2018
     */
    Observable<ValidDocumentType> valid(int psNroSuministro,int psDocumentType, String psDocumentNumber);
}
