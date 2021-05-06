package pe.com.sedapal.ofivirtual.ui.component.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by jsaenz on 03,enero,2019
 */
public class ViewPagerCustom extends ViewPager {
    private Boolean disable = false;

    public ViewPagerCustom(Context context) {
        super(context);
    }

    public ViewPagerCustom(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return disable ? false : super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return disable ? false : super.onTouchEvent(event);
    }

    public void disableSwipe(Boolean disable){
        //When disable = true not work the scroll and when disble = false work the swipe
        this.disable = disable;
    }
}
