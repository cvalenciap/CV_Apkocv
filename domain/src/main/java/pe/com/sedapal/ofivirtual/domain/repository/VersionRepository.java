package pe.com.sedapal.ofivirtual.domain.repository;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.entity.ValidateVersion;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface VersionRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 12/2018
     */

    Observable<ValidateVersion> validateVersion(String psFlagChannel, String psVersionMovil);
}
