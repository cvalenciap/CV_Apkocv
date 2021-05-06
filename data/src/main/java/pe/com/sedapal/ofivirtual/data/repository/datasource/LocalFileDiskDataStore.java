package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import pe.com.sedapal.ofivirtual.data.entity.RequestInvoicePdfEntity;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.net.RestBase;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;

public class LocalFileDiskDataStore extends RestBase implements FileDataStore {

    private static final String TAG = LocalFileDiskDataStore.class.getSimpleName();

    public LocalFileDiskDataStore(Context poContext) {
        super(poContext);
    }

    @Override
    public void getPdf(CallbackDataStore<String> poCallback, String psFileName) {
        try {
            LogUtil.i(TAG, "INICIO - getPdf");
            //String lsCompletePath = getCompletePath(psFileName);
            String lsCompletePath = getCompletePath().getAbsolutePath();
            if (checkPdfExists(lsCompletePath)) {
                LogUtil.i(TAG, "FIN - getPdf: onResponse");
                poCallback.onSuccess(lsCompletePath);
            } else {
                LogUtil.i(TAG, "FIN - getPdf: onResponse");
                poCallback.onSuccess(null);
            }
        } catch (Exception poException) {
            LogUtil.i(TAG, "FIN - login: onError");
            poCallback.onError(poException);
        }
    }

    @Override
    public void downloadPdf(CallbackDataStore<String> poCallback, String token, RequestInvoicePdfEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void savePdf(CallbackDataStore<String> poCallback, String poPdfFile, String psFileName) {
        try {

            File loFile = new File(getCompletePath(),psFileName);
            FileOutputStream loOutputStream = null;

            try {
                byte[] pdfAsBytes = new byte[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                    pdfAsBytes = Base64.decode(poPdfFile, 0);
                }

                loOutputStream = new FileOutputStream(loFile, false);
                loOutputStream.write(pdfAsBytes);
                loOutputStream.flush();
                loOutputStream.close();

                LogUtil.i(TAG, "FIN - savePdf: onResponse");
                poCallback.onSuccess(loFile.getAbsolutePath());
            } catch (IOException poException) {
                LogUtil.i(TAG, "FIN - savePdf: onError");
                poCallback.onError(poException);
            } finally {
                if (loOutputStream != null) {
                    loOutputStream.close();
                }
            }
        } catch (IOException poException) {
            LogUtil.i(TAG, "FIN - savePdf: onError");
            poCallback.onError(poException);
        }
    }

    private boolean checkPdfExists(String psCompletePath) {
        File loFile = new File(psCompletePath);
        return loFile.exists();
    }

    @SuppressLint("NewApi")
    private File getCompletePath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    /*private String getCompletePath(String psFileName) {
        //return getContext().getExternalFilesDir(null) + File.separator + psFileName;

        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }*/

    public String cutUrlToFileName(String psUrl) {
        int liPosition = psUrl.lastIndexOf("/");
        return psUrl.substring(liPosition + 1);
    }

}
