package pe.com.sedapal.ofivirtual.data.entity;

import androidx.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hernan Pareja on 4/01/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponseEntity<T> {

    @JsonProperty("bRESP")
    private T data;
    @Nullable
    @JsonProperty("cRESP_SP")
    private String msg;
    @JsonProperty("nRESP_SP")
    private int codResult;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCodResult() {
        return codResult;
    }

    public void setCodResult(int codResult) {
        this.codResult = codResult;
    }
}
