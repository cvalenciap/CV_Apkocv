package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetDetailInvoicesUseCase;
import pe.com.sedapal.ofivirtual.model.DetailInvoicesModel;
import pe.com.sedapal.ofivirtual.model.mapper.DetailInvoicesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.DetailInvoicesView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */
public class DetailInvoicesPresenter implements Presenter {

    private DetailInvoicesView goDetailInvoicesView;

    private final GetDetailInvoicesUseCase goGetDetailInvoicesUseCase;
    private final DetailInvoicesModelMapper goDetailInvoicesModelMMapper;

    @Inject
    public DetailInvoicesPresenter(GetDetailInvoicesUseCase poGetDetailInvoicesUseCase, DetailInvoicesModelMapper poDetailInvoicesModelMMapper) {
        this.goGetDetailInvoicesUseCase = poGetDetailInvoicesUseCase;
        this.goDetailInvoicesModelMMapper = poDetailInvoicesModelMMapper;
    }

    public void setView(@NonNull DetailInvoicesView poDetailInvoicesView) {
        this.goDetailInvoicesView = poDetailInvoicesView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goDetailInvoicesView = null;
        this.goGetDetailInvoicesUseCase.dispose();
    }

    public void initialize(long recibo) {
        this.hideViewRetry();
        this.doObtain(recibo);
    }

    private void showViewRetry() {
        this.goDetailInvoicesView.showRetry();
    }

    private void hideViewRetry() {
        this.goDetailInvoicesView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goDetailInvoicesView.showError(psErrorMessage);
    }

    private void showDetailInvoicesToView(List<DetailInvoices> poListDetailInvoices) {
        List<DetailInvoicesModel> loDetailInvoicesModel = goDetailInvoicesModelMMapper.transform(poListDetailInvoices);
        if(loDetailInvoicesModel == null){
            loDetailInvoicesModel = new ArrayList<>();
        }
        this.goDetailInvoicesView.showSucessListPendingInvoices(loDetailInvoicesModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     * @param recibo
     */
    private void doObtain(long recibo) {
        GetDetailInvoicesUseCase.Params params = GetDetailInvoicesUseCase.Params.forValidate(recibo);
        this.goGetDetailInvoicesUseCase.execute(new DetailInvoicesObserver(),params);
    }

    private final class DetailInvoicesObserver extends DefaultObserver<List<DetailInvoices>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goDetailInvoicesView.onSessionExpired(poException.getMessage());
            }else {
                DetailInvoicesPresenter.this.showErrorMessage(poException.getMessage());
                DetailInvoicesPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<DetailInvoices> poData) {
            DetailInvoicesPresenter.this.showDetailInvoicesToView(poData);
        }
    }
}
