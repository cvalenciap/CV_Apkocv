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

public class DeleteSessionUserUseCase extends UseCase<Boolean, Void> {

    private final UserRepository goUserRepository;

    /**
     * Constructs a {@link DeleteSessionUserUseCase    }.
     *
     * @param poUserRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 8/02/2017
     */
    @Inject
    DeleteSessionUserUseCase(UserRepository poUserRepository, ThreadExecutor poThreadExecutor,
                             PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Void aVoid) {
        return goUserRepository.deleteSessionUser();
    }
}
