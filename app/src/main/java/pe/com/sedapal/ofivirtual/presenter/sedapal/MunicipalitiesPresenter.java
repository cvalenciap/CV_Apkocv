package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.Municipalities;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetMunicipalitiesUseCase;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.mapper.MunicipalitiesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.MunicipalitiesView;
import pe.com.sedapal.ofivirtual.util.Constants;

public class MunicipalitiesPresenter implements Presenter {

    private MunicipalitiesView goMunicipalitiesView;

    private final GetMunicipalitiesUseCase goGetMunicipalitiesUseCase;
    private final MunicipalitiesModelMapper goMunicipalitiesModelMMapper;

    @Inject
    public MunicipalitiesPresenter(GetMunicipalitiesUseCase poGetMunicipalitiesUseCase, MunicipalitiesModelMapper poMunicipalitiesModelMMapper) {
        this.goGetMunicipalitiesUseCase = poGetMunicipalitiesUseCase;
        this.goMunicipalitiesModelMMapper = poMunicipalitiesModelMMapper;
    }

    public void setView(@NonNull MunicipalitiesView poMunicipalitiesView) {
        this.goMunicipalitiesView = poMunicipalitiesView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goMunicipalitiesView = null;
        this.goGetMunicipalitiesUseCase.dispose();
    }

    public void initialize() {
        this.hideViewRetry();
        this.showViewLoading();
        this.doObtain();
    }

    private void showViewLoading() {
        this.goMunicipalitiesView.showLoadingPersonalized();
    }

    private void hideViewLoading() {
        this.goMunicipalitiesView.hideLoadingPersonalized();
    }

    private void showViewRetry() {
        this.goMunicipalitiesView.showRetry();
    }

    private void hideViewRetry() {
        this.goMunicipalitiesView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goMunicipalitiesView.showError(psErrorMessage);
    }

    private void showMunicipalitiesToView(List<Municipalities> poListMunicipalities) {
        List<MunicipalitiesModel> loMunicipalitiesModel = goMunicipalitiesModelMMapper.transform(poListMunicipalities);
        if(loMunicipalitiesModel == null){
            loMunicipalitiesModel = new ArrayList<>();
        }
        this.goMunicipalitiesView.showSucessListMunicipalities(loMunicipalitiesModel);
    }

    /**
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain() {
        this.goGetMunicipalitiesUseCase.execute(new MunicipalitiesPresenter.MunicipalitiesObserver(),null);
    }

    private final class MunicipalitiesObserver extends DefaultObserver<List<Municipalities>> {

        @Override
        public void onComplete() {
            MunicipalitiesPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            MunicipalitiesPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goMunicipalitiesView.onSessionExpired(poException.getMessage());
            }else {
                MunicipalitiesPresenter.this.showErrorMessage(poException.getMessage());
                MunicipalitiesPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(List<Municipalities> poData) {
            MunicipalitiesPresenter.this.showMunicipalitiesToView(poData);
        }
    }
}
