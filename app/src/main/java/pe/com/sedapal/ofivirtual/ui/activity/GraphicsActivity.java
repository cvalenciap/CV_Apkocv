package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerGraphicsComponent;
import pe.com.sedapal.ofivirtual.di.components.GraphicsComponent;
import pe.com.sedapal.ofivirtual.model.HistoricConsumModel;
import pe.com.sedapal.ofivirtual.model.ListHistoricConsumModel;
import pe.com.sedapal.ofivirtual.presenter.user.ListHistoricConsumPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.ListHistoricConsumView;
import pe.com.sedapal.ofivirtual.ui.component.navigation.ViewPagerCustom;
import pe.com.sedapal.ofivirtual.ui.fragment.GraphicMetersFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.GraphicMoneyFragment;

public class GraphicsActivity extends BaseActivity implements HasComponent<GraphicsComponent>, ListHistoricConsumView {
    @BindView(R.id.toolbar_back)
    Toolbar toolbar_back;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.container)
    ViewPagerCustom mViewPager;
    @BindView(R.id.txtNumSupply)
    TextView txtNumSupply;

    //Load
    @BindView(R.id.rlProgressLoadScreeen)
    RelativeLayout rlProgressLoadScreeen;
    @BindView(R.id.pbProgressBarLoadScreen)
    ProgressBar pbProgressBarLoadScreen;
    @BindView(R.id.llError)
    LinearLayout llError;

    @BindView(R.id.llContentGraphics)
    LinearLayout llContentGraphics;

    private GraphicsComponent goGraphicsComponent;
    private static String BUNDLE_NIS_RAD_SELECTED = "BUNDLE_NIS_RAD_SELECTED";
    public long nisRadSelected;

    @Inject
    ListHistoricConsumPresenter goListHistoricConsumPresenter;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    final List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);
        ButterKnife.bind(this);
        initializeInjector();
        setupView();
    }

    private void initializeInjector() {
        this.goGraphicsComponent = DaggerGraphicsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goGraphicsComponent.inject(GraphicsActivity.this);
    }

    private void configToolbar() {
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_back.setNavigationIcon(R.drawable.ic_back_toolbar);
        toolbar_title.setText(getResources().getString(R.string.lbl_tittle_toolbar_graphics));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar_back.setVisibility(View.GONE);
            toolbar_title.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getCallingIntent(Context poContext, long nisRad) {
        Bundle loBundle = new Bundle();
        loBundle.putLong(BUNDLE_NIS_RAD_SELECTED, nisRad);
        Intent loIntent = new Intent(poContext, GraphicsActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    public void setupView(){
        this.goListHistoricConsumPresenter.setView(this);
        nisRadSelected = getIntent().getLongExtra(BUNDLE_NIS_RAD_SELECTED,0);
        txtNumSupply.setText(String.valueOf(nisRadSelected));
        configToolbar();
        initializeHistoricConsum();
    }

    public void initializeHistoricConsum(){
        this.goListHistoricConsumPresenter.initialize(nisRadSelected);
    }

    /**
     * CONFIGURANDO LAS VISTAS LUEGO DE OBTENER LOS DATOS
     */

    public void configControls(){
        fragments.add(GraphicMoneyFragment.newInstance(listaHistoricConsum));
        fragments.add(GraphicMetersFragment.newInstance(listaHistoricConsum));
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.disableSwipe(true);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //region WEBSERVICES RESPONSE
    ListHistoricConsumModel listaHistoricConsum;

    @Override
    public void showSucessListHistoricConsum(List<HistoricConsumModel> poHistoricConsumModel) {
        llError.setVisibility(View.GONE);
        llContentGraphics.setVisibility(View.VISIBLE);

        listaHistoricConsum = new ListHistoricConsumModel();
        listaHistoricConsum.setListHistoricConsumModel(poHistoricConsumModel);
        configControls();
    }

    @Override
    public void showErrorMessageListHistoricConsum(String poErrorMessage) {
        showDialogError(poErrorMessage);
    }

    @Override
    public GraphicsComponent getComponent() {
        return this.goGraphicsComponent;
    }

    //endregion

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;
        private String[] tabTitles = new String[]{getResources().getString(R.string.lbl_nuevos_soles), getResources().getString(R.string.lbl_metros_cubicos)};

        public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void showProgessDialog() {
        rlProgressLoadScreeen.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);
        llContentGraphics.setVisibility(View.GONE);
    }

    @Override
    public void hideProgessDialog() {
        rlProgressLoadScreeen.setVisibility(View.GONE);
    }

    @Override
    public void errorProgressDialog() {
        rlProgressLoadScreeen.setVisibility(View.VISIBLE);
        pbProgressBarLoadScreen.setVisibility(View.GONE);
        llError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        showDialogError(psMessage);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goListHistoricConsumPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goListHistoricConsumPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goListHistoricConsumPresenter.destroy();
    }
}
