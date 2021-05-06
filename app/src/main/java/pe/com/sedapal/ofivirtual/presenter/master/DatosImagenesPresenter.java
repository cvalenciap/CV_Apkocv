package pe.com.sedapal.ofivirtual.presenter.master;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.DatosImagenes;
import pe.com.sedapal.ofivirtual.domain.entity.User;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.master.GetDatosImagenesUseCase;
import pe.com.sedapal.ofivirtual.model.DatosImagenesModel;
import pe.com.sedapal.ofivirtual.model.mapper.DatosImagenesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.DatosImagenesView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 02/04/2019.
 */
public class DatosImagenesPresenter implements Presenter {

    private DatosImagenesView goDatosImagenesView;

    private final GetDatosImagenesUseCase goGetDatosImagenesUseCase;
    private final DatosImagenesModelMapper goDatosImagenesModelMapper;

    /**
     * Constructs a {@link DatosImagenesPresenter}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    @Inject
    public DatosImagenesPresenter(GetDatosImagenesUseCase poGetDatosImagenesUseCase, DatosImagenesModelMapper poDatosImagenesModelMapper) {
        this.goGetDatosImagenesUseCase = poGetDatosImagenesUseCase;
        this.goDatosImagenesModelMapper = poDatosImagenesModelMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @param poDatosImagenesView {@link LoginView}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public void setView(@NonNull DatosImagenesView poDatosImagenesView) {
        this.goDatosImagenesView = poDatosImagenesView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetDatosImagenesUseCase.dispose();
        this.goDatosImagenesView = null;
    }

    /**
     * Initialize login the presenter by login in the user.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public void initialize() {
        this.login();
    }


    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void login() {
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
        //this.goDatosImagenesView.showProgressLoading();
    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void hideViewLoading() {
        //this.goDatosImagenesView.hideProgressLoading();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showViewRetry() {
        this.goDatosImagenesView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void hideViewRetry() {
        this.goDatosImagenesView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showErrorMessage(String psErrorMessage) {
        this.goDatosImagenesView.showError(psErrorMessage);
    }

    /**
     * Show result login
     *
     * @param poDatosImagenes {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showLoginSuccessToView(DatosImagenes poDatosImagenes) {
        DatosImagenesModel loUserModel = goDatosImagenesModelMapper.transform(poDatosImagenes);
        this.goDatosImagenesView.showDatosImagenesSuccess(loUserModel);
    }

    /**
     * Execute.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void goGetData() {
        String psCategoria = Constants.PARAMETROS_CATEGORIA.DATOS_IMAGENES;
        GetDatosImagenesUseCase.Params loParams = GetDatosImagenesUseCase.Params.forValidate(psCategoria);
        this.goGetDatosImagenesUseCase.execute(new DatosImagenesObserver(), loParams);
    }

    private final class DatosImagenesObserver extends DefaultObserver<DatosImagenes> {

        @Override
        public void onComplete() {
            DatosImagenesPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            DatosImagenesPresenter.this.hideViewLoading();
            DatosImagenesPresenter.this.showErrorMessage(poException.getMessage());
            DatosImagenesPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(DatosImagenes poData) {
            DatosImagenesPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
