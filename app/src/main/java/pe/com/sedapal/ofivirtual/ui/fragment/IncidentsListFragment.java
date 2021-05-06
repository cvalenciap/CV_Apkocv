package pe.com.sedapal.ofivirtual.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import pe.com.sedapal.ofivirtual.ui.adapter.MunicipalitiesAdapter;

public class IncidentsListFragment extends BaseFragment implements MunicipalitiesView {

    @BindView(R.id.rcvMunicipalitiesAffected)
    RecyclerView rcvMunicipalitiesAffected;
    @BindView(R.id.swrlMunicipalities)
    SwipeRefreshLayout swrlMunicipalities;
    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;
    @BindView(R.id.rlContentPendingInvoice)
    RelativeLayout rlContentPendingInvoice;

    //error
    @BindView(R.id.llError)
    LinearLayout llError;
    @BindView(R.id.txtErrorDetail)
    TextView txtErrorDetail;

    MunicipalitiesAdapter goMunicipalitiesAdapter;

    @Inject
    public Navigator navigator;

    @Inject
    MunicipalitiesPresenter opMunicipalitiesPresenter;


    private static final String TAG = IncidentsListFragment.class.getSimpleName();


    public IncidentsListFragment() {
        // Required empty public constructor
    }

    public static IncidentsListFragment newInstance() {
        IncidentsListFragment fragment = new IncidentsListFragment();
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
        final View loView = inflater.inflate(R.layout.fragment_incidents_list, container, false);
        ButterKnife.bind(this, loView);

        eventControls();
        setupRecyclerView();
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle poSavedInstanceState) {
        super.onViewCreated(view, poSavedInstanceState);
        this.opMunicipalitiesPresenter.setView(this);
        if (poSavedInstanceState == null) {
            this.loadMunicipalities();
        }
    }


    public void eventControls() {
        swrlMunicipalities.setColorSchemeColors(getResources().getColor(R.color.colorPrimarySwipe), getResources().getColor(R.color.colorSecondarySwipe));
        swrlMunicipalities.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                llError.setVisibility(View.GONE);
                onRefreshSwipe();
            }
        });

        /**
         * Hide layouts from response
         */
        llCircleProgressBar.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);
        txtErrorDetail.setVisibility(View.GONE);
    }

    public void setupRecyclerView() {
        this.goMunicipalitiesAdapter = new MunicipalitiesAdapter(getContext());
        this.goMunicipalitiesAdapter.setOnItemClickListener(onTextItemClickListener);

        LinearLayoutManager loDetailInvoicesAdapter = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        this.rcvMunicipalitiesAffected.setLayoutManager(loDetailInvoicesAdapter);
        this.rcvMunicipalitiesAffected.setNestedScrollingEnabled(true);
        this.rcvMunicipalitiesAffected.setAdapter(goMunicipalitiesAdapter);
    }

    public void loadMunicipalities(){
        this.opMunicipalitiesPresenter.initialize();
        goMunicipalitiesAdapter.clearAdapter();
    }

    //region RESPONSE WEBSERVICES

    @Override
    public void showSucessListMunicipalities(List<MunicipalitiesModel> poListMunicipalities) {
        if(poListMunicipalities != null)
            if(!poListMunicipalities.isEmpty())
        goMunicipalitiesAdapter.setMunicipalitiesList(poListMunicipalities);
    }

    public void onRefreshSwipe() {
        loadMunicipalities();
    }

    @Override
    public void showLoadingPersonalized() {
        llCircleProgressBar.setVisibility(View.VISIBLE);
        verifyIsRefreshingSwipe();
    }

    @Override
    public void hideLoadingPersonalized() {
        llCircleProgressBar.setVisibility(View.GONE);
        verifyIsRefreshingSwipe();
    }

    //endregion
    private MunicipalitiesAdapter.OnItemClickListener onTextItemClickListener = new MunicipalitiesAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(MunicipalitiesModel psMunicipalitiesModel) {
            goToIncidentsMunicipalities(psMunicipalitiesModel);
        }
    };


    public void goToIncidentsMunicipalities(MunicipalitiesModel poMunicipalitiesModel){
        navigator.navigateToIncidentsMunicipalitiesActivity((Activity) getContext(),poMunicipalitiesModel);

    }


    public void verifyIsRefreshingSwipe() {
        if (swrlMunicipalities.isRefreshing()) {
            swrlMunicipalities.setRefreshing(false);
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        llError.setVisibility(View.VISIBLE);
        txtErrorDetail.setVisibility(View.VISIBLE);
        txtErrorDetail.setText(psMessage);
    }

    @Override
    public Context context() {
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.opMunicipalitiesPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.opMunicipalitiesPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.opMunicipalitiesPresenter.destroy();
    }
}