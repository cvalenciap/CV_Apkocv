package pe.com.sedapal.ofivirtual.domain.interactor.user;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.entity.SecretQuestion;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

import javax.inject.Inject;
import java.util.List;

public class GetListSecretQuestionUseCase extends UseCase<List<SecretQuestion>, Void> {
    private final MasterRepository goMasterRepository;
    /**
     * Constructs a {@link UseCase}.
     *
     * @param threadExecutor      {@link ThreadExecutor}.
     * @param postExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 11/12/2018
     */
    @Inject
    GetListSecretQuestionUseCase(MasterRepository poMasterRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goMasterRepository = poMasterRepository;
    }

    @Override
    public Observable<List<SecretQuestion>> buildUseCaseObservable(Void aVoid) {
        return goMasterRepository.getListSecretQuestions();
    }
}
