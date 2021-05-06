package pe.com.sedapal.ofivirtual.data.util;

import com.fernandocejas.arrow.strings.Charsets;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.modelmapper.ModelMapper;

import java.io.IOException;

/**
 * Created by jsaenz on 26/01/2017.
 */

public class ParseUtil {

    public static ModelMapper modelMapper;

    public static <D> D parseObject(Object origen, Class<D> destinationType) {

        if (modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        return modelMapper.map(origen, destinationType);
    }

    public static String requestBodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String responseBodyToString(final ResponseBody responseBody) {
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            return buffer.clone().readString(Charsets.UTF_8).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] responseBodyToByte(final ResponseBody responseBody) {
        BufferedSource source = responseBody.source();
        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            return buffer.clone().readByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
