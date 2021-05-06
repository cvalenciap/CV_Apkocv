package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerOnboaringComponent;
import pe.com.sedapal.ofivirtual.di.components.OnboaringComponent;
import pe.com.sedapal.ofivirtual.ui.adapter.OnboardingAdapter;
import pe.com.sedapal.ofivirtual.util.ItemOnboarding;

public class OnboardingActivity extends BaseActivity implements HasComponent<OnboaringComponent> {
    @BindView(R.id.txtRegistrate)
    TextView txtRegistrate;
    @BindView(R.id.btnIngresar)
    Button btnIngresar;
    @BindView(R.id.viewPagerCountDots)
    LinearLayout pager_indicator;
    @BindView(R.id.pager_introduction)
    ViewPager onboard_pager;
    @BindView(R.id.llRegistrate)
    LinearLayout llRegistrate;

    private int dotsCount;
    private ImageView[] dots;
    private OnboardingAdapter mAdapter;
    int previous_pos = 0;
    ArrayList<ItemOnboarding> onBoardItems = new ArrayList<>();
    private int mInterval = 4000; //Tiempo de la transicion
    private Handler mHandler;
    boolean esPrimeraVez = true;

    private OnboaringComponent goOnboaringComponent;

    private void initializeInjector() {
        this.goOnboaringComponent = DaggerOnboaringComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goOnboaringComponent.inject(OnboardingActivity.this);
    }

    @Override
    public OnboaringComponent getComponent() {
        return this.goOnboaringComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initializeInjector();
        ButterKnife.bind(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setUpView();
        mHandler = new Handler();
        startRepeatingTask();
    }

    public static Intent getCallingIntent(Context poContext) {
        Intent loIntent = new Intent(poContext, OnboardingActivity.class);
        return loIntent;
    }

    public void setUpView(){
        loadData();

        if(goUsuarioLogueado == null){
            llRegistrate.setVisibility(View.VISIBLE);
        }else {
            llRegistrate.setVisibility(View.INVISIBLE);
        }

        mAdapter = new OnboardingAdapter(this, onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);
        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(OnboardingActivity.this, R.drawable.ind_no_selecc_onboard));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(OnboardingActivity.this, R.drawable.ind_selecc_onboard));

                int pos = position + 1;
                if (pos == dotsCount && previous_pos == (dotsCount - 1))
                    show_animation();
                else if (pos == (dotsCount - 1) && previous_pos == dotsCount)
                    previous_pos = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setUiPageViewController();
    }

    public void loadData() {
        String[] desc = {DESC_ONBOARDING_1, DESC_ONBOARDING_2, DESC_ONBOARDING_3, DESC_ONBOARDING_4};
        Bitmap[] urlImageBackground = {BITMAP_IMG_ONBOARDING_1, BITMAP_IMG_ONBOARDING_2, BITMAP_IMG_ONBOARDING_3, BITMAP_IMG_ONBOARDING_4};
        Bitmap[] urlImageIcon = {BITMAP_ICON_ONBOARDING_1, BITMAP_ICON_ONBOARDING_2, BITMAP_ICON_ONBOARDING_3, BITMAP_ICON_ONBOARDING_4};

        for (int i = 0; i < urlImageBackground.length; i++) {
            ItemOnboarding item = new ItemOnboarding();
            item.setBitmapIcon(urlImageIcon[i]);
            item.setDescripcion(desc[i]);
            item.setBitmapImageBack(urlImageBackground[i]);

            onBoardItems.add(item);
        }
    }

    public void show_animation() {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(OnboardingActivity.this, R.drawable.ind_no_selecc_onboard));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(OnboardingActivity.this, R.drawable.ind_selecc_onboard));
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                if (esPrimeraVez) {
                    onboard_pager.setCurrentItem(0, true);
                    esPrimeraVez = false;
                } else{
                    switch (onboard_pager.getCurrentItem()) {
                        case 0:
                            onboard_pager.setCurrentItem(1, true);
                            break;
                        case 1:
                            onboard_pager.setCurrentItem(2, true);
                            break;
                        case 2:
                            onboard_pager.setCurrentItem(3, true);
                            break;
                        case 3:
                            onboard_pager.setCurrentItem(0, true);
                            break;
                    }
                }

            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }

    @OnClick(R.id.txtRegistrate)
    public void onClickRegistrate(){
        navigator.navigateToRegistroUsuario(this);
    }

    @OnClick(R.id.btnIngresar)
    public void onClickBtnIngresar(){
        /**
         * SE COMPRUEBA SI SE TIENEN LOS DATOS DE UN USUARIO LOGUEADO, para ello se obtiene la
         * variable global con los datos del usuario
         */
        if(goUsuarioLogueado != null){
            /**
             * Si encuentra un usuario logueado se dirige hacia el activity principal
             */
            navigator.navigateToCommercialOfficeActivity(this,goUsuarioLogueado);
        }else {
            /**
             * Si no encuentra lo envia a loguearse
             */
            navigator.navigateToLogin(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
