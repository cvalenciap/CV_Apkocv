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
 * Created by jsaenz on 27/07/20
 */

public class ValidateConfirmationCodeUseCase extends UseCase<String, ValidateConfirmationCodeUseCase.Params> {

    private final UserRepository goUserRepository;

    /**
     * Constructs a {@link ValidateConfirmationCodeUseCase}.
     *
     * @param poUserRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 8/02/2017
     */
    @Inject
    ValidateConfirmationCodeUseCase(UserRepository poUserRepository, ThreadExecutor poThreadExecutor,
                                    PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goUserRepository = poUserRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goUserRepository.validateConfirmationCode(poParams.psEmailUser, poParams.psCodeVerify);
    }

    public static final class Params {

        private final String psEmailUser;
        private final String psCodeVerify;


        public Params(String psEmailUser, String psCodeVerify) {
            this.psEmailUser = psEmailUser;
            this.psCodeVerify = psCodeVerify;
        }

        public static Params forUser(String psEmailUser, String psCodeVerify) {
            return new Params(psEmailUser, psCodeVerify);
        }
    }
}
