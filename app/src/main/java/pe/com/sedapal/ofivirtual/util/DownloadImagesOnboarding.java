package pe.com.sedapal.ofivirtual.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import pe.com.sedapal.ofivirtual.model.ImagenesOnboardingModel;
import pe.com.sedapal.ofivirtual.ui.activity.BaseActivity;

public class DownloadImagesOnboarding {
    List<ImagenesOnboardingModel> listImagenesOnboardingModel;
    BaseActivity contextBaseActivity;
    CallbackDownload callbackDownload;

    public interface CallbackDownload {
        void onDownload();
    }

    public DownloadImagesOnboarding(BaseActivity ctx, List<ImagenesOnboardingModel> plListImagenesOnboardingModel, CallbackDownload callbackDownload) {
        this.contextBaseActivity = ctx;
        this.listImagenesOnboardingModel = plListImagenesOnboardingModel;
        this.callbackDownload = callbackDownload;

        download_IMG_ONBOARDING_1();
    }

    AsyncTask mMyTask;
    URL url;

    private void download_IMG_ONBOARDING_1() {
        mMyTask = new DownloadTask_IMG_ONBOARDING_1().execute(stringToURL_IMG_ONBOARDING_1());
    }

    private class DownloadTask_IMG_ONBOARDING_1 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_IMG_ONBOARDING_1 = result;
                download_IMG_ONBOARDING_2();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("IMG_ONBOARDING_1", "Ocurrión un error");
                download_IMG_ONBOARDING_2();
            }
        }
    }

    protected URL stringToURL_IMG_ONBOARDING_1() {
        try {
            url = new URL(listImagenesOnboardingModel.get(0).getFondoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * download_IMG_ONBOARDING_2
     */


    private void download_IMG_ONBOARDING_2() {
        mMyTask = new DownloadTask_IMG_ONBOARDING_2().execute(stringToURL_IMG_ONBOARDING_2());
    }

    private class DownloadTask_IMG_ONBOARDING_2 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_IMG_ONBOARDING_2 = result;
                download_IMG_ONBOARDING_3();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("IMG_ONBOARDING_2", "Ocurrión un error");
                download_IMG_ONBOARDING_3();
            }
        }
    }

    protected URL stringToURL_IMG_ONBOARDING_2() {
        try {
            url = new URL(listImagenesOnboardingModel.get(1).getFondoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * download_IMG_ONBOARDING_3
     */


    private void download_IMG_ONBOARDING_3() {
        mMyTask = new DownloadTask_IMG_ONBOARDING_3().execute(stringToURL_IMG_ONBOARDING_3());
    }

    private class DownloadTask_IMG_ONBOARDING_3 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_IMG_ONBOARDING_3 = result;
                download_IMG_ONBOARDING_4();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("IMG_ONBOARDING_3", "Ocurrión un error");
                download_IMG_ONBOARDING_4();
            }
        }
    }

    protected URL stringToURL_IMG_ONBOARDING_3() {
        try {
            url = new URL(listImagenesOnboardingModel.get(2).getFondoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * download_IMG_ONBOARDING_4
     */

    private void download_IMG_ONBOARDING_4() {
        mMyTask = new DownloadTask_IMG_ONBOARDING_4().execute(stringToURL_IMG_ONBOARDING_4());
    }

    private class DownloadTask_IMG_ONBOARDING_4 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_IMG_ONBOARDING_4 = result;
                download_BITMAP_ICON_ONBOARDING_1();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("IMG_ONBOARDING_4", "Ocurrión un error");
                download_BITMAP_ICON_ONBOARDING_1();
            }
        }
    }

    protected URL stringToURL_IMG_ONBOARDING_4() {
        try {
            url = new URL(listImagenesOnboardingModel.get(3).getFondoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * download_BITMAP_ICON_ONBOARDING_1
     */

    private void download_BITMAP_ICON_ONBOARDING_1() {
        mMyTask = new DownloadTask_BITMAP_ICON_ONBOARDING_1().execute(stringToURL_BITMAP_ICON_ONBOARDING_1());
    }

    private class DownloadTask_BITMAP_ICON_ONBOARDING_1 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_ICON_ONBOARDING_1 = result;
                download_BITMAP_ICON_ONBOARDING_2();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("BI_ICON_ONBOARDING_1", "Ocurrión un error");
                download_BITMAP_ICON_ONBOARDING_2();
            }
        }
    }

    protected URL stringToURL_BITMAP_ICON_ONBOARDING_1() {
        try {
            url = new URL(listImagenesOnboardingModel.get(0).getIconoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * download_BITMAP_ICON_ONBOARDING_2
     */

    private void download_BITMAP_ICON_ONBOARDING_2() {
        mMyTask = new DownloadTask_BITMAP_ICON_ONBOARDING_2().execute(stringToURL_BITMAP_ICON_ONBOARDING_2());
    }

    private class DownloadTask_BITMAP_ICON_ONBOARDING_2 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_ICON_ONBOARDING_2 = result;
                download_BITMAP_ICON_ONBOARDING_3();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("BI_ICON_ONBOARDING_2", "Ocurrión un error");
                download_BITMAP_ICON_ONBOARDING_3();
            }
        }
    }

    protected URL stringToURL_BITMAP_ICON_ONBOARDING_2() {
        try {
            url = new URL(listImagenesOnboardingModel.get(1).getIconoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * download_BITMAP_ICON_ONBOARDING_3
     */

    private void download_BITMAP_ICON_ONBOARDING_3() {
        mMyTask = new DownloadTask_BITMAP_ICON_ONBOARDING_3().execute(stringToURL_BITMAP_ICON_ONBOARDING_3());
    }

    private class DownloadTask_BITMAP_ICON_ONBOARDING_3 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_ICON_ONBOARDING_3 = result;
                download_BITMAP_ICON_ONBOARDING_4();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("BI_ICON_ONBOARDING_3", "Ocurrión un error");
                download_BITMAP_ICON_ONBOARDING_4();
            }
        }
    }

    protected URL stringToURL_BITMAP_ICON_ONBOARDING_3() {
        try {
            url = new URL(listImagenesOnboardingModel.get(2).getIconoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * download_BITMAP_ICON_ONBOARDING_4
     */

    private void download_BITMAP_ICON_ONBOARDING_4() {
        mMyTask = new DownloadTask_BITMAP_ICON_ONBOARDING_4().execute(stringToURL_BITMAP_ICON_ONBOARDING_4());
    }

    private class DownloadTask_BITMAP_ICON_ONBOARDING_4 extends AsyncTask<URL, Void, Bitmap> {
        protected void onPreExecute() {

        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
//            mProgressDialog.dismiss();
            if (result != null) {
                contextBaseActivity.BITMAP_ICON_ONBOARDING_4 = result;
                callbackDownload.onDownload();
            } else {
                // Notify user that an error occurred while downloading image
                Log.v("BI_ICON_ONBOARDING_3", "Ocurrión un error");
                callbackDownload.onDownload();
            }
        }
    }

    protected URL stringToURL_BITMAP_ICON_ONBOARDING_4() {
        try {
            url = new URL(listImagenesOnboardingModel.get(3).getIconoOnboarding());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
