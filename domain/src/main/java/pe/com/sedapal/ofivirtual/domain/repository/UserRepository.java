package pe.com.sedapal.ofivirtual.domain.repository;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.domain.entity.ObtainDataUserLogin;
import pe.com.sedapal.ofivirtual.domain.entity.RegisterUser;
import pe.com.sedapal.ofivirtual.domain.entity.SessionUser;
import pe.com.sedapal.ofivirtual.domain.entity.UpdateDataUser;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface UserRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */

    Observable<String> register(RegisterUser psRegisterUser);

    Observable<SessionUser> obtainSessionUser();

    Observable<Boolean> deleteSessionUser();

    Observable<LoginNewUser> loginNewUser(String psemail, String psPassword);

    Observable<LoginSupply> loginSupply(long psNisRad, String psPassword);

    Observable<String> updateDataUser(UpdateDataUser psUpdateDataUser);

    Observable<ObtainDataUserLogin> obtainDataUser(long idCliente);

    Observable<String> validateConfirmationCode(String psEmailUser, String psCodeVerify);

    Observable<String> sendConfirmationCode(String psEmailUser);

}
