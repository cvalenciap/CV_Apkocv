package pe.com.sedapal.ofivirtual.data.net.enchufate;

import android.content.Context;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.com.sedapal.ofivirtual.data.BuildConfig;
import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.net.RestApi;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.util.SSLUtil;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by jsaenz on 09/04/2019
 */
public class RestBaseEnchufate {

    Context ioContext;
    RestApi ioRestApi;
    String goBaseUriEnchufate;
    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public RestBaseEnchufate(Context poContext) {
        this.ioContext = poContext;
    }

    public Context getContext() {
        return ioContext;
    }

    public RestApi getRestApi(String poBaseUriEnchufate) {
        this.goBaseUriEnchufate = poBaseUriEnchufate;
        this.ioRestApi = createRestApi();
        return ioRestApi;
    }

    /**
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     *
     * @return Instance retrofit when invoke to diferents services {@link RestApi}
     * @author jsaenz
     * @version 1.0
     * @since 09/04/19
     */
    private RestApi createRestApi() {
        if (ioRestApi == null) {
            String lsUrlServer = String.format("%s", getHost());


            OkHttpClient.Builder loBuilder = new OkHttpClient.Builder();
            loBuilder.connectTimeout(getConnetTimeout(), TimeUnit.SECONDS);
            loBuilder.readTimeout(getReadTimeout(), TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loLogging = new HttpLoggingInterceptor();
                loLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
                loBuilder.addInterceptor(loLogging);


                try {
                    final SSLSocketFactory sslSocketFactory = SSLUtil.getSSLConfig(getContext()).getSocketFactory();
                    loBuilder.sslSocketFactory(sslSocketFactory);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                }

                loBuilder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
            }
            OkHttpClient loClient = loBuilder.build();


            ObjectMapper loObjectMapper = new ObjectMapper()
                    .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
//                    .disable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
            Retrofit loRetrofit = new Retrofit.Builder()
                    .baseUrl(lsUrlServer)
                    .client(loClient)
                    .addConverterFactory(JacksonConverterFactory.create(loObjectMapper))
                    .build();
            ioRestApi = loRetrofit.create(RestApi.class);
        }
        return ioRestApi;
    }

    protected String getHost() {
        return goBaseUriEnchufate;
    }

    protected int getConnetTimeout() {
        return ioContext.getResources().getInteger(R.integer.connect_timeout);
    }

    protected int getReadTimeout() {
        return ioContext.getResources().getInteger(R.integer.read_timeout);
    }
}
