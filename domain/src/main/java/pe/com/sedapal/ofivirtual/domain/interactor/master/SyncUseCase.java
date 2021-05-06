package pe.com.sedapal.ofivirtual.domain.interactor.master;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Void}.
 * <p>
 * Created by Hernan Pareja on 8/02/2017.
 */

public class SyncUseCase extends UseCase<Void, Void> {

    private final MasterRepository goMasterRepository;

    /**
     * Constructs a {@link SyncUseCase    }.
     *
     * @param poMasterRepository    {@link MasterRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 8/02/2017
     */
    @Inject
    SyncUseCase(MasterRepository poMasterRepository, ThreadExecutor poThreadExecutor,
                PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goMasterRepository = poMasterRepository;
    }

    @Override
    public Observable<Void> buildUseCaseObservable(Void poVoid) {

        return goMasterRepository.sync();
    }

}
