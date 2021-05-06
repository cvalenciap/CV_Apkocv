package pe.com.sedapal.ofivirtual.presenter.enchufate;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.Liquidacion;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.enchufate.GetLiquidacionEnchufateUseCase;
import pe.com.sedapal.ofivirtual.model.LiquidacionModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.RequestLiquidacionEnchufateModel;
import pe.com.sedapal.ofivirtual.model.mapper.LiquidacionModelMapper;
import pe.com.sedapal.ofivirtual.model.mapper.RequestLiquidationInvoicesModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.LiquidacionEnchufateView;
import pe.com.sedapal.ofivirtual.presenter.view.LoginView;


/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 02/04/2019.
 */
public class GenerateLiquidacionEnchufatePresenter implements Presenter {

    private LiquidacionEnchufateView goLiquidacionEnchufateView;
    private final GetLiquidacionEnchufateUseCase goGetLiquidacionUseCase;
    private final LiquidacionModelMapper goLiquidacionModelMapper;
    private final RequestLiquidationInvoicesModelMapper goRequestLiquidationInvoicesModelMapper;


    /**
     * Constructs a {@link GenerateLiquidacionEnchufatePresenter}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    @Inject
    public GenerateLiquidacionEnchufatePresenter(GetLiquidacionEnchufateUseCase poGetLiquidacionUseCase,
                                                 LiquidacionModelMapper poLiquidacionModelMapper,
                                                 RequestLiquidationInvoicesModelMapper poRequestLiquidationInvoicesModelMapper) {
        this.goGetLiquidacionUseCase = poGetLiquidacionUseCase;
        this.goLiquidacionModelMapper = poLiquidacionModelMapper;
        this.goRequestLiquidationInvoicesModelMapper = poRequestLiquidationInvoicesModelMapper;
    }

    /**
     * Set View {@link LoginView} to listener on Fragment or Activity
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public void setView(@NonNull LiquidacionEnchufateView poLiquidacionEnchufateView) {
        this.goLiquidacionEnchufateView = poLiquidacionEnchufateView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetLiquidacionUseCase.dispose();
        this.goLiquidacionEnchufateView = null;
    }

    /**
     * Initialize login the presenter get data enchufate.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    public void initialize(long nisRad, List<PendingInvoicesModel> listPendingInvoices) {
        this.getLiquidacion(listPendingInvoices, nisRad);
    }

    /**
     * The user login.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void getLiquidacion(List<PendingInvoicesModel> listPendingInvoices, long nisRad) {
        this.hideViewRetry();
        this.showViewLoadingGenerateLiqu();
        this.goGetLiquidacion(listPendingInvoices, nisRad);
    }

    /**
     * Show view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showViewLoadingGenerateLiqu() {

    }

    /**
     * Hide view loading.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void hideViewLoadingGenerateLiqu() {
        this.goLiquidacionEnchufateView.hideLoading();
    }

    /**
     * Show view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showViewRetry() {
        this.goLiquidacionEnchufateView.showRetry();
    }

    /**
     * Hide view retry.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void hideViewRetry() {
        this.goLiquidacionEnchufateView.hideRetry();
    }

    /**
     * Show error message.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showErrorMessage(String psErrorMessage) {
        //this.goLiquidacionEnchufateView.showError(psErrorMessage);
        this.goLiquidacionEnchufateView.showPayError();
    }

    /**
     * Show result login
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void showDataSuccessToView(Liquidacion poLiquidacion) {
        LiquidacionModel loUserModel = goLiquidacionModelMapper.transform(poLiquidacion);
        this.goLiquidacionEnchufateView.showGenerateLiquidacionSuccess(loUserModel);
    }

    /**
     * Execute.
     *
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    private void goGetLiquidacion(List<PendingInvoicesModel> listPendingInvoices, long nisRad) {

        List<RequestLiquidacionEnchufateModel.RequestLiquidationInvoicesModel> lstRequestLiquidacion = new ArrayList<>();

        for (PendingInvoicesModel item : listPendingInvoices) {
            RequestLiquidacionEnchufateModel.RequestLiquidationInvoicesModel requestModel = new RequestLiquidacionEnchufateModel.RequestLiquidationInvoicesModel();
            requestModel.setDeuda(item.getDeuda());
            requestModel.setFechaEmision(item.getfFact());
            requestModel.setFechaVencimiento(item.getVencimiento());
            requestModel.setNumeroDoc(item.getRecibo());

            lstRequestLiquidacion.add(requestModel);
        }

        RequestLiquidacionEnchufateModel poRequest = new RequestLiquidacionEnchufateModel();
        poRequest.setNisRad(nisRad);
        poRequest.setDocumentos(lstRequestLiquidacion);

        GetLiquidacionEnchufateUseCase.Params loParams = GetLiquidacionEnchufateUseCase.Params.forValidate(goRequestLiquidationInvoicesModelMapper.transform(poRequest));
        this.goGetLiquidacionUseCase.execute(new LiquidacionObserver(), loParams);
    }

    /**
     * Observer generar liquidacion enchufate webservices
     */

    private final class LiquidacionObserver extends DefaultObserver<Liquidacion> {

        @Override
        public void onComplete() {
            GenerateLiquidacionEnchufatePresenter.this.hideViewLoadingGenerateLiqu();
        }

        @Override
        public void onError(Throwable poException) {
            GenerateLiquidacionEnchufatePresenter.this.hideViewLoadingGenerateLiqu();
            GenerateLiquidacionEnchufatePresenter.this.showErrorMessage(poException.getMessage());
            GenerateLiquidacionEnchufatePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(Liquidacion poData) {
            Log.v("GenerateLiquidacion", "OK");
            GenerateLiquidacionEnchufatePresenter.this.showDataSuccessToView(poData);
        }
    }

}
