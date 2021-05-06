package pe.com.sedapal.ofivirtual.data.util;

import android.content.Context;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by sedapal on 7/03/17.
 */

public class SSLUtil {


    public static SSLContext getSSLConfig(Context context) throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] dummyTrustManager = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        // Creating an SSLSocketFactory that uses our TrustManager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, dummyTrustManager, new SecureRandom());
        return sslContext;
    }
}
