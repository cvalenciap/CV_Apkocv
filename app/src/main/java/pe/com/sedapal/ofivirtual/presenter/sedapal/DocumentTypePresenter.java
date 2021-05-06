package pe.com.sedapal.ofivirtual.presenter.sedapal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.domain.entity.ValidDocumentType;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetListDocumentTypeUseCase;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.ValidDocumentTypeUseCase;
import pe.com.sedapal.ofivirtual.model.DocumentTypeModel;
import pe.com.sedapal.ofivirtual.model.ValidDocumentTypeModel;
import pe.com.sedapal.ofivirtual.model.mapper.DocumentTypeModelMapper;
import pe.com.sedapal.ofivirtual.model.mapper.ValidDocumentTypeModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.DocumentTypeView;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class DocumentTypePresenter implements Presenter {

    private DocumentTypeView goDocumentTypeView;

    private final GetListDocumentTypeUseCase goGetListDocumentTypeUseCase;
    private final ValidDocumentTypeUseCase goValidDocumentTypeUseCase;
    private final DocumentTypeModelMapper goDocumentTypeModelMapper;
    private final ValidDocumentTypeModelMapper goValidDocumentTypeModelMapper;

    @Inject
    public DocumentTypePresenter(GetListDocumentTypeUseCase poGetListDocumentTypeUseCase,ValidDocumentTypeUseCase goValidDocumentTypeUseCase, DocumentTypeModelMapper poDocumentTypeModelMapper, ValidDocumentTypeModelMapper poValidDocumentTypeModelMapper) {
        this.goGetListDocumentTypeUseCase = poGetListDocumentTypeUseCase;
        this.goValidDocumentTypeUseCase = goValidDocumentTypeUseCase;
        this.goDocumentTypeModelMapper = poDocumentTypeModelMapper;
        this.goValidDocumentTypeModelMapper = poValidDocumentTypeModelMapper;
    }

    public void setView(@NonNull DocumentTypeView poSuministroView) {
        this.goDocumentTypeView = poSuministroView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goDocumentTypeView = null;
        this.goGetListDocumentTypeUseCase.dispose();
        this.goValidDocumentTypeUseCase.dispose();
    }

    public void obtainListDocumentType() {
        this.hideViewRetry();
        this.doObtain();
    }

    public void validDocumentType(){
        this.showViewLoading();
    }

    public void validateTypeDocument(int psNroSuministro,int psTipDoc, String psNumDoc){
        this.hideViewRetry();
        this.showViewLoading();
        this.doValidate(psNroSuministro,psTipDoc,psNumDoc);
    }

    private void showViewLoading() {
        //this.goDocumentTypeView.showLoading(this.goDocumentTypeView.context().getResources().getString(R.string.lbl_progressdialog_document_verif));
        this.goDocumentTypeView.showLoadingDocumentType();
    }

    private void hideViewLoading() {
        this.goDocumentTypeView.hideLoadingDocumentType();
    }

    private void showViewRetry() {
        this.goDocumentTypeView.showRetry();
    }

    private void hideViewRetry() {
        this.goDocumentTypeView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goDocumentTypeView.showError(psErrorMessage);
    }

    private void showSuministroSuccessToView(List<DocumentType> poListDocumentType) {
        List<DocumentTypeModel> loDocumentTypeModel = goDocumentTypeModelMapper.transform(poListDocumentType);
        if(loDocumentTypeModel == null){
            loDocumentTypeModel = new ArrayList<>();
        }
        this.goDocumentTypeView.showSucessListDocumentType(loDocumentTypeModel);
    }

    private void showTypeDocumentValidateToView(ValidDocumentType poValidDocumentType) {
        ValidDocumentTypeModel loValidDocumentTypeModel = goValidDocumentTypeModelMapper.transform(poValidDocumentType);
        this.goDocumentTypeView.showSucessValidateDocumentType(loValidDocumentTypeModel);
    }

    /**
     * Obtain document type from database.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain() {
       this.goGetListDocumentTypeUseCase.execute(new DocumentTypeObserver(),null);
    }

    private final class DocumentTypeObserver extends DefaultObserver<List<DocumentType>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            DocumentTypePresenter.this.showErrorMessage(poException.getMessage());
            DocumentTypePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<DocumentType> poData) {
            DocumentTypePresenter.this.showSuministroSuccessToView(poData);
        }
    }

    /**
     *  clb nuevo para metro nro de suministro
     * @param psTipoDoc
     * @param psNroDoc
     */
    private void doValidate(int psNroSuministro,int psTipoDoc, String psNroDoc) {
        ValidDocumentTypeUseCase.Params loParams = ValidDocumentTypeUseCase.Params.forValidate(psNroSuministro,psTipoDoc, psNroDoc);
        this.goValidDocumentTypeUseCase.execute(new ValidDocumentTypeObserver(),loParams);
    }

    private final class ValidDocumentTypeObserver extends DefaultObserver<ValidDocumentType> {

        @Override
        public void onComplete() {
            DocumentTypePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            DocumentTypePresenter.this.hideViewLoading();
            BaseException loBaseException =(BaseException)poException;
            if(loBaseException.getCodeError().equalsIgnoreCase(Constants.SESSION.ERROR_DATA_SESSION_TOKEN)){
                goDocumentTypeView.onSessionExpired(poException.getMessage());
            }else {
                DocumentTypePresenter.this.showErrorMessage(poException.getMessage());
                DocumentTypePresenter.this.showViewRetry();
            }
        }

        @Override
        public void onNext(ValidDocumentType poData) {
            DocumentTypePresenter.this.showTypeDocumentValidateToView(poData);
        }
    }

}
