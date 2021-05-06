package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.ApplicantType;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetListApplicantTypeUseCase;
import pe.com.sedapal.ofivirtual.model.ApplicantTypeModel;
import pe.com.sedapal.ofivirtual.model.mapper.ApplicantTypeModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ApplicantTypeView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class ApplicantTypePresenter implements Presenter {

    private ApplicantTypeView goApplicantTypeView;

    private final GetListApplicantTypeUseCase goGetListApplicantTypeUseCase;
    private final ApplicantTypeModelMapper goApplicantTypeModelMapper;

    @Inject
    public ApplicantTypePresenter(GetListApplicantTypeUseCase poGetListApplicantTypeUseCase, ApplicantTypeModelMapper poApplicantTypeModelMapper) {
        this.goGetListApplicantTypeUseCase = poGetListApplicantTypeUseCase;
        this.goApplicantTypeModelMapper = poApplicantTypeModelMapper;
    }

    public void setView(@NonNull ApplicantTypeView poSuministroView) {
        this.goApplicantTypeView = poSuministroView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goApplicantTypeView = null;
    }

    public void initialize() {
        this.hideViewRetry();
        this.doObtain();
    }

    private void showViewRetry() {
        this.goApplicantTypeView.showRetry();
    }

    private void hideViewRetry() {
        this.goApplicantTypeView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goApplicantTypeView.showError(psErrorMessage);
    }

    private void showSuministroSuccessToView(List<ApplicantType> poListApplicantType) {
        List<ApplicantTypeModel> loApplicantTypeModel = goApplicantTypeModelMapper.transform(poListApplicantType);
        if(loApplicantTypeModel == null){
            loApplicantTypeModel = new ArrayList<>();
        }
        this.goApplicantTypeView.showSucessApplicantType(loApplicantTypeModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain() {
       this.goGetListApplicantTypeUseCase.execute(new ApplicantTypeObserver(),null);
    }

    private final class ApplicantTypeObserver extends DefaultObserver<List<ApplicantType>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            ApplicantTypePresenter.this.showErrorMessage(poException.getMessage());
            ApplicantTypePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ApplicantType> poData) {
            ApplicantTypePresenter.this.showSuministroSuccessToView(poData);
        }
    }
}
