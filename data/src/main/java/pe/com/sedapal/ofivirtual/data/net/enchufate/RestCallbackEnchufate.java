package pe.com.sedapal.ofivirtual.data.net.enchufate;

import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;

/**
 * Created by jsanez on 09/04/19.
 */

public interface RestCallbackEnchufate<T> {

    /**
     * Invoked for a received HTTP response. Response is validate.
     *
     * @param poData               Data to response evaluated
     * @author jsaenz
     * @version 1.0
     * @since 09/01/2017
     */
    void onResponse(T poData);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     *
     * @param poException Exception error {@link BaseException}
     * @author jsaenz
     * @version 1.0
     * @since 09/01/2017
     */
    void onError(BaseException poException);
}
