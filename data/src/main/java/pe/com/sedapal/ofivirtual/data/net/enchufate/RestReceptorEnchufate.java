package pe.com.sedapal.ofivirtual.data.net.enchufate;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.net.RestCallback;
import pe.com.sedapal.ofivirtual.data.util.CommonUtil;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jsaenz on 09/04/19
 */
public class RestReceptorEnchufate<T> {

    private static final String TAG = RestReceptorEnchufate.class.getSimpleName();

    private final Context goContext;

    /**
     * Constructs a {@link RestReceptorEnchufate}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 09/04/19
     */
    public RestReceptorEnchufate(Context poContext) {
        this.goContext = poContext;
    }

    /**
     * Invoke el method enqueue to retrofit, validate is connection to internet and response
     * to callback {@link RestCallback} is onResponse or onError
     *
     * @param poCall         {@link Call} of {@link BaseResponseEntity}.
     * @param poRestCallback {@link RestCallback}.
     * @author jsaenz
     * @version 1.0
     * @since 09/04/19
     */
    public void invoke(Call<T> poCall, final RestCallbackEnchufate<T> poRestCallback) {

        if (CommonUtil.isOnline(this.goContext)) {
            poCall.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> poCall, Response<T> poResponse) {

                    if (poResponse.body() != null) {
                        if (poResponse.isSuccessful()) {
                            poRestCallback.onResponse(poResponse.body());
                        } else {
                            String lsMessageError = String.format("invoke | onResponse | errorSuccess \n%s",
                                    poResponse.errorBody());
                            LogUtil.e(TAG, lsMessageError);
                            poRestCallback.onError(new BaseException(
                                    "0",
                                    !TextUtils.isEmpty(poResponse.errorBody().toString()) ? poResponse.errorBody().toString() : ""));


                        }
                    } else {
                        try {
                            if (poResponse.errorBody() != null) {
                                String lsMessageError = String.format("invoke | onResponse | errorBody \n%s",
                                        poResponse.errorBody().string());
                                LogUtil.e(TAG, lsMessageError);
                                /**
                                 * Validate if token session is valid
                                 */
                                if(poResponse.code() == BaseException.ERROR_SESSION_TOKEN_1 ||
                                        poResponse.code() == BaseException.ERROR_SESSION_TOKEN_2 ||
                                        poResponse.code() == BaseException.ERROR_SESSION_TOKEN_3){
                                    poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_SESSION_TOKEN,
                                            goContext.getString(R.string.rest_message_error_session_token)));
                                }
                            } else {
                                LogUtil.e(TAG, "Error parseando la data");
                            }
                        } catch (IOException poIOException) {
                            LogUtil.e(TAG, "invoke | onResponse | errorBody", poIOException);
                        }
                        poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_RESPONSE,
                                goContext.getString(R.string.rest_message_error_data_response)));

                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    String lsMessageError = goContext.getString(R.string.rest_message_error_failure);
                    LogUtil.e(TAG, "invoke | onFailure", t);
                    poRestCallback.onError(new BaseException(BaseException.ERROR_FAILURE, lsMessageError));
                }
            });
        } else {
            String lsMessageError = goContext.getString(R.string.rest_message_error_connection);
            LogUtil.e(TAG, "invoke", new Throwable(lsMessageError));
            poRestCallback.onError(new BaseException(BaseException.ERROR_CONNECTION, lsMessageError));
        }
    }
}
