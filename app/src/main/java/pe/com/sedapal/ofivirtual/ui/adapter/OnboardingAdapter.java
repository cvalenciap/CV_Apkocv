package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.util.ItemOnboarding;

public class OnboardingAdapter extends PagerAdapter {

    private Context mContext;
    ArrayList<ItemOnboarding> onBoardItems;

    public OnboardingAdapter(Context mContext, ArrayList<ItemOnboarding> items) {
        this.mContext = mContext;
        this.onBoardItems = items;
    }

    @Override
    public int getCount() {
        return onBoardItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_onboarding, container, false);

        ItemOnboarding item = onBoardItems.get(position);

        LinearLayout llCircleProgressBar = (LinearLayout) itemView.findViewById(R.id.llCircleProgressBar);
        LinearLayout llContentItem = (LinearLayout) itemView.findViewById(R.id.llContentItem);

        RelativeLayout rlBackgroundViewPAger = (RelativeLayout) itemView.findViewById(R.id.rlBackgroundViewPager);
        ImageView imvBackground = (ImageView) itemView.findViewById(R.id.imvBackgroundImage);
        ImageView imgIconoOnb = (ImageView) itemView.findViewById(R.id.imgIconoOnb);

        //Cargando las imágenes
        imvBackground.setImageBitmap(item.getBitmapImageBack());
        imgIconoOnb.setImageBitmap(item.getBitmapIcon());

        llCircleProgressBar.setVisibility(View.GONE); //ELIMINAR
        llContentItem.setVisibility(View.VISIBLE); //ELIMINAR

        TextView txtDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
        txtDescripcion.setText(item.getDescripcion());

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}