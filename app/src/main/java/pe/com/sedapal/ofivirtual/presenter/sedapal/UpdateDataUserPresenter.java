package pe.com.sedapal.ofivirtual.presenter.sedapal;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.UpdateDataUserUseCase;
import pe.com.sedapal.ofivirtual.model.UpdateDataUserModel;
import pe.com.sedapal.ofivirtual.model.mapper.UpdateDataUserMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.UpdateDataUserView;
import pe.com.sedapal.ofivirtual.util.Constants;


public class UpdateDataUserPresenter implements Presenter {
    private UpdateDataUserView goUpdateDataUserView;
    private final UpdateDataUserUseCase goUpdateDataUserUseCase;
    private final UpdateDataUserMapper goUpdateDataUserMapper;

    @Inject
    public UpdateDataUserPresenter(UpdateDataUserUseCase poUpdateDataUserUseCase, UpdateDataUserMapper poUpdateDataUserMapper) {
        this.goUpdateDataUserUseCase = poUpdateDataUserUseCase;
        this.goUpdateDataUserMapper = poUpdateDataUserMapper;
    }

    public void setView(@NonNull UpdateDataUserView goUpdateDataUserView) {
        this.goUpdateDataUserView = goUpdateDataUserView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.goUpdateDataUserView = null;
        this.goUpdateDataUserUseCase.dispose();
    }

    public void initialize(long idCliente, int tipoSolicitante, int tipoDocum, long nroDoc, String apellido1, String apellido2, String nombres, String genero, String distrito, String direccion, String fechaNac, long telefono1, long telefono2, String aceptaNotifica) {
        this.hideViewRetry();
        this.showViewLoading();

        UpdateDataUserModel poUpdateDataUserModel = new UpdateDataUserModel(idCliente, tipoSolicitante, tipoDocum, nroDoc, apellido1, apellido2, nombres, genero, distrito, direccion, fechaNac, telefono1, telefono2, aceptaNotifica);
        this.doUpdateData(poUpdateDataUserModel);
    }

    private void showViewLoading() {
        this.goUpdateDataUserView.showLoading(this.goUpdateDataUserView.context().getString(R.string.lbl_progressdialog_actualizando_usuario));
    }

    private void hideViewLoading() {
        this.goUpdateDataUserView.hideLoading();
    }

    private void showViewRetry() {
        this.goUpdateDataUserView.showRetry();
    }

    private void hideViewRetry() {
        this.goUpdateDataUserView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goUpdateDataUserView.showError(psErrorMessage);
    }

    private void showUpdateDataUserSuccessToView(String poMessage) {
        this.goUpdateDataUserView.showSucessUpdateDataUser(poMessage);
    }

    private void doUpdateData(UpdateDataUserModel poUpdateDataUserModel) {
        UpdateDataUserUseCase.Params loParams = UpdateDataUserUseCase.Params.forUpdate(goUpdateDataUserMapper.transform(poUpdateDataUserModel));
        this.goUpdateDataUserUseCase.execute(new UpdateDataUserOserver(), loParams);
    }

    private final class UpdateDataUserOserver extends DefaultObserver<String> {

        @Override
        public void onComplete() {
            UpdateDataUserPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            UpdateDataUserPresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goUpdateDataUserView.onSessionExpired(poException.getMessage());
            }else {
                UpdateDataUserPresenter.this.showErrorMessage(poException.getMessage());
                UpdateDataUserPresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(String poData) {
            UpdateDataUserPresenter.this.showUpdateDataUserSuccessToView(poData);
        }
    }
}
