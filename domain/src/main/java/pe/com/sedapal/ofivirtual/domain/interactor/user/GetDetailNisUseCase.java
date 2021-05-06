package pe.com.sedapal.ofivirtual.domain.interactor.user;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.NisDetail;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.NisRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link NisDetail}.
 * <p>
 * Created by jsaenz on 11/03/2019
 */

public class GetDetailNisUseCase extends UseCase<NisDetail, GetDetailNisUseCase.Params> {

    private final NisRepository goNisDetailRepository;

    /**
     * Constructs a {@link GetDetailNisUseCase    }.
     *
     * @param poNisDetailRepository {@link NisRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    GetDetailNisUseCase(NisRepository poNisDetailRepository, ThreadExecutor poThreadExecutor,
                        PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goNisDetailRepository = poNisDetailRepository;
    }

    @Override
    public Observable<NisDetail> buildUseCaseObservable(Params poParams) {
        return goNisDetailRepository.getNisDetail(poParams.nisRad, poParams.correo, poParams.flag , poParams.flag_multiple);
    }

    public static final class Params {

        private final long nisRad;
        private final String correo;
        private final String flag;
        private final String flag_multiple;

        public Params(long nisRad, String correo, String flag, String flag_multiple) {
            this.nisRad = nisRad;
            this.correo= correo;
            this.flag =flag;
            this.flag_multiple=flag_multiple;
        }

        public static Params forValidate(long nisRad, String correo, String flag, String flag_multiple) {
            return new Params(nisRad, correo,flag ,flag_multiple);
        }
    }
}
