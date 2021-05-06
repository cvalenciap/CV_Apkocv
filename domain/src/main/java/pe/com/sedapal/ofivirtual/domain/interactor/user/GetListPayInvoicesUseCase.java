package pe.com.sedapal.ofivirtual.domain.interactor.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.PayInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.InvoicesRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by Hernan Pareja on 8/02/2017.
 */

public class GetListPayInvoicesUseCase extends UseCase<List<PayInvoices>, GetListPayInvoicesUseCase.Params> {

    private final InvoicesRepository goInvoicesRepository;

    /**
     * Constructs a {@link GetListPayInvoicesUseCase    }.
     *
     * @param poInvoicesRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetListPayInvoicesUseCase(InvoicesRepository poInvoicesRepository, ThreadExecutor poThreadExecutor,
                              PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goInvoicesRepository = poInvoicesRepository;
    }

    @Override
    public Observable<List<PayInvoices>> buildUseCaseObservable(Params poParams) {
        return goInvoicesRepository.getListPayInvoices(poParams.nisRad, poParams.pageNum,poParams.pageSize);
    }

    public static final class Params {

        private final long nisRad;
        private final int pageNum;
        private final int pageSize;

        public Params(long nisRad, int pageNum, int pageSize) {
            this.nisRad = nisRad;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
        }

        public static Params forValidate(long nisRad, int pageNum, int pageSize) {
            return new Params(nisRad, pageNum,pageSize);
        }
    }
}
