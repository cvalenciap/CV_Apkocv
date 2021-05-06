package pe.com.sedapal.ofivirtual.domain.interactor.user;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
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

public class LoginNewUserUseCase extends UseCase<LoginNewUser, LoginNewUserUseCase.Params> {

    private final UserRepository goUserRepository;

    /**
     * Constructs a {@link LoginNewUserUseCase}.
     *
     * @param poUserRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 8/02/2017
     */
    @Inject
    LoginNewUserUseCase(UserRepository poUserRepository, ThreadExecutor poThreadExecutor,
                        PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<LoginNewUser> buildUseCaseObservable(Params poParams) {
        return goUserRepository.loginNewUser(poParams.email, poParams.password);
    }

    public static final class Params {

        private final String email;
        private final String password;

        private Params(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public static Params forUser(String email, String password) {
            return new Params(email, password);
        }
    }
}
