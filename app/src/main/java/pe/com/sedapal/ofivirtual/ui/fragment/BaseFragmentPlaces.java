package pe.com.sedapal.ofivirtual.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.util.LogUtil;
import pe.com.sedapal.ofivirtual.di.components.CommercialOfficeComponent;
import pe.com.sedapal.ofivirtual.presenter.view.BaseFragmentView;

public class BaseFragmentPlaces extends BaseFragment implements
        BaseFragmentView,
        OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener {

    public static GoogleApiClient goGoogleApiClient;

    public static final String TAG = BaseFragmentPlaces.class.getSimpleName();
    public static final String LOCATION_KEY = "LOCATION";
    public static final long UPDATE_INTERVAL = 20 * 1000;  /* 10 secs */
    public static final long FASTEST_INTERVAL = 2 * 1000; /* 2 sec */
    public static final int REQUEST_DISABLED = 10001;
    public static final int REQUEST_LOCATION = 10004;

    public GoogleMap goGoogleMap;
    public LatLng goCurrentLatLng;

    public static BaseFragmentPlaces newInstance() {
        Bundle args = new Bundle();
        BaseFragmentPlaces fragment = new BaseFragmentPlaces();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(CommercialOfficeComponent.class).inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isGooglePlayServicesAvailable() && (isEnabledGPS())) setUpMaps();
        updateValuesFromBundle(savedInstanceState);
    }

    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability loGoogleApiAvailability = GoogleApiAvailability.getInstance();
        int liStatus = loGoogleApiAvailability.isGooglePlayServicesAvailable(context());
        if (liStatus == ConnectionResult.SUCCESS) {
            return true;
        } else {
            loGoogleApiAvailability.getErrorDialog(getActivity(), liStatus, REQUEST_DISABLED, dialog -> {
                //finish();
            }).show();
            return false;
        }
    }

    private boolean isEnabledGPS() {
        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_DISABLED:
                break;

            case REQUEST_LOCATION:
                setUpMaps();
                break;
        }
    }

    public void setUpMaps() {
        SupportMapFragment goMapFragment = SupportMapFragment.newInstance();
        goMapFragment.getMapAsync(this);
        final FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frlContainer, goMapFragment);
        fragmentTransaction.commit();

        if (goGoogleApiClient == null) {
            goGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .build();
            goGoogleApiClient.connect();
        }
    }

    @Override
    public void onMapReady(GoogleMap poGoogleMap) {

        this.goGoogleMap = poGoogleMap;
        if (ActivityCompat.checkSelfPermission((Activity) getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Activity) getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.goGoogleMap.setMyLocationEnabled(true);
        this.goGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    protected void startLocationUpdates() {
        try {
            LogUtil.i(TAG, "startLocationUpdates - INIT");
            LocationServices.FusedLocationApi.removeLocationUpdates(goGoogleApiClient, this);
            LocationRequest goLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(UPDATE_INTERVAL)
                    .setFastestInterval(FASTEST_INTERVAL);

            if (ActivityCompat.checkSelfPermission((Activity) getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Activity) getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    goGoogleApiClient, goLocationRequest, this);
            LogUtil.i(TAG, "startLocationUpdates - END");
        }catch (Exception e){
            LogUtil.i(TAG, "startLocationUpdates - Exception" + e);
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
    }

    private void onLocationChangedCamera(LatLng poLatLng) {
        this.goCurrentLatLng = poLatLng;
        CameraUpdate loCameraUpdate = CameraUpdateFactory.newLatLngZoom(this.goCurrentLatLng, 12);
        goGoogleMap.animateCamera(loCameraUpdate);
        //initGetPointToCurrent();
    }

    public void onBackPressed() {

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
        return getActivity();
    }

    @Override
    public void onStart() {
        if (goGoogleApiClient != null) {
            goGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        if (goGoogleApiClient != null) {
            if (goGoogleApiClient.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(
                        goGoogleApiClient, this);
                goGoogleApiClient.disconnect();
            }
        }
        super.onStop();
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
        goGoogleApiClient = null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
