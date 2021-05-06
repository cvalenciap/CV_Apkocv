package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.HistoricConsum;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetListHistoricConsumUseCase;
import pe.com.sedapal.ofivirtual.model.HistoricConsumModel;
import pe.com.sedapal.ofivirtual.model.mapper.ListHistoricConsumModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ListHistoricConsumView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 1/03/2019.
 */
public class ListHistoricConsumPresenter implements Presenter {

    private ListHistoricConsumView goHistoricConsumView;

    private final GetListHistoricConsumUseCase goGetListHistoricConsumUseCase;
    private final ListHistoricConsumModelMapper goListHistoricConsumModelMapper;

    @Inject
    public ListHistoricConsumPresenter(GetListHistoricConsumUseCase poGetListHistoricConsumUseCase, ListHistoricConsumModelMapper poListHistoricConsumModelMapper) {
        this.goGetListHistoricConsumUseCase = poGetListHistoricConsumUseCase;
        this.goListHistoricConsumModelMapper = poListHistoricConsumModelMapper;
    }

    public void setView(@NonNull ListHistoricConsumView poHistoricConsumView) {
        this.goHistoricConsumView = poHistoricConsumView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goHistoricConsumView = null;
        this.goGetListHistoricConsumUseCase.dispose();
    }

    public void initialize(long poNisRad) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doObtain(poNisRad);
    }

    private void showViewLoading() {
        //this.goLoginNewUserView.showLoading(this.goLoginNewUserView.context().getString(R.string.lbl_progressdialog_validando_usuario));
        this.goHistoricConsumView.showProgessDialog();

    }

    private void hideViewLoading() {
        this.goHistoricConsumView.hideProgessDialog();
    }

    private void hideViewLoadingError() {
        this.goHistoricConsumView.errorProgressDialog();
    }

    private void showViewRetry() {
        this.goHistoricConsumView.showRetry();
    }

    private void hideViewRetry() {
        this.goHistoricConsumView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goHistoricConsumView.showErrorMessageListHistoricConsum(psErrorMessage);
    }

    private void showHistoricConsumToView(List<HistoricConsum> poListHistoricConsum) {
        List<HistoricConsumModel> loHistoricConsumModel = goListHistoricConsumModelMapper.transform(poListHistoricConsum);
        if(loHistoricConsumModel == null){
            loHistoricConsumModel = new ArrayList<>();
        }
        this.goHistoricConsumView.showSucessListHistoricConsum(loHistoricConsumModel);
    }

    /**
     *
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    private void doObtain(long poNisRad) {
        GetListHistoricConsumUseCase.Params params = GetListHistoricConsumUseCase.Params.forObtain(poNisRad);
        this.goGetListHistoricConsumUseCase.execute(new HistoricConsumObserver(),params);
    }

    private final class HistoricConsumObserver extends DefaultObserver<List<HistoricConsum>> {

        @Override
        public void onComplete() {
            ListHistoricConsumPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goHistoricConsumView.onSessionExpired(poException.getMessage());
            }

            ListHistoricConsumPresenter.this.hideViewLoadingError();
            ListHistoricConsumPresenter.this.showErrorMessage(poException.getMessage());
            ListHistoricConsumPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<HistoricConsum> poData) {
            ListHistoricConsumPresenter.this.showHistoricConsumToView(poData);
        }
    }
}
