package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.IncidentsMunicipalitiesModel;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Created by jsaenz on 20/03/2019
 */
public class IncidentsMunicipalitiesAdapter extends RecyclerView.Adapter<IncidentsMunicipalitiesAdapter.IncidentsMunicipalitiesViewHolder> {
    private final LayoutInflater goLayoutInflater;
    private List<IncidentsMunicipalitiesModel> goIncidentsMunicipalitiesModel;
    private final Context goContext;

    AreaAffectedAdapter loAreaAffectedAdapter;
    LinearLayoutManager loDetailInvoicesAdapter;

    public IncidentsMunicipalitiesAdapter(Context poContext) {
        this.goContext = poContext;
        this.goLayoutInflater =
                (LayoutInflater) poContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.goIncidentsMunicipalitiesModel = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.goIncidentsMunicipalitiesModel != null) ? this.goIncidentsMunicipalitiesModel.size() : 0;
    }

    @Override
    public IncidentsMunicipalitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        loAreaAffectedAdapter = new AreaAffectedAdapter(parent.getContext());
        loDetailInvoicesAdapter = new LinearLayoutManager(parent.getContext(), LinearLayoutManager.VERTICAL, false);

        final View view = this.goLayoutInflater.inflate(R.layout.row_incidents_municipalities, parent, false);
        return new IncidentsMunicipalitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncidentsMunicipalitiesViewHolder holder, int position) {
        final IncidentsMunicipalitiesModel loIncidentsMunicipalitiesViewHolder = this.goIncidentsMunicipalitiesModel.get(position);

        holder.txtTipoIncidencia.setText(loIncidentsMunicipalitiesViewHolder.getTipoIncidencia());
        holder.txtEstado.setText(String.valueOf(loIncidentsMunicipalitiesViewHolder.getEstadoIncidencia()));
        holder.txtFechaInicio.setText(CommonUtil.getFormatDateValue_hhmm(loIncidentsMunicipalitiesViewHolder.getFechaInicio()));
        holder.txtFechaEstSolucion.setText(CommonUtil.getFormatDateValue_hhmm(loIncidentsMunicipalitiesViewHolder.getFechaEstimadaSol()));
        holder.txtObservacionDescripcion.setText(loIncidentsMunicipalitiesViewHolder.getObservacion());

        if(!loIncidentsMunicipalitiesViewHolder.getAreasAfectadas().isEmpty()) {
            holder.rcvAreasAfectadas.setLayoutManager(loDetailInvoicesAdapter);
            holder.rcvAreasAfectadas.setAdapter(loAreaAffectedAdapter);
            loAreaAffectedAdapter.setAffectedAreaList(loIncidentsMunicipalitiesViewHolder.getAreasAfectadas());
        }else {
            holder.lblAreas.setVisibility(View.GONE);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setIncidentsMunicipalitiesList(List<IncidentsMunicipalitiesModel> poIncidentsMunicipalitiesModel){
        this.validateIncidentsMunicipalitiesList(poIncidentsMunicipalitiesModel);
        this.goIncidentsMunicipalitiesModel = (List<IncidentsMunicipalitiesModel>) poIncidentsMunicipalitiesModel;
        this.notifyDataSetChanged();
    }

    public void clearAdapter(){
        goIncidentsMunicipalitiesModel.clear();
        notifyDataSetChanged();
    }

    private void validateIncidentsMunicipalitiesList(List<IncidentsMunicipalitiesModel> poIncidentsMunicipalitiesModel) {
        if (poIncidentsMunicipalitiesModel == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public class IncidentsMunicipalitiesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTipoIncidencia)
        TextView txtTipoIncidencia;
        @BindView(R.id.txtEstado)
        TextView txtEstado;
        @BindView(R.id.txtFechaInicio)
        TextView txtFechaInicio;
        @BindView(R.id.txtFechaEstSolucion)
        TextView txtFechaEstSolucion;
        @BindView(R.id.txtObservacionDescripcion)
        TextView txtObservacionDescripcion;
        @BindView(R.id.rcvAreasAfectadas)
        RecyclerView rcvAreasAfectadas;
        @BindView(R.id.lblAreas)
        TextView lblAreas;

        public IncidentsMunicipalitiesViewHolder(View poItemView) {
            super(poItemView);
            ButterKnife.bind(this, poItemView);
        }
    }
}
