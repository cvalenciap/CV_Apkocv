package pe.com.sedapal.ofivirtual.domain.interactor.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.HistoricConsum;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.NisRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link HistoricConsum}.
 * <p>
 * Created by jsaenz on 11/03/2019
 */

public class GetListHistoricConsumUseCase extends UseCase<List<HistoricConsum>, GetListHistoricConsumUseCase.Params> {

    private final NisRepository goNisRepository;

    /**
     * Constructs a {@link GetListHistoricConsumUseCase    }.
     *
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    GetListHistoricConsumUseCase(NisRepository poNisRepository, ThreadExecutor poThreadExecutor,
                                 PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goNisRepository = poNisRepository;
    }

    @Override
    public Observable<List<HistoricConsum>> buildUseCaseObservable(Params poParams) {
        return goNisRepository.getListHistoricConsum(poParams.nisRad);
    }

    public static final class Params {

        private final long nisRad;

        public Params(long nisRad) {
            this.nisRad = nisRad;
        }

        public static Params forObtain(long nisRad) {
            return new Params(nisRad);
        }
    }
}
