package pe.com.sedapal.ofivirtual.domain.interactor.master;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DatosVisa;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetIncidentsMunicipalitiesUseCase;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;

public class GetDatosVisaUseCase extends UseCase<DatosVisa, GetDatosVisaUseCase.Params> {
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
    GetDatosVisaUseCase(MasterRepository poMasterRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goMasterRepository = poMasterRepository;
    }

    @Override
    public Observable<DatosVisa> buildUseCaseObservable(Params params) {
        return goMasterRepository.getDatosVisa(params.psCategoria);
    }

    public static final class Params {
        private final String psCategoria;

        public Params(String poCategoria) {
            this.psCategoria = poCategoria;
        }

        public static GetDatosVisaUseCase.Params forValidate(String psCategoria) {
            return new GetDatosVisaUseCase.Params(psCategoria);
        }
    }
}
