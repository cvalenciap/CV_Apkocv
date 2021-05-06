package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.ValidPayRefRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidSupplyRepository;

import javax.inject.Inject;

public class ValidPayRefUseCase extends UseCase<String, ValidPayRefUseCase.Params> {
    private final ValidPayRefRepository goValidPayRefRepository;
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
    ValidPayRefUseCase(ValidPayRefRepository poValidPayRefRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goValidPayRefRepository = poValidPayRefRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goValidPayRefRepository.valid(poParams.supply, poParams.payRef);
    }

    public static final class Params {
        private final long supply;
        private final long payRef;

        public Params(long supply, long payRef) {
            this.supply = supply;
            this.payRef = payRef;
        }

        public static Params forValid(long supply, long payRef) {
            return new Params(supply, payRef);
        }
    }
}
