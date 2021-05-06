package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by jsaenz on 29/07/20
 */

public class SendConfirmationCodeUseCase extends UseCase<String, SendConfirmationCodeUseCase.Params> {

    private final UserRepository goUserRepository;

    /**
     * Constructs a {@link SendConfirmationCodeUseCase}.
     *
     * @param poUserRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     */
    @Inject
    SendConfirmationCodeUseCase(UserRepository poUserRepository, ThreadExecutor poThreadExecutor,
                                PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goUserRepository.sendConfirmationCode(poParams.psEmailUser);
    }

    public static final class Params {

        private final String psEmailUser;


        public Params(String psEmailUser) {
            this.psEmailUser = psEmailUser;
        }

        public static Params forUser(String psEmailUser) {
            return new Params(psEmailUser);
        }
    }
}
