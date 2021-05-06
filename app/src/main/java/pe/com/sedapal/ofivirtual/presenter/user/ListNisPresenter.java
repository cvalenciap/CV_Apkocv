package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.Nis;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetListNisUseCase;
import pe.com.sedapal.ofivirtual.model.NisModel;
import pe.com.sedapal.ofivirtual.model.mapper.ListNisModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ListNisView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 1/03/2019.
 */
public class ListNisPresenter implements Presenter {

    private ListNisView goNisView;

    private final GetListNisUseCase goGetListNisUseCase;
    private final ListNisModelMapper goListNisModelMapper;

    @Inject
    public ListNisPresenter(GetListNisUseCase poGetListNisUseCase, ListNisModelMapper poListNisModelMapper) {
        this.goGetListNisUseCase = poGetListNisUseCase;
        this.goListNisModelMapper = poListNisModelMapper;
    }

    public void setView(@NonNull ListNisView poNisView) {
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
        this.goGetListNisUseCase.dispose();
    }

    public void initialize(long nisRad, String correo, String flag) {
        this.hideViewRetry();
        this.doObtain(nisRad, correo, flag);
    }

    private void showViewRetry() {
        this.goNisView.showRetry();
    }

    private void hideViewRetry() {
        this.goNisView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goNisView.showErrorMessageListNis(psErrorMessage);
    }

    private void showNisToView(List<Nis> poListNis) {
        List<NisModel> loNisModel = goListNisModelMapper.transform(poListNis);
        if(loNisModel == null){
            loNisModel = new ArrayList<>();
        }
        this.goNisView.showSucessListNis(loNisModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 11/03/2019
     */
    private void doObtain(long nisRad,String correo, String flag) {
        GetListNisUseCase.Params params = GetListNisUseCase.Params.forValidate(nisRad, correo, flag);
        this.goGetListNisUseCase.execute(new NisObserver(),params);
    }

    private final class NisObserver extends DefaultObserver<List<Nis>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goNisView.onSessionExpired(poException.getMessage());
            }

            ListNisPresenter.this.showErrorMessage(poException.getMessage());
            ListNisPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Nis> poData) {
            ListNisPresenter.this.showNisToView(poData);
        }
    }
}
