package pe.com.sedapal.ofivirtual.presenter.master;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.ImagenesOnboarding;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.master.GetImagenesOnboardingUseCase;
import pe.com.sedapal.ofivirtual.model.ImagenesOnboardingModel;
import pe.com.sedapal.ofivirtual.model.mapper.ImagenesOnboardingModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.ImagenesOnboardingView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 02/04/2019.
 */
public class ImagenesOnboardingPresenter implements Presenter {

    private ImagenesOnboardingView goImagenesOnboardingView;

    private final GetImagenesOnboardingUseCase goGetImagenesOnboardingUseCase;
    private final ImagenesOnboardingModelMapper goImagenesOnboardingModelMapper;

    @Inject
    public ImagenesOnboardingPresenter(GetImagenesOnboardingUseCase poGetImagenesOnboardingUseCase, ImagenesOnboardingModelMapper poImagenesOnboardingModelMapper) {
        this.goGetImagenesOnboardingUseCase = poGetImagenesOnboardingUseCase;
        this.goImagenesOnboardingModelMapper = poImagenesOnboardingModelMapper;
    }

    public void setView(@NonNull ImagenesOnboardingView poImagenesOnboardingView) {
        this.goImagenesOnboardingView = poImagenesOnboardingView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetImagenesOnboardingUseCase.dispose();
        this.goImagenesOnboardingView = null;
    }

    public void initialize() {
        this.GetListImagenesOnboarding();
    }

    private void GetListImagenesOnboarding() {
        this.hideViewRetry();
        this.goGetData();
    }

    private void showViewRetry() {
        this.goImagenesOnboardingView.showRetry();
    }

    private void hideViewRetry() {
        this.goImagenesOnboardingView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goImagenesOnboardingView.showError(psErrorMessage);
    }

    private void showLoginSuccessToView(List<ImagenesOnboarding> poImagenesOnboarding) {
        List<ImagenesOnboardingModel> loListImagenesOnboardingModel = goImagenesOnboardingModelMapper.transform(poImagenesOnboarding);
        this.goImagenesOnboardingView.showImagenesOnboarding(loListImagenesOnboardingModel);
    }

    private void goGetData() {
        this.goGetImagenesOnboardingUseCase.execute(new ImagenesOnboardingObserver(), null);
    }

    private final class ImagenesOnboardingObserver extends DefaultObserver<List<ImagenesOnboarding>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            ImagenesOnboardingPresenter.this.showErrorMessage(poException.getMessage());
            ImagenesOnboardingPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ImagenesOnboarding> poData) {
            ImagenesOnboardingPresenter.this.showLoginSuccessToView(poData);
        }
    }
}
