package pe.com.sedapal.ofivirtual.data.exception;

import android.text.TextUtils;

/**
 * Created by jsaenz on 5/01/2017.
 */

public class BaseException extends Exception {


    public final static String ERROR_GENERAL = "99999";
    public final static String ERROR_DATA_RESPONSE = "99998";
    public final static String ERROR_FAILURE = "99997";
    public final static String ERROR_CONNECTION = "99996";
    public final static String ERROR_SESSION_USER = "99995";
    public final static String ERROR_SESSION_SERVICE = "60000";
    public final static String ERROR_TOKEN_NOT_EXIST_SERVICE = "60001";

    public final static int ERROR_SESSION_TOKEN_1 = 401;
    public final static int ERROR_SESSION_TOKEN_2 = 403;
    public final static int ERROR_SESSION_TOKEN_3 = 0;
    public final static String ERROR_DATA_SESSION_TOKEN = "40000";

    private String gsCodeError;
    private String gsMessage;
    private String gsMessageError;

    public BaseException(String psCodeError, String psMessage, String psMessageError, Throwable poCause) {
        super(poCause);
        this.gsCodeError = psCodeError;
        this.gsMessage = psMessage;
        this.gsMessageError = psMessageError;
    }

    public BaseException(String psCodeError, String psMessage, String psMessageError) {
        super();
        this.gsCodeError = psCodeError;
        this.gsMessage = psMessage;
        this.gsMessageError = psMessageError;
    }

    public BaseException(String psCodeError, String psMessage) {
        super();
        this.gsCodeError = psCodeError;
        this.gsMessage = psMessage;
    }

    @Override
    public String getMessage() {
        return !TextUtils.isEmpty(gsMessage) ? gsMessage : gsMessageError;
    }

    public String getMessageError() {
        return gsMessageError;
    }

    public String getCodeError() {
        return gsCodeError;
    }
}
