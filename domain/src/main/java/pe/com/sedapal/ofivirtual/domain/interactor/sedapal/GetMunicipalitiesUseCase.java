package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.Municipalities;
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
 * Created by Hernan Pareja on 8/02/2017.
 */

public class GetMunicipalitiesUseCase extends UseCase<List<Municipalities>,Void> {

    private final IncidentsRepository goIncidentsRepository;

    /**
     * Constructs a {@link GetMunicipalitiesUseCase    }.
     *
     * @param poIncidentsRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetMunicipalitiesUseCase(IncidentsRepository poIncidentsRepository, ThreadExecutor poThreadExecutor,
                             PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goIncidentsRepository = poIncidentsRepository;
    }

    @Override
    public Observable<List<Municipalities>> buildUseCaseObservable(Void aVoid) {
        return goIncidentsRepository.getListMunicipalities();
    }
}
