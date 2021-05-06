package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.Agency;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.PayPlaceRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by Hernan Pareja on 8/02/2017.
 */

public class GetListAgencyUseCase extends UseCase<List<Agency>, Void> {

    private final PayPlaceRepository goPayPlaceRepository;

    /**
     * Constructs a {@link GetListAgencyUseCase    }.
     *
     * @param poPayPlaceRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetListAgencyUseCase(PayPlaceRepository poPayPlaceRepository, ThreadExecutor poThreadExecutor,
                         PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goPayPlaceRepository = poPayPlaceRepository;
    }

    @Override
    public Observable<List<Agency>> buildUseCaseObservable(Void aVoid) {
        return goPayPlaceRepository.getListAgency();
    }
}
