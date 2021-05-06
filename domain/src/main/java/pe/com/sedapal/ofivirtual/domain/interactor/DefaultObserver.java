
package pe.com.sedapal.ofivirtual.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default error handling.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T poData) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable poException) {
        // no-op by default.
    }
}
