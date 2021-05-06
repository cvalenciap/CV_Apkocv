package pe.com.sedapal.ofivirtual.presenter.file;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetPdfInvoiceUseCase;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.GetPdfInvoiceView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * Created by Hernan Pareja on 05/08/2017.
 */
public class GetPdfInvoicePresenter implements Presenter {

    private GetPdfInvoiceView goGetPdfInvoiceView;

    private final GetPdfInvoiceUseCase goGetPdfInvoiceUseCase;

    @Inject
    public GetPdfInvoicePresenter(GetPdfInvoiceUseCase poGetPdfInvoiceUseCase) {
        this.goGetPdfInvoiceUseCase = poGetPdfInvoiceUseCase;
    }

    public void setView(@NonNull GetPdfInvoiceView poGetPdfInvoiceView) {
        this.goGetPdfInvoiceView = poGetPdfInvoiceView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetPdfInvoiceUseCase.dispose();
        this.goGetPdfInvoiceView = null;
    }

    public void initialize(String psRecibo,long psSecRec, long psNisRad, long psSecNis, String psfFact) {
        String naneDocDownload = "Recibo-" + psRecibo + "-" + psfFact + ".pdf";
        this.GetPdfInvoice(
                psSecRec,
                psNisRad,
                psSecNis,
                psfFact,
                naneDocDownload);
    }

    private void GetPdfInvoice(long psSecRec, long psNisRad, long psSecNis, String psfFact, String psNameFile) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doGetPdfInvoice(psSecRec,psNisRad,psSecNis,psfFact,psNameFile);
    }

    private void showViewLoading() {
        if(this.goGetPdfInvoiceView != null)
        this.goGetPdfInvoiceView.showLoading(this.goGetPdfInvoiceView.context().getString(R.string.lbl_download_pdf));
    }

    private void hideViewLoading() {
        if(this.goGetPdfInvoiceView != null)
        this.goGetPdfInvoiceView.hideLoading();
    }

    private void showViewRetry() {
        if(this.goGetPdfInvoiceView != null)
        this.goGetPdfInvoiceView.showRetry();
    }

    private void hideViewRetry() {
        if(this.goGetPdfInvoiceView != null)
        this.goGetPdfInvoiceView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        if(this.goGetPdfInvoiceView != null)
        this.goGetPdfInvoiceView.showError(psErrorMessage);
       // this.goGetPdfInvoiceView.showErr
    }

    private void showGetPdfInvoiceSuccessToView(String psPath) {
        if(this.goGetPdfInvoiceView != null)
        this.goGetPdfInvoiceView.showGetPdfInvoiceSuccess(psPath);
    }

    private void doGetPdfInvoice(long psSecRec, long psNisRad, long psSecNis, String psfFact, String psNameFile) {
        GetPdfInvoiceUseCase.Params loParams = GetPdfInvoiceUseCase.Params.forValidate(psSecRec,psNisRad,psSecNis,psfFact,psNameFile);
        this.goGetPdfInvoiceUseCase.execute(new GetPdfInvoiceObserver(),loParams);
    }

    private final class GetPdfInvoiceObserver extends DefaultObserver<String> {
        @Override
        public void onComplete() {
            GetPdfInvoicePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            GetPdfInvoicePresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goGetPdfInvoiceView.onSessionExpired(poException.getMessage());
            }else {
                GetPdfInvoicePresenter.this.showErrorMessage(poException.getMessage());
                GetPdfInvoicePresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(String poData) {
            GetPdfInvoicePresenter.this.showGetPdfInvoiceSuccessToView(poData);
        }
    }

}
