package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.HasComponent;
import pe.com.sedapal.ofivirtual.di.components.DaggerIncidentsMunicipalitiesComponent;
import pe.com.sedapal.ofivirtual.di.components.IncidentsMunicipalitiesComponent;
import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;
import pe.com.sedapal.ofivirtual.presenter.sedapal.IncidentsMunicipalitiesPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.IncidentsMunicipalitiesView;
import pe.com.sedapal.ofivirtual.ui.adapter.IncidentsMunicipalitiesAdapter;

public class IncidentsMunicipalitiesActivity extends BaseActivity implements HasComponent<IncidentsMunicipalitiesComponent>, IncidentsMunicipalitiesView {
    @BindView(R.id.toolbar_back)
    Toolbar toolbar_back;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.rcvIncidentsMunicipalities)
    RecyclerView rcvIncidentsMunicipalities;

    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;
    //error
    @BindView(R.id.llError)
    LinearLayout llError;
    @BindView(R.id.txtErrorDetail)
    TextView txtErrorDetail;

    IncidentsMunicipalitiesAdapter goIncidentsMunicipalitiesAdapter;

    private IncidentsMunicipalitiesComponent goIncidentsMunicipalitiesComponent;
    private static String BUNDLE_NIS_MUNICIPALITIES_SELECTED = "BUNDLE_NIS_MUNICIPALITIES_SELECTED";
    public MunicipalitiesModel municipalitiesModelSelected;

    @Inject
    IncidentsMunicipalitiesPresenter goIncidentsMunicipalitiesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidents_municipalities);
        ButterKnife.bind(this);
        initializeInjector();
        setupView();
    }

    private void initializeInjector() {
        this.goIncidentsMunicipalitiesComponent = DaggerIncidentsMunicipalitiesComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goIncidentsMunicipalitiesComponent.inject(IncidentsMunicipalitiesActivity.this);
    }

    private void configToolbar() {
        setSupportActionBar(toolbar_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_back.setNavigationIcon(R.drawable.ic_back_toolbar);
        toolbar_title.setText(getResources().getString(R.string.lbl_tittle_toolbar_incidencia_operativa) + "\n" + municipalitiesModelSelected.getNomMunicipio());
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

    public static Intent getCallingIntent(Context poContext, MunicipalitiesModel poMunicipalitiesModel) {
        Bundle loBundle = new Bundle();
        loBundle.putSerializable(BUNDLE_NIS_MUNICIPALITIES_SELECTED, poMunicipalitiesModel);
        Intent loIntent = new Intent(poContext, IncidentsMunicipalitiesActivity.class);
        loIntent.putExtras(loBundle);
        return loIntent;
    }

    public void setupView() {
        this.goIncidentsMunicipalitiesPresenter.setView(this);
        municipalitiesModelSelected = (MunicipalitiesModel) getIntent().getSerializableExtra(BUNDLE_NIS_MUNICIPALITIES_SELECTED);

        configToolbar();
        configControls();
        setupRecyclerView();
        initializeIncidents();
    }

    public void initializeIncidents() {
        this.goIncidentsMunicipalitiesPresenter.initialize(municipalitiesModelSelected.getCodMunicipio());
    }

    /**
     * CONFIGURANDO LAS VISTAS LUEGO DE OBTENER LOS DATOS
     */

    public void configControls() {

        /**
         * Hide layouts from response
         */
        llCircleProgressBar.setVisibility(View.VISIBLE);
        llError.setVisibility(View.GONE);
        txtErrorDetail.setVisibility(View.GONE);
    }

    public void setupRecyclerView() {
        this.goIncidentsMunicipalitiesAdapter = new IncidentsMunicipalitiesAdapter(getApplicationContext());

        LinearLayoutManager loDetailInvoicesAdapter = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        this.rcvIncidentsMunicipalities.setLayoutManager(loDetailInvoicesAdapter);
        this.rcvIncidentsMunicipalities.setNestedScrollingEnabled(true);
        this.rcvIncidentsMunicipalities.setAdapter(goIncidentsMunicipalitiesAdapter);
    }

    //region WEBSERVICES RESPONSE


    @Override
    public void showSucessListIncidentsMunicipalities(List<IncidentsMunicipalitiesModel> poIncidentsMunicipalitiesModel) {
        if(poIncidentsMunicipalitiesModel != null)
            if(!poIncidentsMunicipalitiesModel.isEmpty())
                goIncidentsMunicipalitiesAdapter.setIncidentsMunicipalitiesList(poIncidentsMunicipalitiesModel);
    }

    @Override
    public void showLoadingPersonalized() {
        llCircleProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingPersonalized() {
        llCircleProgressBar.setVisibility(View.GONE);
    }

    //endregion

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public IncidentsMunicipalitiesComponent getComponent() {
        return this.goIncidentsMunicipalitiesComponent;
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
        this.goIncidentsMunicipalitiesPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goIncidentsMunicipalitiesPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goIncidentsMunicipalitiesPresenter.destroy();
    }
}
