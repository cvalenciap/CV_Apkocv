package pe.com.sedapal.ofivirtual.domain.interactor.user;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.ObtenerDatosPago;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.InsertarPagoMovilRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by jsaenz on 30/07/2020
 */

public class ObtenerDatosPagoUseCase extends UseCase<ObtenerDatosPago, ObtenerDatosPagoUseCase.Params> {

    private final InsertarPagoMovilRepository goInsertarPagoMovilRepository;


    @Inject
    ObtenerDatosPagoUseCase(InsertarPagoMovilRepository poInsertarPagoMovilRepository, ThreadExecutor poThreadExecutor,
                            PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goInsertarPagoMovilRepository = poInsertarPagoMovilRepository;
    }

    @Override
    public Observable<ObtenerDatosPago> buildUseCaseObservable(Params poParams) {
        return goInsertarPagoMovilRepository.obtenerDatosPago(poParams.psTrxId);
    }

    public static final class Params {

        private final String psTrxId;

        public Params(String psTrxId) {
            this.psTrxId = psTrxId;
        }

        public static Params gotGet(String psTrxId) {
            return new Params(psTrxId);
        }
    }
}
