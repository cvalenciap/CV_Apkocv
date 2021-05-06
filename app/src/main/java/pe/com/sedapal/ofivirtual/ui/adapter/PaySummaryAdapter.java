package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Created by jsaenz on 11/03/2019
 */
public class PaySummaryAdapter extends RecyclerView.Adapter<PaySummaryAdapter.PaySummaryViewHolder> {
    private final LayoutInflater goLayoutInflater;
    private List<PendingInvoicesModel> goPendingInvoicesModel;
    private final Context goContext;

    public PaySummaryAdapter(Context poContext) {
        this.goContext = poContext;
        this.goLayoutInflater =
                (LayoutInflater) poContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.goPendingInvoicesModel = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.goPendingInvoicesModel != null) ? this.goPendingInvoicesModel.size() : 0;
    }

    @Override
    public PaySummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.goLayoutInflater.inflate(R.layout.row_pay_summary, parent, false);
        return new PaySummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaySummaryViewHolder holder, int position) {
        final PendingInvoicesModel loPaySummaryViewHolder = this.goPendingInvoicesModel.get(position);
        holder.txtMesRecibo.setText(CommonUtil.getMonthValue(goContext, loPaySummaryViewHolder.getMes()));
        holder.txtMontoTotal.setText(CommonUtil.valueDoubleFormat(loPaySummaryViewHolder.getDeuda()));
        holder.txtTipoConsumo.setText(loPaySummaryViewHolder.getTipoRecibo());
        holder.txtFechaVencimiento.setText(CommonUtil.getFormatDateValue(loPaySummaryViewHolder.getfFact()));

        if(position%2 == 0){
            holder.llContentPaysummanry.setBackgroundColor(goContext.getResources().getColor(R.color.color_fondo_row));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setPaySummaryList(List<PendingInvoicesModel> poPendingInvoicesModel){
        this.validatePaySummaryList(poPendingInvoicesModel);
        this.goPendingInvoicesModel = (List<PendingInvoicesModel>) poPendingInvoicesModel;
        this.notifyDataSetChanged();
    }


    private void validatePaySummaryList(List<PendingInvoicesModel> poPendingInvoicesModel) {
        if (poPendingInvoicesModel == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public class PaySummaryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtMesRecibo)
        TextView txtMesRecibo;
        @BindView(R.id.txtMontoTotal)
        TextView txtMontoTotal;
        @BindView(R.id.txtTipoConsumo)
        TextView txtTipoConsumo;
        @BindView(R.id.txtFechaVencimiento)
        TextView txtFechaVencimiento;
        @BindView(R.id.llContentPaysummanry)
        LinearLayout llContentPaysummanry;

        public PaySummaryViewHolder(View poItemView) {
            super(poItemView);
            ButterKnife.bind(this, poItemView);
        }
    }
}
