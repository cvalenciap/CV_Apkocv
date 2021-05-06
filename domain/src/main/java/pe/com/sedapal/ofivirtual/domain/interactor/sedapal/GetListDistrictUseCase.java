package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

import javax.inject.Inject;
import java.util.List;

public class GetListDistrictUseCase extends UseCase<List<District>, Void> {
    private final MasterRepository goMasterRepository;
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
    GetListDistrictUseCase(MasterRepository poMasterRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goMasterRepository = poMasterRepository;
    }

    @Override
    public Observable<List<District>> buildUseCaseObservable(Void aVoid) {
        return goMasterRepository.getListDistrict();
    }
}
