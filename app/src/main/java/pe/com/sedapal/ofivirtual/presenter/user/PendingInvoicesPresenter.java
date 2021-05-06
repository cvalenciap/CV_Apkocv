package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.PendingInvoices;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetListPendingInvoicesUseCase;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.mapper.PendingInvoicesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.PendingInvoicesView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */
public class PendingInvoicesPresenter implements Presenter {

    private PendingInvoicesView goPendingInvoicesView;

    private final GetListPendingInvoicesUseCase goGetListPendingInvoicesUseCase;
    private final PendingInvoicesModelMapper goPendingInvoicesModelMMapper;

    @Inject
    public PendingInvoicesPresenter(GetListPendingInvoicesUseCase poGetListPendingInvoicesUseCase, PendingInvoicesModelMapper poPendingInvoicesModelMMapper) {
        this.goGetListPendingInvoicesUseCase = poGetListPendingInvoicesUseCase;
        this.goPendingInvoicesModelMMapper = poPendingInvoicesModelMMapper;
    }

    public void setView(@NonNull PendingInvoicesView poPendingInvoicesView) {
        this.goPendingInvoicesView = poPendingInvoicesView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goPendingInvoicesView = null;
        this.goGetListPendingInvoicesUseCase.dispose();
    }

    public void initialize(long nisRad, int pageNum, int pageSize) {
        this.hideViewRetry();
        this.doObtain(nisRad,pageNum,pageSize);
    }

    private void showViewRetry() {
        this.goPendingInvoicesView.showRetry();
    }

    private void hideViewRetry() {
        this.goPendingInvoicesView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goPendingInvoicesView.showErrorMessagePending(psErrorMessage);
    }

    private void showPendingInvoicesToView(List<PendingInvoices> poListPendingInvoices) {
        List<PendingInvoicesModel> loPendingInvoicesModel = goPendingInvoicesModelMMapper.transform(poListPendingInvoices);
        if(loPendingInvoicesModel == null){
            loPendingInvoicesModel = new ArrayList<>();
        }
        this.goPendingInvoicesView.showSucessListPendingInvoices(loPendingInvoicesModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     * @param nisRad
     * @param pageNum
     * @param pageSize
     */
    private void doObtain(long nisRad, int pageNum, int pageSize) {
        GetListPendingInvoicesUseCase.Params params = GetListPendingInvoicesUseCase.Params.forValidate(nisRad,pageNum,pageSize);
        this.goGetListPendingInvoicesUseCase.execute(new PendingInvoicesObserver(),params);
    }

    private final class PendingInvoicesObserver extends DefaultObserver<List<PendingInvoices>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goPendingInvoicesView.onSessionExpired(poException.getMessage());
            }

            PendingInvoicesPresenter.this.showErrorMessage(poException.getMessage());
            PendingInvoicesPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<PendingInvoices> poData) {
            PendingInvoicesPresenter.this.showPendingInvoicesToView(poData);
        }
    }
}
