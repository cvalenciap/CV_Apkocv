package pe.com.sedapal.ofivirtual.data.net;

/**
 * Created by jsaenz on 16/01/2017.
 * <p>
 * Interface that represents callback to DataStore
 */
public interface CallbackDataStore<T> {

    void onSuccess(T poData);

    void onError(Throwable poException);

}
