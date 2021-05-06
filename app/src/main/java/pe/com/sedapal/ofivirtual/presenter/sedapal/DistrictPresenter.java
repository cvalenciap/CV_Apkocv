package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetListDistrictUseCase;
import pe.com.sedapal.ofivirtual.model.DistrictModel;
import pe.com.sedapal.ofivirtual.model.mapper.DistrictModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.DistrictView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class DistrictPresenter implements Presenter {

    private DistrictView goDistrictView;

    private final GetListDistrictUseCase goGetListDistrictUseCase;
    private final DistrictModelMapper goDistrictModelMMapper;

    @Inject
    public DistrictPresenter(GetListDistrictUseCase poGetListDistrictUseCase, DistrictModelMapper poDistrictModelMMapper) {
        this.goGetListDistrictUseCase = poGetListDistrictUseCase;
        this.goDistrictModelMMapper = poDistrictModelMMapper;
    }

    public void setView(@NonNull DistrictView poDistrictView) {
        this.goDistrictView = poDistrictView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goDistrictView = null;
        this.goGetListDistrictUseCase.dispose();
    }

    public void initialize() {
        this.hideViewRetry();
        this.doObtain();
    }

    private void showViewRetry() {
        this.goDistrictView.showRetry();
    }

    private void hideViewRetry() {
        this.goDistrictView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goDistrictView.showError(psErrorMessage);
    }

    private void showDistrictToView(List<District> poListDistrict) {
        List<DistrictModel> loDistrictModel = goDistrictModelMMapper.transform(poListDistrict);
        if(loDistrictModel == null){
            loDistrictModel = new ArrayList<>();
        }
        this.goDistrictView.showSucessListDictrict(loDistrictModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain() {
       this.goGetListDistrictUseCase.execute(new DistrictObserver(),null);
    }

    private final class DistrictObserver extends DefaultObserver<List<District>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            DistrictPresenter.this.showErrorMessage(poException.getMessage());
            DistrictPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<District> poData) {
            DistrictPresenter.this.showDistrictToView(poData);
        }
    }
}
