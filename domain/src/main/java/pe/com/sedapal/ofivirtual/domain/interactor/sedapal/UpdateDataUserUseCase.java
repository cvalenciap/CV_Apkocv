package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.RegisterUser;
import pe.com.sedapal.ofivirtual.domain.entity.UpdateDataUser;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

public class UpdateDataUserUseCase extends UseCase<String, UpdateDataUserUseCase.Params> {
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
    UpdateDataUserUseCase(UserRepository poUserRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goUserRepository.updateDataUser(poParams.updateDataUser);
    }

    public static final class Params {
        private final UpdateDataUser updateDataUser;

        public Params(UpdateDataUser updateDataUser) {
            this.updateDataUser = updateDataUser;
        }

        public static Params forUpdate(UpdateDataUser registerUpdateDataUser) {
            return new Params(registerUpdateDataUser);
        }
    }
}
