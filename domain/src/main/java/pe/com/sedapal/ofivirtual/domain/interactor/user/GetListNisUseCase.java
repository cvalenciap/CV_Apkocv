package pe.com.sedapal.ofivirtual.domain.interactor.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.NisRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Nis}.
 * <p>
 * Created by jsaenz on 11/03/2019
 */

public class GetListNisUseCase extends UseCase<List<Nis>, GetListNisUseCase.Params> {

    private final NisRepository goNisRepository;
    /**
     * Constructs a {@link GetListNisUseCase    }.
     *
     * @param poNisRepository      {@link NisRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    @Inject
    GetListNisUseCase(NisRepository poNisRepository, ThreadExecutor poThreadExecutor,
                      PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goNisRepository = poNisRepository;
    }

    @Override
    public Observable<List<Nis>> buildUseCaseObservable(Params poParams) {
        return goNisRepository.listNis(poParams.nisRad, poParams.correo, poParams.flag);
    }

    public static final class Params {
        private final long nisRad;
        private final String correo;
        private final String flag;


        public Params(long nisRad, String correo,String flag) {
            this.nisRad = nisRad;
            this.correo= correo;
            this.flag= flag;
        }

        public static Params forValidate(long nisRad, String correo, String flag) {
            return new Params(nisRad, correo,flag);
        }
    }
}
