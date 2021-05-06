package pe.com.sedapal.ofivirtual.ui.adapter;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CustomInfoWindowMapAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String TAG = "CustomInfoWindowAdapter";
    private LayoutInflater inflater;

    public CustomInfoWindowMapAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    @Override
    public View getInfoContents(final Marker m) {
        return null;
    }

    @Override
    public View getInfoWindow(Marker m) {
        IncidentsMunicipalitiesModel loUbigeoModel = (IncidentsMunicipalitiesModel) m.getTag();
        //View v =  inflater.inflate(R.layout.infowindow_map_layout, null);


        //v.setBackground(v.getResources().getDrawable(R.drawable.button_blue_background));
        ContextThemeWrapper cw = new ContextThemeWrapper(inflater.getContext(), R.style.TransparentInfoWindow);
        LayoutInflater inflater = (LayoutInflater) cw
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.infowindow_map_layout, null);

        ((TextView)v.findViewById(R.id.txtTipoIncidencia)).setText(loUbigeoModel.getTipoIncidencia());
        ((TextView)v.findViewById(R.id.txtEstado)).setText(loUbigeoModel.getEstadoIncidencia());
        ((TextView)v.findViewById(R.id.txtSolEstado)).setText(CommonUtil.getFormatDateValue_hhmm(loUbigeoModel.getFechaEstimadaSol()));
        return v;
    }

}
