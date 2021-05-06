package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.NisDetail;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetDetailNisUseCase;
import pe.com.sedapal.ofivirtual.model.NisDetailModel;
import pe.com.sedapal.ofivirtual.model.mapper.NisDetailModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.DetailNisView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 1/03/2019.
 */
public class DetailNisPresenter implements Presenter {

    private DetailNisView goNisView;

    private final GetDetailNisUseCase goGetDetailNisUseCase;
    private final NisDetailModelMapper goNisDetailModelMapper;

    @Inject
    public DetailNisPresenter(GetDetailNisUseCase poGetDetailNisUseCase, NisDetailModelMapper poNisDetailModelMapper) {
        this.goGetDetailNisUseCase = poGetDetailNisUseCase;
        this.goNisDetailModelMapper = poNisDetailModelMapper;
    }

    public void setView(@NonNull DetailNisView poNisView) {
        this.goNisView = poNisView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goNisView = null;
        this.goGetDetailNisUseCase.dispose();
    }

    public void initialize(long nisRad, String correo, String flag, String flag_multiple) {
        this.hideViewRetry();
        this.doObtain(nisRad, correo, flag, flag_multiple);
    }

    private void showViewRetry() {
        this.goNisView.showRetry();
    }

    private void hideViewRetry() {
        this.goNisView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goNisView.showErrorMessageDetailNis(psErrorMessage);
    }

    private void showNisToView(NisDetail poDetailNis) {
        NisDetailModel loNisModel = goNisDetailModelMapper.transform(poDetailNis);
        this.goNisView.showSucessDetailNis(loNisModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     * @param nisRad
     */
    private void doObtain(long nisRad, String correo, String flag, String flag_multiple) {
        GetDetailNisUseCase.Params params = GetDetailNisUseCase.Params.forValidate(nisRad, correo, flag,flag_multiple);
        this.goGetDetailNisUseCase.execute(new NisObserver(),params);
    }

    private final class NisObserver extends DefaultObserver<NisDetail> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goNisView.onSessionExpired(poException.getMessage());
            }

            DetailNisPresenter.this.showErrorMessage(poException.getMessage());
            DetailNisPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(NisDetail poData) {
            DetailNisPresenter.this.showNisToView(poData);
        }
    }
}
