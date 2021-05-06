package pe.com.sedapal.ofivirtual.presenter.user;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.domain.entity.SecretQuestion;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.user.GetListSecretQuestionUseCase;
import pe.com.sedapal.ofivirtual.model.SecretQuestionModel;
import pe.com.sedapal.ofivirtual.model.mapper.SecretQuestionModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.SecretQuestionView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 10/02/2017.
 */
public class SecretQuestionPresenter implements Presenter {

    private SecretQuestionView goSecretQuestionView;

    private final GetListSecretQuestionUseCase goGetListSecretQuestionUseCase;
    private final SecretQuestionModelMapper goSecretQuestionModelMapper;

    @Inject
    public SecretQuestionPresenter(GetListSecretQuestionUseCase poGetListSecretQuestionUseCase, SecretQuestionModelMapper poSecretQuestionModelMapper) {
        this.goGetListSecretQuestionUseCase = poGetListSecretQuestionUseCase;
        this.goSecretQuestionModelMapper = poSecretQuestionModelMapper;
    }

    public void setView(@NonNull SecretQuestionView poSecretQuestionView) {
        this.goSecretQuestionView = poSecretQuestionView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goSecretQuestionView = null;
    }

    public void obtainListSecretQuestion() {
        this.hideViewRetry();
        this.doObtain();
    }

    private void showViewLoading() {
        this.goSecretQuestionView.showLoading(this.goSecretQuestionView.context().getResources().getString(R.string.lbl_progressdialog_document_verif));
    }

    private void hideViewLoading() {
        this.goSecretQuestionView.hideLoading();
    }

    private void showViewRetry() {
        this.goSecretQuestionView.showRetry();
    }

    private void hideViewRetry() {
        this.goSecretQuestionView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goSecretQuestionView.showError(psErrorMessage);
    }

    private void showListSecretQuestionToView(List<SecretQuestion> poListSecretQuestion) {
        List<SecretQuestionModel> loSecretQuestionModel = goSecretQuestionModelMapper.transform(poListSecretQuestion);
        this.goSecretQuestionView.showSucessListSecretQuestion(loSecretQuestionModel);
    }

    /**
     * Execute sign in.
     *
     * @author jsaenz
     * @version 1.0
     * @since 15/02/2017
     */
    private void doObtain() {
       this.goGetListSecretQuestionUseCase.execute(new SecretQuestionObserver(),null);
    }

    private final class SecretQuestionObserver extends DefaultObserver<List<SecretQuestion>> {

        @Override
        public void onComplete() {
            SecretQuestionPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable poException) {
            SecretQuestionPresenter.this.hideViewLoading();
            SecretQuestionPresenter.this.showErrorMessage(poException.getMessage());
            SecretQuestionPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<SecretQuestion> poData) {
            SecretQuestionPresenter.this.showListSecretQuestionToView(poData);
        }
    }

}
