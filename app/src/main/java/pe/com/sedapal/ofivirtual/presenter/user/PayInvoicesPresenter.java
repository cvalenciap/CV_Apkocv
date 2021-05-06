package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.PayInvoices;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetListPayInvoicesUseCase;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.model.mapper.PayInvoicesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.PayInvoicesView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */
public class PayInvoicesPresenter implements Presenter {

    private PayInvoicesView goPayInvoicesView;

    private final GetListPayInvoicesUseCase goGetListPayInvoicesUseCase;
    private final PayInvoicesModelMapper goPayInvoicesModelMMapper;

    @Inject
    public PayInvoicesPresenter(GetListPayInvoicesUseCase poGetListPayInvoicesUseCase, PayInvoicesModelMapper poPayInvoicesModelMMapper) {
        this.goGetListPayInvoicesUseCase = poGetListPayInvoicesUseCase;
        this.goPayInvoicesModelMMapper = poPayInvoicesModelMMapper;
    }

    public void setView(@NonNull PayInvoicesView poPayInvoicesView) {
        this.goPayInvoicesView = poPayInvoicesView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goPayInvoicesView = null;
        this.goGetListPayInvoicesUseCase.dispose();
    }

    public void initialize(long nisRad, int pageNum, int pageSize) {
        this.hideViewRetry();
        this.doObtain(nisRad,pageNum,pageSize);
    }

    private void showViewRetry() {
        this.goPayInvoicesView.showRetry();
    }

    private void hideViewRetry() {
        this.goPayInvoicesView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goPayInvoicesView.showErrorMessagePay(psErrorMessage);
    }

    private void showPayInvoicesToView(List<PayInvoices> poListPayInvoices) {
        List<PayInvoicesModel> loPayInvoicesModel = goPayInvoicesModelMMapper.transform(poListPayInvoices);
        if(loPayInvoicesModel == null){
            loPayInvoicesModel = new ArrayList<>();
        }
        this.goPayInvoicesView.showSucessListPayInvoices(loPayInvoicesModel);
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
        GetListPayInvoicesUseCase.Params params = GetListPayInvoicesUseCase.Params.forValidate(nisRad,pageNum,pageSize);
        this.goGetListPayInvoicesUseCase.execute(new PayInvoicesObserver(),params);
    }

    private final class PayInvoicesObserver extends DefaultObserver<List<PayInvoices>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goPayInvoicesView.onSessionExpired(poException.getMessage());
            }

            PayInvoicesPresenter.this.showErrorMessage(poException.getMessage());
            PayInvoicesPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<PayInvoices> poData) {
            PayInvoicesPresenter.this.showPayInvoicesToView(poData);
        }
    }
}
