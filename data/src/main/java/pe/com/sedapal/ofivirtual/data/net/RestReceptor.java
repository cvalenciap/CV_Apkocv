package pe.com.sedapal.ofivirtual.data.net;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;

import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.entity.BaseResponseEntity;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.util.CommonUtil;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jsaenz on 9/01/17.
 */
public class RestReceptor<T> {

    private static final String TAG = RestReceptor.class.getSimpleName();

    private final Context goContext;

    /**
     * Constructs a {@link RestReceptor}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public RestReceptor(Context poContext) {
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
     * @since 10/01/2017
     */
    public void invoke(Call<BaseResponseEntity<T>> poCall, final RestCallback<T> poRestCallback) {

        if (CommonUtil.isOnline(this.goContext)) {
            poCall.enqueue(new Callback<BaseResponseEntity<T>>() {
                @Override
                public void onResponse(Call<BaseResponseEntity<T>> poCall, Response<BaseResponseEntity<T>> poResponse) {

                    if (poResponse.body() != null) {
                        if (poResponse.body().getCodResult() == 1 || poResponse.body().getCodResult() == 2) {
                            poRestCallback.onResponse(poResponse.body().getData(), poResponse.body());
                        } else {
                            String lsMessageError = String.format("invoke | onResponse | errorSuccess \n%s",
                                    poResponse.body().getMsg());
                            LogUtil.e(TAG, lsMessageError);
                            poRestCallback.onError(new BaseException(
                                    poResponse.body().getMsg(),
                                    !TextUtils.isEmpty(poResponse.body().getMsg()) ? poResponse.body().getMsg() : ""));
                        }
                    } else {

                        /**
                         * Validate if token session is valid
                         */

                        try {
                            if (poResponse.errorBody() != null) {
                                String lsMessageError = String.format("invoke | onResponse | errorBody \n%s",
                                        poResponse.errorBody().string());
                                LogUtil.e(TAG, lsMessageError);
                                if (poResponse.code() == BaseException.ERROR_SESSION_TOKEN_1 ||
                                        poResponse.code() == BaseException.ERROR_SESSION_TOKEN_2 ||
                                        poResponse.code() == BaseException.ERROR_SESSION_TOKEN_3) {

                                    poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_SESSION_TOKEN,
                                            goContext.getString(R.string.rest_message_error_session_token)));
                                }else {
                                    poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_RESPONSE,
                                            goContext.getString(R.string.rest_message_error_data_response)));
                                }
                            } else {
                                LogUtil.e(TAG, "Error parseando la data");
                                poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_RESPONSE,
                                        goContext.getString(R.string.rest_message_error_data_response)));
                            }
                        } catch (IOException poIOException) {
                            LogUtil.e(TAG, "invoke | onResponse | errorBody", poIOException);
                            poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_RESPONSE,
                                    goContext.getString(R.string.rest_message_error_data_response)));
                        }

                        //if (poResponse.code() == BaseException.ERROR_SESSION_TOKEN_1 ||
                        //        poResponse.code() == BaseException.ERROR_SESSION_TOKEN_2 ||
                        //        poResponse.code() == BaseException.ERROR_SESSION_TOKEN_3) {
//
                        //    poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_SESSION_TOKEN,
                        //            goContext.getString(R.string.rest_message_error_session_token)));
                        //}
//
                        //try {
                        //        if (poResponse.errorBody() != null) {
                        //            String lsMessageError = String.format("invoke | onResponse | errorBody \n%s",
                        //                    poResponse.errorBody().string());
                        //            LogUtil.e(TAG, lsMessageError);
                        //        } else {
                        //            LogUtil.e(TAG, "Error parseando la data");
                        //        }
                        //    } catch (IOException poIOException) {
                        //        LogUtil.e(TAG, "invoke | onResponse | errorBody", poIOException);
                        //    }
                        //    poRestCallback.onError(new BaseException(BaseException.ERROR_DATA_RESPONSE,
                        //            goContext.getString(R.string.rest_message_error_data_response)));

                    }
                }

                @Override
                public void onFailure(Call<BaseResponseEntity<T>> call, Throwable t) {
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
