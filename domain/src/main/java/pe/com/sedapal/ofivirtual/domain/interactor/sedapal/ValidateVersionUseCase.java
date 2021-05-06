package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.ValidateVersion;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.VersionRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Void}.
 * <p>
 * Created by jsaenz on 29/07/2020
 */

public class ValidateVersionUseCase extends UseCase<ValidateVersion, ValidateVersionUseCase.Params> {

    private final VersionRepository goVersionRepository;

    /**
     * Constructs a {@link ValidateVersionUseCase    }.
     *
     * @param poVersionRepository   {@link VersionRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 8/02/2017
     */
    @Inject
    ValidateVersionUseCase(VersionRepository poVersionRepository, ThreadExecutor poThreadExecutor,
                           PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goVersionRepository = poVersionRepository;
    }

    @Override
    public Observable<ValidateVersion> buildUseCaseObservable(Params params) {
        return goVersionRepository.validateVersion(params.psFlagChannel, params.psVersionMovil);
    }

    public static final class Params {
        private final String psFlagChannel;
        private final String psVersionMovil;

        public Params(String psFlagChannel, String psVersionMovil) {
            this.psFlagChannel = psFlagChannel;
            this.psVersionMovil = psVersionMovil;
        }

        public static ValidateVersionUseCase.Params forValidate(String psFlagChannel, String psVersionMovil) {
            return new ValidateVersionUseCase.Params(psFlagChannel, psVersionMovil);
        }
    }
}
