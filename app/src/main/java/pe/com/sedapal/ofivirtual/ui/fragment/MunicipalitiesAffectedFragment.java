package pe.com.sedapal.ofivirtual.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.sedapal.MunicipalitiesPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.MunicipalitiesView;

public class MunicipalitiesAffectedFragment extends BaseFragment implements MunicipalitiesView{
    @BindView(R.id.llMapIncidents)
    LinearLayout llMapIncidents;
    @BindView(R.id.llListIncidents)
    LinearLayout llListIncidents;

    @Inject
    public Navigator navigator;

    @Inject
    MunicipalitiesPresenter opMunicipalitiesPresenter;


    private static final String TAG = MunicipalitiesAffectedFragment.class.getSimpleName();


    public MunicipalitiesAffectedFragment() {
        // Required empty public constructor
    }

    public static MunicipalitiesAffectedFragment newInstance() {
        MunicipalitiesAffectedFragment fragment = new MunicipalitiesAffectedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CommercialOfficeComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View loView = inflater.inflate(R.layout.fragment_municipalities, container, false);
        ButterKnife.bind(this, loView);

        //eventControls();
        //setupRecyclerView();
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle poSavedInstanceState) {
        super.onViewCreated(view, poSavedInstanceState);
        //this.opMunicipalitiesPresenter.setView(this);
        if (poSavedInstanceState == null) {
            this.instanciateFragments();
            eventControls();
        }
    }

    Fragment FRAGMENT_INCIDENTS_LIST;
    Fragment FRAGMENT_INCIDENTS_PLACE;
    Fragment CURRENT_FRAGMENT;
    FragmentManager fragmentManager;

    public void instanciateFragments(){
        /**
         * Instanciando fragmentos que contendra la vista principal
         */
        FRAGMENT_INCIDENTS_LIST = IncidentsListFragment.newInstance();
        FRAGMENT_INCIDENTS_PLACE = IncidentPlaceFragment.newInstance();
        fragmentManager = getChildFragmentManager();
        CURRENT_FRAGMENT = FRAGMENT_INCIDENTS_LIST;
    }

    /**
     * Iniciando los fragments al cargar por primera vez la vista
     */
    private void eventControls() {
        fragmentManager.beginTransaction().add(R.id.content,FRAGMENT_INCIDENTS_LIST, "FRAGMENT_INCIDENTS_LIST").commit();
        fragmentManager.beginTransaction().add(R.id.content, FRAGMENT_INCIDENTS_PLACE, "FRAGMENT_INCIDENTS_PLACE").hide(FRAGMENT_INCIDENTS_PLACE).commit();
        llMapIncidents.setOnClickListener(mOnClickListener);
        llListIncidents.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.llListIncidents:
                    llListIncidents.setVisibility(View.GONE);
                    llMapIncidents.setVisibility(View.VISIBLE);
                    loadFragment(FRAGMENT_INCIDENTS_LIST);
                    break;

                case R.id.llMapIncidents:
                    llListIncidents.setVisibility(View.VISIBLE);
                    llMapIncidents.setVisibility(View.GONE);
                    loadFragment(FRAGMENT_INCIDENTS_PLACE);
                    break;
            }
        }
    };

    public void loadFragment(Fragment fragmentChange){
        if(CURRENT_FRAGMENT != fragmentChange)
            fragmentManager.beginTransaction().hide(CURRENT_FRAGMENT).show(fragmentChange).commit();
        CURRENT_FRAGMENT = fragmentChange;
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
    }

    @Override
    public Context context() {
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showSucessListMunicipalities(List<MunicipalitiesModel> poListMunicipalities) {

    }

    @Override
    public void showLoadingPersonalized() {

    }

    @Override
    public void hideLoadingPersonalized() {

    }
}