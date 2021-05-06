package pe.com.sedapal.ofivirtual.presenter.master;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.DatosVisa;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.master.GetDatosVisaUseCase;
import pe.com.sedapal.ofivirtual.model.DatosVisaModel;
import pe.com.sedapal.ofivirtual.model.mapper.DatosVisaModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.presenter.view.ParametersVisaView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 02/04/2019.
 */
public class ParametersVisaPresenter implements Presenter {

    private ParametersVisaView goParametersVisaView;

    private final GetDatosVisaUseCase goGetDatosVisaUseCase;
    private final DatosVisaModelMapper goDatosVisaModelMapper;

    /**
     * Constructs a {@link ParametersVisaPresenter}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    @Inject
    public ParametersVisaPresenter(GetDatosVisaUseCase poGetDatosVisaUseCase, DatosVisaModelMapper poDatosVisaModelMapper) {
        this.goGetDatosVisaUseCase = poGetDatosVisaUseCase;
        this.goDatosVisaModelMapper = poDatosVisaModelMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poParametersVisaView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public void setView(@NonNull ParametersVisaView poParametersVisaView) {
        this.goParametersVisaView = poParametersVisaView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetDatosVisaUseCase.dispose();
        this.goParametersVisaView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public void initializeParametersVisa() {
        this.doGetParametersVisa();
    }


    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void doGetParametersVisa() {
        this.hideViewRetry();
        this.showViewLoading();
        this.goGetData();
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showViewLoading() {
        this.goParametersVisaView.showProgressLoading();
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void hideViewLoading() {
        this.goParametersVisaView.hideProgressLoading();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showViewRetry() {
        this.goParametersVisaView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void hideViewRetry() {
        this.goParametersVisaView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goParametersVisaView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poDatosVisa {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showLoginSuccessToView(DatosVisa poDatosVisa) {
        DatosVisaModel loUserModel = goDatosVisaModelMapper.transform(poDatosVisa);
        this.goParametersVisaView.showDatosVisaSuccess(loUserModel);
    }

    /**
     * Execute.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void goGetData() {
        String psCategoria = Constants.PARAMETROS_CATEGORIA.DATOS_VISA;
        GetDatosVisaUseCase.Params loParams = GetDatosVisaUseCase.Params.forValidate(psCategoria);
        this.goGetDatosVisaUseCase.execute(new DatosVisaObserver(), loParams);
    }

    private final class DatosVisaObserver extends DefaultObserver<DatosVisa> {

        @Override
        public void onComplete() {
            ParametersVisaPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            ParametersVisaPresenter.this.hideViewLoading();

            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goParametersVisaView.onSessionExpired(poException.getMessage());
            }else {
                ParametersVisaPresenter.this.showErrorMessage(poException.getMessage());
                ParametersVisaPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(DatosVisa poData) {
            ParametersVisaPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
