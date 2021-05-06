package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetDetailPayInvoiceUseCase;
import pe.com.sedapal.ofivirtual.model.DetailPayInvoiceModel;
import pe.com.sedapal.ofivirtual.model.mapper.DetailPayInvoiceModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.DetailPayInvoiceView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 07/03/2019.
 */
public class DetailPayInvoicePresenter implements Presenter {

    private DetailPayInvoiceView goDetailPayInvoiceView;

    private final GetDetailPayInvoiceUseCase goGetDetailPayInvoiceUseCase;
    private final DetailPayInvoiceModelMapper goDetailPayInvoiceModelMMapper;

    @Inject
    public DetailPayInvoicePresenter(GetDetailPayInvoiceUseCase poGetDetailPayInvoiceUseCase, DetailPayInvoiceModelMapper poDetailPayInvoiceModelMMapper) {
        this.goGetDetailPayInvoiceUseCase = poGetDetailPayInvoiceUseCase;
        this.goDetailPayInvoiceModelMMapper = poDetailPayInvoiceModelMMapper;
    }

    public void setView(@NonNull DetailPayInvoiceView poDetailPayInvoiceView) {
        this.goDetailPayInvoiceView = poDetailPayInvoiceView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goDetailPayInvoiceView = null;
        this.goGetDetailPayInvoiceUseCase.dispose();
    }

    public void initialize(String psRecibo, String psNumFact) {
        this.hideViewRetry();
        this.doObtain(psRecibo,psNumFact);
    }

    private void showViewRetry() {
        this.goDetailPayInvoiceView.showRetry();
    }

    private void hideViewRetry() {
        this.goDetailPayInvoiceView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goDetailPayInvoiceView.showError(psErrorMessage);
    }

    private void hideLoadingPay(){
        this.goDetailPayInvoiceView.hideLoadingDetailPay();
    }

    private void showDetailPayInvoiceToView(List<DetailPayInvoice> poListDetailPayInvoice) {
        List<DetailPayInvoiceModel> loDetailPayInvoiceModel = goDetailPayInvoiceModelMMapper.transform(poListDetailPayInvoice);
        if(loDetailPayInvoiceModel == null){
            loDetailPayInvoiceModel = new ArrayList<>();
        }
        this.goDetailPayInvoiceView.showSucessListDetailPay(loDetailPayInvoiceModel);
    }

    /**
     * Execute doObtain
     *
     * @author jsaenz
     * @version 1.0
     * @since 07/03/2019
     */
    private void doObtain(String psRecibo, String psNumFact) {
        GetDetailPayInvoiceUseCase.Params params = GetDetailPayInvoiceUseCase.Params.forValidate(psRecibo,psNumFact);
        this.goGetDetailPayInvoiceUseCase.execute(new DetailPayInvoiceObserver(),params);
    }

    private final class DetailPayInvoiceObserver extends DefaultObserver<List<DetailPayInvoice>> {

        @Override
        public void onComplete() {
            DetailPayInvoicePresenter.this.hideLoadingPay();
        }

        @Override
        public void onError(Throwable poException) {
            DetailPayInvoicePresenter.this.hideLoadingPay();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goDetailPayInvoiceView.onSessionExpired(poException.getMessage());
            }else {
                DetailPayInvoicePresenter.this.showErrorMessage(poException.getMessage());
                DetailPayInvoicePresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<DetailPayInvoice> poData) {
            DetailPayInvoicePresenter.this.hideLoadingPay();
            DetailPayInvoicePresenter.this.showDetailPayInvoiceToView(poData);
        }
    }
}
