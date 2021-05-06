package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.IncidentsMunicipalities;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetIncidentsMunicipalitiesUseCase;
import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.mapper.IncidentsMunicipalitiesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.IncidentsMunicipalitiesView;
import pe.com.sedapal.ofivirtual.util.Constants;

public class IncidentsMunicipalitiesPresenter implements Presenter {

    private IncidentsMunicipalitiesView goIncidentsMunicipalitiesView;

    private final GetIncidentsMunicipalitiesUseCase goGetIncidentsMunicipalitiesUseCase;
    private final IncidentsMunicipalitiesModelMapper goIncidentsMunicipalitiesModelMMapper;

    @Inject
    public IncidentsMunicipalitiesPresenter(GetIncidentsMunicipalitiesUseCase poGetIncidentsMunicipalitiesUseCase, IncidentsMunicipalitiesModelMapper poIncidentsMunicipalitiesModelMMapper) {
        this.goGetIncidentsMunicipalitiesUseCase = poGetIncidentsMunicipalitiesUseCase;
        this.goIncidentsMunicipalitiesModelMMapper = poIncidentsMunicipalitiesModelMMapper;
    }

    public void setView(@NonNull IncidentsMunicipalitiesView poIncidentsMunicipalitiesView) {
        this.goIncidentsMunicipalitiesView = poIncidentsMunicipalitiesView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goIncidentsMunicipalitiesView = null;
        this.goGetIncidentsMunicipalitiesUseCase.dispose();
    }

    public void initialize(int codMunicipio) {
        this.hideViewRetry();
        this.doObtain(codMunicipio);
    }

    private void showViewLoading() {
        this.goIncidentsMunicipalitiesView.showLoadingPersonalized();
    }

    private void hideViewLoading() {
        this.goIncidentsMunicipalitiesView.hideLoadingPersonalized();
    }

    private void showViewRetry() {
        this.goIncidentsMunicipalitiesView.showRetry();
    }

    private void hideViewRetry() {
        this.goIncidentsMunicipalitiesView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goIncidentsMunicipalitiesView.showError(psErrorMessage);
    }

    private void showIncidentsMunicipalitiesToView(List<IncidentsMunicipalities> poListIncidentsMunicipalities) {
        List<IncidentsMunicipalitiesModel> loIncidentsMunicipalitiesModel = goIncidentsMunicipalitiesModelMMapper.transform(poListIncidentsMunicipalities);
        if(loIncidentsMunicipalitiesModel == null){
            loIncidentsMunicipalitiesModel = new ArrayList<>();
        }
        this.goIncidentsMunicipalitiesView.showSucessListIncidentsMunicipalities(loIncidentsMunicipalitiesModel);
    }

    /**
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     * @param codMunicipio
     */
    private void doObtain(int codMunicipio) {
        GetIncidentsMunicipalitiesUseCase.Params loParams = GetIncidentsMunicipalitiesUseCase.Params.forValidate(codMunicipio);
        this.goGetIncidentsMunicipalitiesUseCase.execute(new IncidentsMunicipalitiesPresenter.IncidentsMunicipalitiesObserver(),loParams);
    }

    private final class IncidentsMunicipalitiesObserver extends DefaultObserver<List<IncidentsMunicipalities>> {

        @Override
        public void onComplete() {
            IncidentsMunicipalitiesPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            IncidentsMunicipalitiesPresenter.this.hideViewLoading();

            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goIncidentsMunicipalitiesView.onSessionExpired(poException.getMessage());
            }else {
                IncidentsMunicipalitiesPresenter.this.showErrorMessage(poException.getMessage());
                IncidentsMunicipalitiesPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<IncidentsMunicipalities> poData) {
            IncidentsMunicipalitiesPresenter.this.showIncidentsMunicipalitiesToView(poData);
        }
    }
}
