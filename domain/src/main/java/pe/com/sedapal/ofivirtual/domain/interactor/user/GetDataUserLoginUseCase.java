package pe.com.sedapal.ofivirtual.domain.interactor.user;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.ObtainDataUserLogin;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

public class GetDataUserLoginUseCase extends UseCase<ObtainDataUserLogin, GetDataUserLoginUseCase.Params> {

    private final UserRepository goUserRepository;

    @Inject
    GetDataUserLoginUseCase(UserRepository poUserRepository, ThreadExecutor poThreadExecutor,
                            PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<ObtainDataUserLogin> buildUseCaseObservable(Params poParams) {
        return goUserRepository.obtainDataUser(poParams.idCliente);
    }

    public static final class Params {

        private final long idCliente;

        private Params(long idCliente) {
            this.idCliente = idCliente;
        }

        public static Params forObtain(long idCliente) {
            return new Params(idCliente);
        }
    }
}
