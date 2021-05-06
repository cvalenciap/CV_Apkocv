package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.IncidentsMunicipalities;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.IncidentsRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by jsaenz on 20/03/2019
 */

public class GetIncidentsMunicipalitiesUseCase extends UseCase<List<IncidentsMunicipalities>, GetIncidentsMunicipalitiesUseCase.Params> {

    private final IncidentsRepository goIncidentsRepository;

    /**
     * Constructs a {@link GetIncidentsMunicipalitiesUseCase    }.
     *
     * @param poIncidentsRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetIncidentsMunicipalitiesUseCase(IncidentsRepository poIncidentsRepository, ThreadExecutor poThreadExecutor,
                                      PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goIncidentsRepository = poIncidentsRepository;
    }

    @Override
    public Observable<List<IncidentsMunicipalities>> buildUseCaseObservable(Params poParams) {
        return goIncidentsRepository.getListIncidentsMunicipalities(poParams.psCodMunicipio);
    }

    public static final class Params {
        private final int psCodMunicipio;


        public Params(int psCodMunicipio) {
            this.psCodMunicipio = psCodMunicipio;
        }

        public static GetIncidentsMunicipalitiesUseCase.Params forValidate(int psCodMunicipio) {
            return new GetIncidentsMunicipalitiesUseCase.Params(psCodMunicipio);
        }
    }
}
