package pe.com.sedapal.ofivirtual.domain.interactor.user;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.ForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.entity.ReqForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.ForgetPasswordRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by Hernan Pareja on 8/02/2017.
 */

public class ForgetPasswordSupplyUseCase extends UseCase<ForgetPasswordSupply, ForgetPasswordSupplyUseCase.Params> {

    private final ForgetPasswordRepository goForgetPasswordRepository;

    /**
     * Constructs a {@link ForgetPasswordSupplyUseCase    }.
     *
     * @param poForgetPasswordRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 11/12/2018
     */
    @Inject
    ForgetPasswordSupplyUseCase(ForgetPasswordRepository poForgetPasswordRepository, ThreadExecutor poThreadExecutor,
                                PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goForgetPasswordRepository = poForgetPasswordRepository;
    }

    @Override
    public Observable<ForgetPasswordSupply> buildUseCaseObservable(Params poParams) {
        return goForgetPasswordRepository.validForgetPasswordSupply(poParams.reqForgetPasswordSupply);

    }

    public static final class Params {

        private final ReqForgetPasswordSupply reqForgetPasswordSupply;

        public Params(ReqForgetPasswordSupply reqForgetPasswordSupply) {
            this.reqForgetPasswordSupply = reqForgetPasswordSupply;
        }

        public static Params forUser(ReqForgetPasswordSupply reqForgetPasswordSupply) {
            return new Params(reqForgetPasswordSupply);
        }
    }
}
