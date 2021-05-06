package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerCommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.ui.fragment.HomeFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.MunicipalitiesAffectedFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.OthersFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PaymentPlaceFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.ProfileFragment;
import pe.com.sedapal.ofivirtual.util.CommonUtil;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class CommercialOfficeActivity extends BaseActivity
        implements BaseDrawerFragment.OnBackFragment,
        BaseDrawerFragment.OnFragmentInteractionListener,
        HasComponent<CommercialOfficeComponent> {
    String TAG = CommercialOfficeActivity.class.getName();
    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.searchtoolbar)
    Toolbar searchtollbar;


    private static final String BUNDLE_USER_LOGIN = "USER_LOGIN_SUPPLY";
    private static final String BUNDLE_IS_PAY = "BUNDLE_IS_PAY";

    public boolean IS_PAY_SSUCESS = false;

    Fragment FRAGMENT_HOME;
    Fragment FRAGMENT_PAYMENT_PLACE;
    Fragment FRAGMENT_MUNICIPALITIES_AFFECTED;
    Fragment FRAGMENT_PROFILE;
    Fragment FRAGMENT_OTHERS;
    Fragment CURRENT_FRAGMENT;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commercial_office);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();

        /**
         * Se verifiara si ingresa por primera vez a la aplicacion
         * mostrando el tour guide
         */
        if(!CommonUtil.getFirstLogin(getApplicationContext())) {
            tourGuideApp();
        }
    }

    private void tourGuideApp() {
        CommonUtil.saveFirstLogin(getApplicationContext());

        View navigation_home = findViewById(R.id.navigation_home);
        View navigation_notifications = findViewById(R.id.navigation_notifications);
        View navigation_incidents = findViewById(R.id.navigation_incidents);
        View navigation_others = findViewById(R.id.navigation_others);
        ChainTourGuide tourGuide1 = ChainTourGuide.init(this).
                setToolTip(new ToolTip().
                        setTitle(getResources().getString(R.string.tour_tittle_1)).
                        setDescription(getResources().getString(R.string.tour_description_1)).
                        setGravity(Gravity.TOP | Gravity.RIGHT).
                        setBackgroundColor(getResources().getColor(R.color.colorPrimary))).
                playLater(navigation_home);

        ChainTourGuide tourGuide2 = ChainTourGuide.init(this).
                setToolTip(new ToolTip().
                        setTitle(getResources().getString(R.string.tour_tittle_2)).
                        setDescription(getResources().getString(R.string.tour_description_2)).
                        setGravity(Gravity.TOP | Gravity.RIGHT).
                        setBackgroundColor(getResources().getColor(R.color.colorPrimary))).
                playLater(navigation_notifications);

        ChainTourGuide tourGuide3 = ChainTourGuide.init(this).
                setToolTip(new ToolTip().
                        setTitle(getResources().getString(R.string.tour_tittle_3)).
                        setDescription(getResources().getString(R.string.tour_description_3)).
                        setGravity(Gravity.TOP).
                        setBackgroundColor(getResources().getColor(R.color.colorPrimary))).
                playLater(navigation_incidents);

        ChainTourGuide tourGuide4 = ChainTourGuide.init(this).
                setToolTip(new ToolTip().
                        setTitle(getResources().getString(R.string.tour_tittle_4)).
                        setDescription(getResources().getString(R.string.tour_description_4)).
                        setGravity(Gravity.TOP | Gravity.LEFT).
                        setBackgroundColor(getResources().getColor(R.color.colorPrimary))).
                playLater(navigation_others);

        Sequence sequence = new Sequence.SequenceBuilder().
                add(tourGuide1, tourGuide2, tourGuide3, tourGuide4).
                setDefaultOverlay(new Overlay()).
                setDefaultPointer(null).
                setContinueMethod(Sequence.ContinueMethod.OVERLAY).build();
        ChainTourGuide.init(this).playInSequence(sequence);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        refreshFragmentsAfterPay();
    }

    private CommercialOfficeComponent opCommercialOfficeComponent;

    private void initializeInjector() {
        opCommercialOfficeComponent = DaggerCommercialOfficeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public CommercialOfficeComponent getComponent() {
        return opCommercialOfficeComponent;
    }

    public static Intent getCallingIntent(Context poContext, LoginNewUserModel poLoginNewUserModel) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_USER_LOGIN, poLoginNewUserModel);
        Intent loIntent = new Intent(poContext, CommercialOfficeActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    private void setUpView() {
        if((LoginNewUserModel) getIntent().getSerializableExtra(BUNDLE_USER_LOGIN) != null){
            goUsuarioLogueado = (LoginNewUserModel) getIntent().getSerializableExtra(BUNDLE_USER_LOGIN);
        }
        instanciateFragments();
        configToolbar();
        eventControls();
    }

    private void configToolbar() {
        setSupportActionBar(toolbar_logo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void instanciateFragments(){
        /**
         * Instanciando fragmentos que contendra la vista principal
         */
        FRAGMENT_HOME = HomeFragment.newInstance(goUsuarioLogueado);
        FRAGMENT_PAYMENT_PLACE = PaymentPlaceFragment.newInstance();
        FRAGMENT_MUNICIPALITIES_AFFECTED = MunicipalitiesAffectedFragment.newInstance();
        FRAGMENT_PROFILE = ProfileFragment.newInstance("","");
        FRAGMENT_OTHERS = OthersFragment.newInstance(goUsuarioLogueado);
        fragmentManager = getSupportFragmentManager();
        CURRENT_FRAGMENT = FRAGMENT_HOME;
    }


    /**
     * Iniciando los fragments al cargar por primera vez la vista
     */
    private void eventControls() {
        fragmentManager.beginTransaction().add(R.id.content,FRAGMENT_HOME, "fragment_home").commit();
        fragmentManager.beginTransaction().add(R.id.content, FRAGMENT_PAYMENT_PLACE, "fragment_payment_place").hide(FRAGMENT_PAYMENT_PLACE).commit();
        fragmentManager.beginTransaction().add(R.id.content, FRAGMENT_MUNICIPALITIES_AFFECTED, "fragment_municipalities_affected").hide(FRAGMENT_MUNICIPALITIES_AFFECTED).commit();
        fragmentManager.beginTransaction().add(R.id.content, FRAGMENT_PROFILE, "fragment_profile").hide(FRAGMENT_PROFILE).commit();
        fragmentManager.beginTransaction().add(R.id.content, FRAGMENT_OTHERS, "fragment_others").hide(FRAGMENT_OTHERS).commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void refreshFragmentsAfterPay(){
        fragmentManager.beginTransaction().detach(FRAGMENT_HOME).commitAllowingStateLoss();
        fragmentManager.beginTransaction().attach(FRAGMENT_HOME).commitAllowingStateLoss();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(FRAGMENT_HOME);
                    return false;

                case R.id.navigation_notifications:
                    List<String> lasPermission = new ArrayList<>();
                    lasPermission.add(ACCESS_FINE_LOCATION);
                    if (navigator.mayPermission(CommercialOfficeActivity.this, lasPermission, Navigator.Type.FIND_YOUR_PLACE_FRAGMENT.ordinal())) {
                        loadFragment(FRAGMENT_PAYMENT_PLACE);
                    }
                    return false;

                case R.id.navigation_incidents:
                    List<String> lasPermissionM = new ArrayList<>();
                    lasPermissionM.add(ACCESS_FINE_LOCATION);
                    if (navigator.mayPermission(CommercialOfficeActivity.this, lasPermissionM, Navigator.Type.MUNICIPALITIES_FRAGMENT.ordinal())) {
                        loadFragment(FRAGMENT_MUNICIPALITIES_AFFECTED);
                    }
                    return false;

                //case R.id.navigation_profile:
                //    loadFragment(FRAGMENT_PROFILE);
                //    return false;

                case R.id.navigation_others:
                    loadFragment(FRAGMENT_OTHERS);
                    return false;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragmentChange){
        if(fragmentChange instanceof HomeFragment == false){
            searchtollbar.setVisibility(View.GONE);
        }

        if(CURRENT_FRAGMENT != fragmentChange)
            fragmentManager.beginTransaction().hide(CURRENT_FRAGMENT).show(fragmentChange).commit();
            CURRENT_FRAGMENT = fragmentChange;
            setEnabledOptionNavigationBottom(fragmentChange);
    }

    @Override
    public void onFragmentInteraction(Fragment poFragment) {
        if(poFragment instanceof PaymentPlaceFragment){
            fragmentManager.beginTransaction().hide(CURRENT_FRAGMENT).show(FRAGMENT_PAYMENT_PLACE).commit();
            CURRENT_FRAGMENT = FRAGMENT_PAYMENT_PLACE;
        }

        if(poFragment instanceof MunicipalitiesAffectedFragment){
            fragmentManager.beginTransaction().hide(CURRENT_FRAGMENT).show(FRAGMENT_MUNICIPALITIES_AFFECTED).commit();
            CURRENT_FRAGMENT = FRAGMENT_MUNICIPALITIES_AFFECTED;
        }

        setEnabledOptionNavigationBottom(poFragment);
    }

    /**
     * Permite marcar los iconos del NavigationBottom cuando una seleccion se
     * tenga confirmada
     * @param
     */

    public void setEnabledOptionNavigationBottom(Fragment poFragment){
        int size = navigation.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigation.getMenu().getItem(i).setChecked(false);
        }

        if(poFragment instanceof HomeFragment){
            navigation.getMenu().getItem(0).setChecked(true);
        }

        if(poFragment instanceof PaymentPlaceFragment){
            navigation.getMenu().getItem(1).setChecked(true);
        }

        if(poFragment instanceof MunicipalitiesAffectedFragment){
            navigation.getMenu().getItem(2).setChecked(true);
        }

        //if(poFragment instanceof ProfileFragment){
        //    navigation.getMenu().getItem(3).setChecked(true);
        //}

        if(poFragment instanceof OthersFragment){
            navigation.getMenu().getItem(3).setChecked(true);
        }
    }

    @Override
    public void onBack(BaseDrawerFragment poBaseDrawerFragment) {

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            showToast(getResources().getString(R.string.lbl_presiona_salir_aplicacion));
            //showToastCorrect(getResources().getString(R.string.lbl_presiona_salir_aplicacion));

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }

    }


}
