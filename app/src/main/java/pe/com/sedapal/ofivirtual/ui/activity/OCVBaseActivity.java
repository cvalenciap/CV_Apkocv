package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Intent;
import android.view.MotionEvent;

import java.util.Calendar;
import java.util.Date;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.exception.BaseException;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.util.Constants;

/**
 * Created by jsaenz on 19/03/2019
 */

public class OCVBaseActivity extends BaseActivity {

    private static final String TAG = "BancaMovilBaseActivity";
    private static final String TAG_SESSION = "SESSION -:";
    private static Date goLastSessionDate;
    //private static ListAdapter.OnItemClickListener glClickItemList;


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        validateFrontSession();
        return super.dispatchTouchEvent(event);
    }

    public static void setLastSessionDate(Date poLastSessionDate) {
        goLastSessionDate = poLastSessionDate;
    }

    private void validateFrontSession() {
        //if (goLastSessionDate == null || getLoginModel() == null) {
        if (goLastSessionDate == null) {
            goLastSessionDate = new Date();
            LogUtil.e(TAG, TAG_SESSION + "NOT SESSION");
            return;
        }
        LogUtil.d(TAG, TAG_SESSION + "IS SESSION");
        Date loDateInit = goLastSessionDate;
        Date loDatEnd = new Date();
        long llMillisInit, llMilisEnd, llDiff;
        long llSecondDiff = Constants.SESSION.TIME;

        Calendar loCalendarInit = Calendar.getInstance();
        Calendar loCalendarEnd = Calendar.getInstance();

        loCalendarInit.setTime(loDateInit);
        loCalendarEnd.setTime(loDatEnd);

        llMillisInit = loCalendarInit.getTimeInMillis();
        llMilisEnd = loCalendarEnd.getTimeInMillis();

        llDiff = llMilisEnd - llMillisInit;
        long llDiffSeconds = Math.abs(llDiff / 1000);

        if (llDiffSeconds > llSecondDiff) {
            onSessionExpired(Constants.SESSION.MESSAGE_END_SESSION);
        } else {
            goLastSessionDate = loDatEnd;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        validateFrontSession();
    }

    public final void showExceptionError(Throwable poThrowable) {
        if (poThrowable instanceof BaseException) {
            final BaseException loBaseException = (BaseException) poThrowable;
            //if (loBaseException.getCodeError().equals(ConstantsNet.NETWORK.ERROR.SESSION_END)) {
            //    onSessionExpired(loBaseException.getMessage());
            //} else {
            //    showError(poThrowable.getMessage());
            //}
            showError(poThrowable.getMessage());
        } else {
            showError(poThrowable.getMessage());
        }
    }

    public void showError(String psMessage) {
    }

    //public static ListAdapter.OnItemClickListener getGlClickItemList() {
    //    return glClickItemList;
    //}
//
    //public static void setGlClickItemList(ListAdapter.OnItemClickListener glClickItemList) {
    //    BancaMovilBaseActivity.glClickItemList = glClickItemList;
    //}
}
