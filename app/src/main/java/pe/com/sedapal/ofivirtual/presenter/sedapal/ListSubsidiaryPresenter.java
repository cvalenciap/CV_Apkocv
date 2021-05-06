package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.Subsidiary;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetListSubsidiaryUseCase;
import pe.com.sedapal.ofivirtual.model.SubsidiaryModel;
import pe.com.sedapal.ofivirtual.model.mapper.ListSubsidiaryModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ListSubsidiaryView;
import pe.com.sedapal.ofivirtual.util.Constants;

public class ListSubsidiaryPresenter implements Presenter {

    private ListSubsidiaryView goListSubsidiaryView;

    private final GetListSubsidiaryUseCase goGetListSubsidiaryUseCase;
    private final ListSubsidiaryModelMapper goListSubsidiaryModelMapper;

    @Inject
    public ListSubsidiaryPresenter(GetListSubsidiaryUseCase poGetListSubsidiaryUseCase, ListSubsidiaryModelMapper poListSubsidiaryModelMapper) {
        this.goGetListSubsidiaryUseCase = poGetListSubsidiaryUseCase;
        this.goListSubsidiaryModelMapper = poListSubsidiaryModelMapper;
    }

    public void setView(@NonNull ListSubsidiaryView poListSubsidiaryView) {
        this.goListSubsidiaryView = poListSubsidiaryView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goListSubsidiaryView = null;
        this.goGetListSubsidiaryUseCase.dispose();
    }

    public void initialize(long codAgencia) {
        this.hideViewRetry();
        this.showViewLoading();
        this.doObtain(codAgencia);
    }

    private void showViewLoading() {
        this.goListSubsidiaryView.showLoadingPersonalizedSubsidiary();
    }

    private void hideViewLoading() {
        this.goListSubsidiaryView.hideLoadingPersonalizedSubsidiary();
    }

    private void showViewRetry() {
        this.goListSubsidiaryView.showRetry();
    }

    private void hideViewRetry() {
        this.goListSubsidiaryView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goListSubsidiaryView.showError(psErrorMessage);
    }

    private void showMunicipalitiesToView(List<Subsidiary> poListSubsidiary) {
        List<SubsidiaryModel> loSubsidiaryModel = goListSubsidiaryModelMapper.transform(poListSubsidiary);
        if(loSubsidiaryModel == null){
            loSubsidiaryModel = new ArrayList<>();
        }
        this.goListSubsidiaryView.showSucessListSubsidiary(loSubsidiaryModel);
    }

    /**
     * Execute list subsidiary
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain(long codAgencia) {
        GetListSubsidiaryUseCase.Params loParams = GetListSubsidiaryUseCase.Params.forValidate(codAgencia);
        this.goGetListSubsidiaryUseCase.execute(new ListSubsidiaryPresenter.SubsidiaryObserver(),loParams);
    }

    private final class SubsidiaryObserver extends DefaultObserver<List<Subsidiary>> {

        @Override
        public void onComplete() {
            ListSubsidiaryPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ListSubsidiaryPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goListSubsidiaryView.onSessionExpired(poException.getMessage());
            }else {
                ListSubsidiaryPresenter.this.showErrorMessage(poException.getMessage());
                ListSubsidiaryPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<Subsidiary> poData) {
            ListSubsidiaryPresenter.this.showMunicipalitiesToView(poData);
        }
    }
}
