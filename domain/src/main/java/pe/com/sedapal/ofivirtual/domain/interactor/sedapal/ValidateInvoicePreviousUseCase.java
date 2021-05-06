package pe.com.sedapal.ofivirtual.domain.interactor.sedapal;

import javax.inject.Inject;

import io.reactivex.Observable;
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

public class ValidateInvoicePreviousUseCase extends UseCase<String, ValidateInvoicePreviousUseCase.Params> {

    private final InvoicesRepository goInvoicesRepository;

    /**
     * Constructs a {@link ValidateInvoicePreviousUseCase    }.
     *
     * @param poInvoicesRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    ValidateInvoicePreviousUseCase(InvoicesRepository poInvoicesRepository, ThreadExecutor poThreadExecutor,
                                   PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goInvoicesRepository = poInvoicesRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goInvoicesRepository.validateInvoicePrevious(poParams.nisRad, poParams.recibo);
    }

    public static final class Params {

        private final long nisRad;
        private final long recibo;

        public Params(long nisRad, long recibo) {
            this.nisRad = nisRad;
            this.recibo = recibo;
        }

        public static Params forValidate(long nisRad, long recibo) {
            return new Params(nisRad, recibo);
        }
    }
}
