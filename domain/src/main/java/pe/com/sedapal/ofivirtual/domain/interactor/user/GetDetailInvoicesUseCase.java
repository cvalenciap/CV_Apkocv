package pe.com.sedapal.ofivirtual.domain.interactor.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
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
 * Created by jsaenz on 11/03/2019
 */

public class GetDetailInvoicesUseCase extends UseCase<List<DetailInvoices>, GetDetailInvoicesUseCase.Params> {

    private final InvoicesRepository goInvoicesRepository;

    /**
     * Constructs a {@link GetDetailInvoicesUseCase    }.
     *
     * @param poInvoicesRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetDetailInvoicesUseCase(InvoicesRepository poInvoicesRepository, ThreadExecutor poThreadExecutor,
                                    PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goInvoicesRepository = poInvoicesRepository;
    }

    @Override
    public Observable<List<DetailInvoices>> buildUseCaseObservable(Params poParams) {
        return goInvoicesRepository.getListDetailInvoices(poParams.recibo);
    }

    public static final class Params {

        private final long recibo;

        public Params(long recibo) {
            this.recibo = recibo;
        }

        public static Params forValidate(long recibo) {
            return new Params(recibo);
        }
    }
}
