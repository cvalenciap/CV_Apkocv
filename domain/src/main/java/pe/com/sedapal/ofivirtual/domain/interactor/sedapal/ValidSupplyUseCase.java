package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.user.LoginUseCase;
import pe.com.sedapal.ofivirtual.domain.repository.TokenRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidSupplyRepository;

import javax.inject.Inject;

public class ValidSupplyUseCase extends UseCase<String, ValidSupplyUseCase.Params> {
    private final ValidSupplyRepository goValidSupplyRepository;
    /**
     * Constructs a {@link UseCase}.
     *
     * @param threadExecutor      {@link ThreadExecutor}.
     * @param postExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 05/12/2018
     */
    @Inject
    ValidSupplyUseCase(ValidSupplyRepository poValidSupplyRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goValidSupplyRepository = poValidSupplyRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goValidSupplyRepository.valid(poParams.supply);
    }

    public static final class Params {
        private final int supply;


        public Params(int supply) {
            this.supply = supply;

        }

        public static Params forValid(int supply) {
            return new Params(supply);
        }
    }
}
