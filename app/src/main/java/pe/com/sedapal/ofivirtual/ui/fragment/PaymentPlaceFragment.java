package pe.com.sedapal.ofivirtual.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.model.AgencyModel;
import pe.com.sedapal.ofivirtual.model.SubsidiaryModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ListAgencyPresenter;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ListSubsidiaryPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.ListAgencyView;
import pe.com.sedapal.ofivirtual.presenter.view.ListSubsidiaryView;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

public class PaymentPlaceFragment extends BaseFragmentPlaces implements
        GoogleMap.OnMarkerClickListener,
        ListAgencyView,
        ListSubsidiaryView{

    @BindView(R.id.fabMyLocation)
    FloatingActionButton fabMyLocation;

    @BindView(R.id.rlNoLocationEnabled)
    RelativeLayout rlNoLocationEnabled;

    @BindView(R.id.rlContentPendingInvoice)
    RelativeLayout rlContentPendingInvoice;

    @BindView(R.id.pbProgressBarLoadData)
    ProgressBar pbProgressBarLoadData;

    @BindView(R.id.btnReintentarGps)
    Button btnReintentarGps;

    @Inject
    ListAgencyPresenter opListAgencyPresenter;

    @Inject
    ListSubsidiaryPresenter opListSubsidiaryPresenter;

    @Inject
    public Navigator navigator;

    private GoogleMap goGoogleMap;

    private LatLng goCurrentLatLng;
    private List<Marker> gaoAgencyMarkers;
    private boolean gbInService = false;
    private boolean gbInWait = false;
    private boolean gbIsEnableAgent = true;

    public boolean isZoomed = false;

    public static PaymentPlaceFragment newInstance() {
        Bundle args = new Bundle();
        PaymentPlaceFragment fragment = new PaymentPlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaymentPlaceFragment.this.getComponent(CommercialOfficeComponent.class).inject(PaymentPlaceFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_payment_place, container, false);
        ButterKnife.bind(PaymentPlaceFragment.this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpView();
        if (isGooglePlayServicesAvailable() && (isEnabledGPS())) setUpMaps();
        updateValuesFromBundle(savedInstanceState);
    }



    private boolean isEnabledGPS() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            rlNoLocationEnabled.setVisibility(View.VISIBLE);
            rlContentPendingInvoice.setVisibility(View.GONE);

            return false;
        }

        hideInfoDisableGps();

        return true;
    }

    public void hideInfoDisableGps(){
        rlNoLocationEnabled.setVisibility(View.GONE);
        rlContentPendingInvoice.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_DISABLED:
                break;

            case REQUEST_LOCATION:
                reintentarConexionGps();
                break;
        }
    }

    @OnClick(R.id.btnReintentarGps)
    public void reintentarConexionGps(){
        isZoomed = false;

        if (isGooglePlayServicesAvailable() && (isEnabledGPS())) {
            hideInfoDisableGps();
            setUpMaps();
        }else {
            showDialogErrorConexionGps();
        }
    }

    public void setUpView() {
        PaymentPlaceFragment.this.opListAgencyPresenter.setView(PaymentPlaceFragment.this);
        PaymentPlaceFragment.this.opListSubsidiaryPresenter.setView(PaymentPlaceFragment.this);
    }

    @Override
    public void onMapReady(GoogleMap poGoogleMap) {

        PaymentPlaceFragment.this.goGoogleMap = poGoogleMap;
        PaymentPlaceFragment.this.goGoogleMap.setOnMarkerClickListener(PaymentPlaceFragment.this);
        if (ActivityCompat.checkSelfPermission((Activity)getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Activity)getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        PaymentPlaceFragment.this.goGoogleMap.setMyLocationEnabled(true);
        PaymentPlaceFragment.this.goGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        PaymentPlaceFragment.this.goGoogleMap.setOnCameraIdleListener(() -> {
            LogUtil.i(TAG, "setOnCameraIdleListener - STOP");
            if (goCurrentLatLng != null) {
                gbInWait = true;
                if (!gbInService) {
                    doGetPoints();
                }
            } else {
                gbInWait = true;
                if (!gbInService) {
                    doGetPoints();
                }
            }
        });
    }

    private void doGetPoints() {
        LogUtil.i(TAG, "doGetPoints -  gbInWait: " + gbInWait);
        if (gbInWait) {
            gbInWait = false;
            goCurrentLatLng = goGoogleMap.getCameraPosition().target;
            initGetPointToCurrent();
            LogUtil.i(TAG, "doGetPoints - currentlatlg: " + goCurrentLatLng.toString());
        }
    }

    private void initGetPointToCurrent() {
        LogUtil.i(TAG, "initGetPointToCurrent - init");
        if (PaymentPlaceFragment.this.goGoogleMap == null || goCurrentLatLng == null) {
            return;
        }
        gbInService = true;
        LogUtil.i(TAG, "initGetPointToCurrent - initialize");
        opListSubsidiaryPresenter.initialize(1);
    }

    @Override
    public void showSucessListSubsidiary(List<SubsidiaryModel> poPlaceModel) {
        PaymentPlaceFragment.this.gbInService = false;
        PaymentPlaceFragment.this.gaoAgencyMarkers = new ArrayList<>();
        PaymentPlaceFragment.this.goGoogleMap.clear();
        Fragment loFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frlContainer);

        if (poPlaceModel != null) {
            if (gbIsEnableAgent) {
                showPoints(poPlaceModel);
            }
        }
    }

    public void showPoints(List<SubsidiaryModel> laoUbigeoModel) {
        if(!isZoomed) {
            startLocationUpdates();
            isZoomed = true;
        }
        BitmapDescriptor loBitmapDescriptor = null;
        List<Marker> laoMarker = null;
        loBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_sedapal);
        laoMarker = gaoAgencyMarkers;

        for (SubsidiaryModel loUbigeoModel : laoUbigeoModel) {
            LatLng loLatLng = loUbigeoModel.getLatLng();
            if (loLatLng != null) {
                Marker loMarker = goGoogleMap.addMarker(new MarkerOptions()
                        .position(loLatLng)
                        .icon(loBitmapDescriptor));
                loMarker.setTag(loUbigeoModel);
                laoMarker.add(loMarker);
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker poMarker) {
        SubsidiaryModel loUbigeoModel = (SubsidiaryModel) poMarker.getTag();
        navigator.navigateToFindYourLocalDetail((Activity)getContext(),loUbigeoModel);
        return true;
    }

    public void showDialogErrorConexionGps(){
        showDialogWarningOption(getResources().getString(R.string.lbl_habilitar_gps),
                getResources().getString(R.string.lbl_descripcion_gps),
                getResources().getString(R.string.lbl_aceptar),
                new CallbackDialogOption() {
                    @Override
                    public void onClickBtnOption() {
                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_LOCATION);
                    }
                });
    }

    @OnClick({R.id.fabMyLocation})
    public void onViewClicked(View view) {
        if (isGooglePlayServicesAvailable() && (isEnabledGPS())) {
            startLocationUpdates();
        } else {
            showDialogErrorConexionGps();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(LOCATION_KEY, goCurrentLatLng);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                goCurrentLatLng = savedInstanceState.getParcelable(LOCATION_KEY);
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (goCurrentLatLng == null) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        LogUtil.i(TAG, "onConnectionSuspended - INIT");
        if (i == CAUSE_SERVICE_DISCONNECTED) {
            LogUtil.i(TAG, "onConnectionSuspended - CAUSE_SERVICE_DISCONNECTED");
        } else if (i == CAUSE_NETWORK_LOST) {
            LogUtil.i(TAG, "onConnectionSuspended - CAUSE_NETWORK_LOST");
        } else {
            LogUtil.i(TAG, "onConnectionSuspended - ELSE");
        }
    }

    @Override
    public void onLocationChanged(Location poLocation) {
        LogUtil.i(TAG, "onLocationChanged - INIT");
        LocationServices.FusedLocationApi.removeLocationUpdates(
                goGoogleApiClient, PaymentPlaceFragment.this);
        onLocationChangedCamera(new LatLng(poLocation.getLatitude(), poLocation.getLongitude()));

        /**
         * Guardando la ultima posicion en SharedPreferences
         */
        CommonUtil.saveLastLocation(getContext(),poLocation.getLatitude(),poLocation.getLongitude());
    }

    private void onLocationChangedCamera(LatLng poLatLng) {
        PaymentPlaceFragment.this.goCurrentLatLng = poLatLng;
        CameraUpdate loCameraUpdate = CameraUpdateFactory.newLatLngZoom(PaymentPlaceFragment.this.goCurrentLatLng, 12);
        goGoogleMap.animateCamera(loCameraUpdate);
        initGetPointToCurrent();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        LogUtil.i(TAG, "onConnectionFailed - INIT");
    }

    @Override
    public void showRetry() {
    }

    @Override
    public void hideRetry() {
    }

    @Override
    public void showError(String psMessage) {
        PaymentPlaceFragment.this.gbInService = false;
        showDialogError(psMessage);
        //DialogView.create(context()).showBasicDialog("", "");
    }


    public void onBackPressed() {

    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onStart() {
        //if (goGoogleApiClient != null) {
        //    goGoogleApiClient.connect();
        //}
        super.onStart();
    }

    @Override
    public void onStop() {
        //if (goGoogleApiClient != null) {
        //    if(goGoogleApiClient.isConnected()) {
        //        LocationServices.FusedLocationApi.removeLocationUpdates(
        //                goGoogleApiClient, PaymentPlaceFragment.this);
        //        goGoogleApiClient.disconnect();
        //    }
        //}
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        PaymentPlaceFragment.this.opListAgencyPresenter.resume();
        PaymentPlaceFragment.this.opListSubsidiaryPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        PaymentPlaceFragment.this.opListAgencyPresenter.pause();
        PaymentPlaceFragment.this.opListSubsidiaryPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PaymentPlaceFragment.this.opListAgencyPresenter.destroy();
        PaymentPlaceFragment.this.opListSubsidiaryPresenter.destroy();

        goGoogleApiClient = null;
        isZoomed = false;
    }


    /**
     * Webservices
     *
     * @param poListAgencyModel
     */

    @Override
    public void showSucessListAgency(List<AgencyModel> poListAgencyModel) {

    }

    @Override
    public void showLoadingPersonalized() {

    }

    @Override
    public void hideLoadingPersonalized() {

    }


    @Override
    public void showLoadingPersonalizedSubsidiary() {
        pbProgressBarLoadData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingPersonalizedSubsidiary() {
        pbProgressBarLoadData.setVisibility(View.GONE);
    }
}
