package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.TokenRepository;

import javax.inject.Inject;

public class SyncTokenUseCase extends UseCase<String, Void> {
    private final TokenRepository goTokenRepository;
    /**
     * Constructs a {@link UseCase}.
     *
     * @param threadExecutor      {@link ThreadExecutor}.
     * @param postExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    SyncTokenUseCase(TokenRepository poTokenRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goTokenRepository = poTokenRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Void aVoid) {
        return goTokenRepository.obtainToken();
    }
}
