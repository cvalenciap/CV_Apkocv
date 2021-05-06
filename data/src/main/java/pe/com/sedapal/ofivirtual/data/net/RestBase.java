package pe.com.sedapal.ofivirtual.data.net;

import android.content.Context;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.com.sedapal.ofivirtual.data.BuildConfig;
import pe.com.sedapal.ofivirtual.data.R;
import pe.com.sedapal.ofivirtual.data.util.SSLUtil;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hernan Pareja on 5/01/2017.
 */
public class RestBase {

    Context ioContext;
    RestApi ioRestApi;

    /**
     * Constructs a {@link RestBase}.
     *
     * @param poContext {@link Context}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public RestBase(Context poContext) {
        this.ioContext = poContext;
        this.ioRestApi = createRestApi();
    }

    public Context getContext() {
        return ioContext;
    }

    public RestApi getRestApi() {
        return ioRestApi;
    }

    /**
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     *
     * @return Instance retrofit when invoke to diferents services {@link RestApi}
     * @author jsaenz
     * @version 1.0
     * @since 09/01/2017
     */
    private RestApi createRestApi() {
        if (ioRestApi == null) {
            String lsUrlServer = String.format("%s%s", getProtocol(), getHost());


            OkHttpClient.Builder loBuilder = new OkHttpClient.Builder();
            loBuilder.connectTimeout(getConnetTimeout(), TimeUnit.SECONDS);
            loBuilder.readTimeout(getReadTimeout(), TimeUnit.SECONDS);


//            Interceptor loInterceptor = new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request loOriginal = chain.request();
//                    String lsBody = ParseUtil.requestBodyToString(loOriginal.body());
//
//                    String lsImei = "Imei";
//                    String lsImeiValue = SecurityECCUtil.encrypt(CommonUtil.getImeiPhone(getContext()));//CommonUtil.getImeiPhone(getContext());
//                    String lsDevice = "Device";
//                    String lsDeviceValue = SecurityECCUtil.encrypt("AND");// "AND";
//                    // String lsRequestBodyEncrypt = SecurityVirgilUtil.encrypToString(lsBody); // lsBody;
//                    byte[] lsEncryptBody = SecurityECCUtil.encrypToString(lsBody);
//
//                    MediaType contentType = loOriginal.body().contentType();
//                    RequestBody requestBody = RequestBody.create(contentType, lsEncryptBody);
//
//                    Request loRequest = loOriginal.newBuilder()
//                            .header(lsImei, lsImeiValue)
//                            .header(lsDevice, lsDeviceValue)
//                            .method(loOriginal.method(), requestBody)
//                            .build();
//
//                    Response loResponse = chain.proceed(loRequest);
//                    if (loResponse.code() == 200) {
////                        byte[] lsResponseBodyEncrypt = ParseUtil.responseBodyToByte(loResponse.body());
//                        String lsResponseBodyEncrypt = ParseUtil.responseBodyToString(loResponse.body());
//                        String lsResponseBody = SecurityECCUtil.decrypt(lsResponseBodyEncrypt);
//                        MediaType loContentType = loResponse.body().contentType();
//                        ResponseBody responseBody = ResponseBody.create(loContentType, lsResponseBody);
//                        return loResponse.newBuilder().body(responseBody).build();
//                    }
//                    return loResponse;
//                }
//            };
//            loBuilder.addInterceptor(loInterceptor);

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
        return ioContext.getString(R.string.host);
    }

    protected String getProtocol() {
        return ioContext.getString(R.string.protocol);
    }

    protected int getConnetTimeout() {
        return ioContext.getResources().getInteger(R.integer.connect_timeout);
    }

    protected int getReadTimeout() {
        return ioContext.getResources().getInteger(R.integer.read_timeout);
    }

}
