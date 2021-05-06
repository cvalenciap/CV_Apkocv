package pe.com.sedapal.ofivirtual.presenter.master;

import androidx.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.domain.entity.CardType;
import pe.com.sedapal.ofivirtual.domain.interactor.DefaultObserver;
import pe.com.sedapal.ofivirtual.domain.interactor.sedapal.GetListCardTypeUseCase;
import pe.com.sedapal.ofivirtual.model.CardTypeModel;
import pe.com.sedapal.ofivirtual.model.mapper.CardTypeModelMapper;
import pe.com.sedapal.ofivirtual.presenter.Presenter;
import pe.com.sedapal.ofivirtual.presenter.view.CardTypeView;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 * <p>
 * Created by jsaenz on 02/04/2019.
 */
public class CardTypePresenter implements Presenter {

    private CardTypeView goCardTypeView;

    private final GetListCardTypeUseCase goGetListCardTypeUseCase;
    private final CardTypeModelMapper goCardTypeModelMapper;

    @Inject
    public CardTypePresenter(GetListCardTypeUseCase poGetListCardTypeUseCase, CardTypeModelMapper poCardTypeModelMapper) {
        this.goGetListCardTypeUseCase = poGetListCardTypeUseCase;
        this.goCardTypeModelMapper = poCardTypeModelMapper;
    }

    public void setView(@NonNull CardTypeView poCardTypeView) {
        this.goCardTypeView = poCardTypeView;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.goGetListCardTypeUseCase.dispose();
        this.goCardTypeView = null;
    }

    public void initialize() {
        this.GetListCardType();
    }

    private void GetListCardType() {
        this.hideViewRetry();
        this.goGetData();
    }

    private void showViewRetry() {
        this.goCardTypeView.showRetry();
    }

    private void hideViewRetry() {
        this.goCardTypeView.hideRetry();
    }

    private void showErrorMessage(String psErrorMessage) {
        this.goCardTypeView.showError(psErrorMessage);
    }

    private void showLoginSuccessToView(List<CardType> poCardType) {
        List<CardTypeModel> loListCardTypeModel = goCardTypeModelMapper.transform(poCardType);
        this.goCardTypeView.showIdCardType(loListCardTypeModel);
    }

    private void goGetData() {
        this.goGetListCardTypeUseCase.execute(new CardTypeObserver(), null);
    }

    private final class CardTypeObserver extends DefaultObserver<List<CardType>> {

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable poException) {
            CardTypePresenter.this.showErrorMessage(poException.getMessage());
            CardTypePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<CardType> poData) {
            CardTypePresenter.this.showLoginSuccessToView(poData);
        }
    }
}
