package pe.com.sedapal.ofivirtual.domain.interactor.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
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

public class GetDetailPayInvoiceUseCase extends UseCase<List<DetailPayInvoice>, GetDetailPayInvoiceUseCase.Params> {

    private final InvoicesRepository goInvoicesRepository;

    /**
     * Constructs a {@link GetDetailPayInvoiceUseCase    }.
     *
     * @param poInvoicesRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetDetailPayInvoiceUseCase(InvoicesRepository poInvoicesRepository, ThreadExecutor poThreadExecutor,
                               PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goInvoicesRepository = poInvoicesRepository;
    }

    @Override
    public Observable<List<DetailPayInvoice>> buildUseCaseObservable(Params poParams) {
        return goInvoicesRepository.getListDetailPayInvoice(poParams.psRecibo,poParams.psNumFact);
    }

    public static final class Params {
        private final String psRecibo;
        private final String psNumFact;

        public Params(String psRecibo, String psNumFact) {
            this.psRecibo = psRecibo;
            this.psNumFact = psNumFact;
        }

        public static Params forValidate(String psRecibo, String psNumFact) {
            return new Params(psRecibo,psNumFact);
        }
    }
}
