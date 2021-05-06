package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.Agency;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetListAgencyUseCase;
import pe.com.sedapal.ofivirtual.model.AgencyModel;
import pe.com.sedapal.ofivirtual.model.mapper.ListAgencyModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ListAgencyView;
import pe.com.sedapal.ofivirtual.util.Constants;

public class ListAgencyPresenter implements Presenter {

    private ListAgencyView goListAgencyView;

    private final GetListAgencyUseCase goGetListAgencyUseCase;
    private final ListAgencyModelMapper goListAgencyModelMapper;

    @Inject
    public ListAgencyPresenter(GetListAgencyUseCase poGetListAgencyUseCase, ListAgencyModelMapper poListAgencyModelMapper) {
        this.goGetListAgencyUseCase = poGetListAgencyUseCase;
        this.goListAgencyModelMapper = poListAgencyModelMapper;
    }

    public void setView(@NonNull ListAgencyView poListAgencyView) {
        this.goListAgencyView = poListAgencyView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goListAgencyView = null;
    }

    public void initialize() {
        this.hideViewRetry();
        this.showViewLoading();
        this.doObtain();
    }

    private void showViewLoading() {
        if(this.goListAgencyView != null)
        this.goListAgencyView.showLoadingPersonalized();
    }

    private void hideViewLoading() {
        if(this.goListAgencyView != null)
        this.goListAgencyView.hideLoadingPersonalized();
    }

    private void showViewRetry() {
        if(this.goListAgencyView != null)
        this.goListAgencyView.showRetry();
    }

    private void hideViewRetry() {
        if(this.goListAgencyView != null)
        this.goListAgencyView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        if(this.goListAgencyView != null)
        this.goListAgencyView.showError(psErrorMessage);
    }

    private void showAgencyToView(List<Agency> poListMunicipalities) {
        List<AgencyModel> loAgencyModel = goListAgencyModelMapper.transform(poListMunicipalities);
        if(loAgencyModel == null){
            loAgencyModel = new ArrayList<>();
        }
        this.goListAgencyView.showSucessListAgency(loAgencyModel);
    }

    /**
     *
     * @author jsaenz
     * @version 1.0
     * @since 21/03/2019
     */
    private void doObtain() {
        this.goGetListAgencyUseCase.execute(new ListAgencyPresenter.AgencyObserver(),null);
    }

    private final class AgencyObserver extends DefaultObserver<List<Agency>> {

        @Override
        public void onComplete() {
            ListAgencyPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ListAgencyPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goListAgencyView.onSessionExpired(poException.getMessage());
            }else {
                ListAgencyPresenter.this.showErrorMessage(poException.getMessage());
                ListAgencyPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<Agency> poData) {
            ListAgencyPresenter.this.showAgencyToView(poData);
        }
    }
}
