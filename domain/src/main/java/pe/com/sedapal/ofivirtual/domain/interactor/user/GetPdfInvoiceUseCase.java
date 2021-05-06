package pe.com.sedapal.ofivirtual.domain.interactor.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.FileRepository;
import pe.com.sedapal.ofivirtual.domain.repository.InvoicesRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 * <p>
 * Created by jsaenz on 11/03/2019
 */

public class GetPdfInvoiceUseCase extends UseCase<String, GetPdfInvoiceUseCase.Params> {

    private final FileRepository goFileRepository;

    /**
     * Constructs a {@link GetPdfInvoiceUseCase    }.
     *
     * @param poFileRepository      {@link UserRepository}.
     * @param poThreadExecutor      {@link ThreadExecutor}.
     * @param poPostExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    @Inject
    GetPdfInvoiceUseCase(FileRepository poFileRepository, ThreadExecutor poThreadExecutor,
                         PostExecutionThread poPostExecutionThread) {
        super(poThreadExecutor, poPostExecutionThread);
        this.goFileRepository = poFileRepository;
    }

    @Override
    public Observable<String> buildUseCaseObservable(Params poParams) {
        return goFileRepository.getPdf(poParams.psSecRec,poParams.psNisRad,poParams.psSecNis,poParams.psfFact, poParams.psNameFile);
    }

    public static final class Params {
        private final long psSecRec;
        private final long psNisRad;
        private final long psSecNis;
        private final String psfFact;
        private final String psNameFile;

        public Params(long psSecRec, long psNisRad, long psSecNis, String psfFact, String psNameFile) {
            this.psSecRec = psSecRec;
            this.psNisRad = psNisRad;
            this.psSecNis = psSecNis;
            this.psfFact = psfFact;
            this.psNameFile = psNameFile;
        }

        public static Params forValidate(long psSecRec, long psNisRad, long psSecNis, String psfFact, String psNameFile) {
            return new Params(psSecRec,psNisRad,psSecNis,psfFact, psNameFile);
        }
    }
}
