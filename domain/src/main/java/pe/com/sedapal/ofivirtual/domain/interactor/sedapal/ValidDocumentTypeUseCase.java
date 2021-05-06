package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.domain.entity.ValidDocumentType;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidDocumentTypeRepository;

import javax.inject.Inject;
import java.util.List;

public class ValidDocumentTypeUseCase extends UseCase<ValidDocumentType, ValidDocumentTypeUseCase.Params> {
    private final ValidDocumentTypeRepository goValidDocumentTypeRepository;
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
    ValidDocumentTypeUseCase(ValidDocumentTypeRepository poValidDocumentTypeRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goValidDocumentTypeRepository = poValidDocumentTypeRepository;
    }

    @Override
    public Observable<ValidDocumentType> buildUseCaseObservable(Params poParams) {
        return goValidDocumentTypeRepository.valid(poParams.nroSuministro,poParams.tipoDoc,poParams.nroDoc);
    }

    public static final class Params {
        private final int nroSuministro;
        private final int tipoDoc;
        private final String nroDoc;

        public Params(int nroSuministro,int tipoDoc, String nroDoc) {
            this.nroSuministro=nroSuministro;
            this.tipoDoc = tipoDoc;
            this.nroDoc = nroDoc;
        }

        public static Params forValidate(int nroSuministro,int tipoDoc, String nroDoc) {
            return new Params(nroSuministro,tipoDoc, nroDoc);
        }
    }
}
