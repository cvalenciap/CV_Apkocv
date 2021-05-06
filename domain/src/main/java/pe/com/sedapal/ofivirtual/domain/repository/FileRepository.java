package pe.com.sedapal.ofivirtual.domain.repository;

import io.reactivex.Observable;

/**
 * Created by jsaenz on 11/03/2019
 */

public interface FileRepository {

    Observable<String> getPdf(long secRec, long nisRad, long secNis, String fFact, String psNameFile);

}
