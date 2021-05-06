package pe.com.sedapal.ofivirtual.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.HistoricConsum;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.NisDetail;
import pe.com.sedapal.ofivirtual.domain.entity.RegisterUser;
import pe.com.sedapal.ofivirtual.domain.entity.UpdateDataUser;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 11/03/2019
 */

public interface NisRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */

    Observable<List<Nis>> listNis(long nisRad,String correo, String flag);

    Observable<NisDetail> getNisDetail(long nisRad, String correo, String flag, String flag_multiple);

    Observable<List<HistoricConsum>> getListHistoricConsum(long nisRad);

}
