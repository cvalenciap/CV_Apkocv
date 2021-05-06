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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

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
import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.sedapal.MunicipalitiesPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.MunicipalitiesView;
import pe.com.sedapal.ofivirtual.ui.adapter.CustomInfoWindowMapAdapter;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

public class IncidentPlaceFragment extends BaseFragmentPlaces implements
        MunicipalitiesView,
        GoogleMap.OnMarkerClickListener{

    @BindView(R.id.fabMyLocation)
    FloatingActionButton fabMyLocation;

    @BindView(R.id.rlNoLocationEnabled)
    RelativeLayout rlNoLocationEnabled;

    @BindView(R.id.rlContentPendingInvoice)
    RelativeLayout rlContentPendingInvoice;

    @BindView(R.id.btnReintentarGps)
    Button btnReintentarGps;

    @BindView(R.id.crdInfoUpdateDataMap)
    CardView crdInfoUpdateDataMap;

    @Inject
    MunicipalitiesPresenter opMunicipalitiesPresenter;

    @Inject
    public Navigator navigator;

    private GoogleMap goGoogleMap;

    private LatLng goCurrentLatLng;
    private List<Marker> gaoIncidentsMarkers;
    private boolean gbInService = false;
    private boolean gbInWait = false;
    private boolean gbIsEnableAgent = true;
    public boolean isMarkerClick = false;

    public boolean isZoomed = false;
    public boolean isFirstLoadMap = true;

    public static IncidentPlaceFragment newInstance() {
        Bundle args = new Bundle();
        IncidentPlaceFragment fragment = new IncidentPlaceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CommercialOfficeComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_incident_place, container, false);
        ButterKnife.bind(this, view);
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
        this.opMunicipalitiesPresenter.setView(this);
    }

    @Override
    public void onMapReady(GoogleMap poGoogleMap) {

        this.goGoogleMap = poGoogleMap;
        this.goGoogleMap.setOnMarkerClickListener(this);
        if (ActivityCompat.checkSelfPermission((Activity)getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Activity)getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.goGoogleMap.setMyLocationEnabled(true);
        this.goGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.goGoogleMap.setOnCameraIdleListener(() -> {
            LogUtil.i(TAG, "setOnCameraIdleListener - STOP");
            if (goCurrentLatLng != null) {
                gbInWait = true;
                if (!gbInService) {
                    if(isMarkerClick) {
                        isMarkerClick = false;
                    }else {
                        doGetPoints();
                    }
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
        if (this.goGoogleMap == null || goCurrentLatLng == null) {
            return;
        }
        gbInService = true;
        LogUtil.i(TAG, "initGetPointToCurrent - initialize");

        /**
         * Obteniendo el listado de distritos
         */
        opMunicipalitiesPresenter.initialize();

        /**
         * Obteniendo ultima posicion almacenada en shared preferences
         */
        obtenerUltimaPosicion();
    }

    public void obtenerUltimaPosicion(){
        /**
         *Obteniendo ultima posicion almacenada en SharedPreferences
         */
        if(isFirstLoadMap) {
            isFirstLoadMap = false;
            CommonUtil.Coordenates coordenates = new CommonUtil.Coordenates();
            coordenates = CommonUtil.getLastLocation(getContext());
            if (coordenates.getLatitud() != 0 && coordenates.getLongitud() != 0) {
                onLocationChangedCamera(new LatLng(coordenates.getLatitud(), coordenates.getLongitud()));
            }
        }
    }

    public void showPoints(List<IncidentsMunicipalitiesModel> laoUbigeoModel) {
        if(!isZoomed) {
            startLocationUpdates();
            isZoomed = true;
        }

        BitmapDescriptor loBitmapDescriptor = null;
        List<Marker> laoMarker = null;
        loBitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_incident);
        laoMarker = gaoIncidentsMarkers;

        for (IncidentsMunicipalitiesModel loUbigeoModel : laoUbigeoModel) {
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
        isMarkerClick = true;
        IncidentsMunicipalitiesModel loUbigeoModel = (IncidentsMunicipalitiesModel) poMarker.getTag();
        goGoogleMap.setInfoWindowAdapter(new CustomInfoWindowMapAdapter(LayoutInflater.from(getActivity())));
        goGoogleMap.setOnInfoWindowClickListener(null);
        poMarker.showInfoWindow();
        CameraUpdate loCameraUpdate = CameraUpdateFactory.newLatLngZoom(loUbigeoModel.getLatLng(), 12);
        goGoogleMap.animateCamera(loCameraUpdate);

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
                goGoogleApiClient, this);
        onLocationChangedCamera(new LatLng(poLocation.getLatitude(), poLocation.getLongitude()));
        /**
         * Guardando la ultima posicion en SharedPreferences
         */
        CommonUtil.saveLastLocation(getContext(),poLocation.getLatitude(),poLocation.getLongitude());
    }

    private void onLocationChangedCamera(LatLng poLatLng) {
        this.goCurrentLatLng = poLatLng;
        CameraUpdate loCameraUpdate = CameraUpdateFactory.newLatLngZoom(this.goCurrentLatLng, 12);
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
        this.gbInService = false;
        showDialogError(psMessage);
    }

    /**
     * Webservices
     */

    @Override
    public void showSucessListMunicipalities(List<MunicipalitiesModel> poListMunicipalities) {
        /**
         * Una vez obtenida la lista de elementos se enviara a ser mostrado
         */
        this.gbInService = false;
        this.gaoIncidentsMarkers = new ArrayList<>();
        this.goGoogleMap.clear();

        if (poListMunicipalities != null) {
            if (!poListMunicipalities.isEmpty()) {
                for (MunicipalitiesModel poIncidet : poListMunicipalities) {
                    if(poIncidet.isMapa()) {
                        showPoints(poIncidet.getIncidencias());
                    }
                }
            }

        }
    }

    @Override
    public void showLoadingPersonalized() {
        //pbProgressBarLoadData.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        crdInfoUpdateDataMap.startAnimation(animation);
        crdInfoUpdateDataMap.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoadingPersonalized() {
        //pbProgressBarLoadData.setVisibility(View.GONE);

        Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);
        crdInfoUpdateDataMap.startAnimation(animation);
        crdInfoUpdateDataMap.setVisibility(View.GONE);
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
        //                goGoogleApiClient, this);
        //        goGoogleApiClient.disconnect();
        //    }
        //}
        super.onStop();
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

        goGoogleApiClient = null;
        isZoomed = false;
    }
}
