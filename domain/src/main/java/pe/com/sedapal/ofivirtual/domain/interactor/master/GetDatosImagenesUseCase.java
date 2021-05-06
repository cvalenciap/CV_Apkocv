package pe.com.sedapal.ofivirtual.domain.interactor.master;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DatosImagenes;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

public class GetDatosImagenesUseCase extends UseCase<DatosImagenes, GetDatosImagenesUseCase.Params> {
    private final MasterRepository goMasterRepository;
    /**
     * Constructs a {@link UseCase}.
     *
     * @param threadExecutor      {@link ThreadExecutor}.
     * @param postExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    @Inject
    GetDatosImagenesUseCase(MasterRepository poMasterRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goMasterRepository = poMasterRepository;
    }

    @Override
    public Observable<DatosImagenes> buildUseCaseObservable(Params params) {
        return goMasterRepository.getDatosImagenes(params.psCategoria);
    }

    public static final class Params {
        private final String psCategoria;

        public Params(String poCategoria) {
            this.psCategoria = poCategoria;
        }

        public static GetDatosImagenesUseCase.Params forValidate(String psCategoria) {
            return new GetDatosImagenesUseCase.Params(psCategoria);
        }
    }
}
