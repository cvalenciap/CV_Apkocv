package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.RegisterUser;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidPayRefRepository;

import javax.inject.Inject;

public class RegisterUserUseCase extends UseCase<String, RegisterUserUseCase.Params> {
    private final UserRepository goUserRepository;
    /**
     * Constructs a {@link UseCase}.
     *
     * @param threadExecutor      {@link ThreadExecutor}.
     * @param postExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    RegisterUserUseCase(UserRepository poUserRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goUserRepository.register(poParams.registerUser);
    }

    public static final class Params {
        private final RegisterUser registerUser;

        public Params(RegisterUser registerUser) {
            this.registerUser = registerUser;
        }

        public static Params forRegister(RegisterUser registerUser) {
            return new Params(registerUser);
        }
    }
}
