package pe.com.sedapal.ofivirtual.data.util;

import android.text.TextUtils;
import android.util.Log;

import pe.com.sedapal.ofivirtual.data.BuildConfig;

/**
 * Created by Hernan Pareja on 5/01/2017.
 */

public final class LogUtil {

    private static final String TAG = LogUtil.class.getSimpleName();
    private static boolean showLog = false;

    public static void v(String psTAG, String psMessage) {
        if ((BuildConfig.DEBUG || showLog) && !TextUtils.isEmpty(psMessage)) {
            if (showLog) {
                psTAG = "Sedapal-" + psTAG;
            }
            Log.v(psTAG, psMessage);
        }
    }

    public static void d(String psTAG, String psMessage) {
        if ((BuildConfig.DEBUG || showLog) && !TextUtils.isEmpty(psMessage)) {
            if (showLog) {
                psTAG = "Sedapal-" + psTAG;
            }
            Log.d(psTAG, psMessage);
        }
    }

    public static void i(String psTAG, String psMessage) {
        if ((BuildConfig.DEBUG || showLog) && !TextUtils.isEmpty(psMessage)) {
            if (showLog) {
                psTAG = "Sedapal-" + psTAG;
            }
            Log.i(psTAG, psMessage);
        }
    }

    public static void w(String psTAG, String psMessage) {
        if ((BuildConfig.DEBUG || showLog) && !TextUtils.isEmpty(psMessage)) {
            if (showLog) {
                psTAG = "Sedapal-" + psTAG;
            }
            Log.w(psTAG, psMessage);
        }
    }

    public static void e(String psTAG, String psMessage) {
        if ((BuildConfig.DEBUG || showLog) && !TextUtils.isEmpty(psMessage)) {
            if (showLog) {
                psTAG = "Sedapal-" + psTAG;
            }
            Log.e(psTAG, psMessage);
        }
    }

    public static void e(String psTAG, String psMessage, Throwable poException) {
        if ((BuildConfig.DEBUG || showLog) && !TextUtils.isEmpty(psMessage)) {
            if (showLog) {
                psTAG = "Sedapal-" + psTAG;
            }
            Log.e(psTAG, psMessage, poException);
        }
    }
}
