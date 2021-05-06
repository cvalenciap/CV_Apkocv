
package pe.com.sedapal.ofivirtual.ui.component.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
@SuppressLint("ViewConstructor")
public class MyMarkerView extends MarkerView {

    private final TextView tvContent;
    private boolean goisMoneyValue;

    public MyMarkerView(Context context, int layoutResource, boolean isMoneyValue) {
        super(context, layoutResource);

        tvContent = findViewById(R.id.tvContent);
        goisMoneyValue = isMoneyValue;
    }

    // runs every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (goisMoneyValue) {
            //tvContent.setText(getContext().getResources().getString(R.string.lbl_money_soles) + " " + Utils.formatNumber(e.getY(), 2, true));
            tvContent.setText(getContext().getResources().getString(R.string.lbl_money_soles) + " " + CommonUtil.valueDoubleFormat(e.getY()));
        } else {
            //tvContent.setText(Utils.formatNumber(e.getY(), 2, true) + " " + getContext().getResources().getString(R.string.lbl_meters_cubic));
            tvContent.setText(CommonUtil.valueDoubleFormat(e.getY()) + " " + getContext().getResources().getString(R.string.lbl_meters_cubic));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
