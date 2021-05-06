package pe.com.sedapal.ofivirtual.domain.interactor.user;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by Hernan Pareja on 8/02/2017.
 */

public class LoginSupplyUseCase extends UseCase<LoginSupply, LoginSupplyUseCase.Params> {

    private final UserRepository goUserRepository;

    /**
     * Constructs a {@link LoginSupplyUseCase}.
     *
     * @param poUserRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 8/02/2017
     */
    @Inject
    LoginSupplyUseCase(UserRepository poUserRepository, ThreadExecutor poThreadExecutor,
                       PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<LoginSupply> buildUseCaseObservable(Params poParams) {
        return goUserRepository.loginSupply(poParams.nisRad, poParams.clave);
    }

    public static final class Params {

        private final long nisRad;
        private final String clave;

        public Params(long nisRad, String clave) {
            this.nisRad = nisRad;
            this.clave = clave;
        }

        public static Params forUser(long nisRad, String clave) {
            return new Params(nisRad, clave);
        }
    }
}
