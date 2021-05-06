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
import pe.com.sedapal.ofivirtual.model.DetailPayInvoiceModel;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Created by jsaenz on 11/03/2019
 */
public class PayDetailAdapter extends RecyclerView.Adapter<PayDetailAdapter.DetailInvoiceViewHolder> {
    private final LayoutInflater goLayoutInflater;
    private List<DetailPayInvoiceModel> goDetailPayInvoiceModel;
    private final Context goContext;

    public PayDetailAdapter(Context poContext) {
        this.goContext = poContext;
        this.goLayoutInflater =
                (LayoutInflater) poContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.goDetailPayInvoiceModel = Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return (this.goDetailPayInvoiceModel != null) ? this.goDetailPayInvoiceModel.size() : 0;
    }

    @Override
    public DetailInvoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.goLayoutInflater.inflate(R.layout.row_detail_pay, parent, false);
        return new DetailInvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailInvoiceViewHolder holder, int position) {
        final DetailPayInvoiceModel loDetailInvoiceViewHolder = this.goDetailPayInvoiceModel.get(position);
        holder.txtFechaPagoDetail.setText(CommonUtil.getFormatDateValue(loDetailInvoiceViewHolder.getFechaPago()) + " - " + loDetailInvoiceViewHolder.getHoraPago());
        holder.txtFormaPagoDetail.setText(loDetailInvoiceViewHolder.getFormaPago());
        holder.txtLugarPagoDetail.setText(loDetailInvoiceViewHolder.getNomAgencia() + " " + loDetailInvoiceViewHolder.getNomSucursal());
        holder.txtMontoPagoDetail.setText(goContext.getResources().getString(R.string.lbl_money_soles) + " " + CommonUtil.valueDoubleFormat(loDetailInvoiceViewHolder.getMontoPago()));

        if(position%2==0){
            holder.llContentDetailPay.setBackgroundColor(goContext.getResources().getColor(R.color.color_fondo_row));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDetailPayInvoiceList(List<DetailPayInvoiceModel> poDetailPayInvoiceModel){
        this.validateDetailInvoiceList(poDetailPayInvoiceModel);
        this.goDetailPayInvoiceModel = (List<DetailPayInvoiceModel>) poDetailPayInvoiceModel;
        this.notifyDataSetChanged();
    }


    private void validateDetailInvoiceList(List<DetailPayInvoiceModel> poDetailPayInvoiceModel) {
        if (poDetailPayInvoiceModel == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    public class DetailInvoiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtFechaPagoDetail)
        TextView txtFechaPagoDetail;
        @BindView(R.id.txtFormaPagoDetail)
        TextView txtFormaPagoDetail;
        @BindView(R.id.txtLugarPagoDetail)
        TextView txtLugarPagoDetail;
        @BindView(R.id.txtMontoPagoDetail)
        TextView txtMontoPagoDetail;
        @BindView(R.id.llContentDetailPay)
        LinearLayout llContentDetailPay;

        public DetailInvoiceViewHolder(View poItemView) {
            super(poItemView);
            ButterKnife.bind(this, poItemView);
        }
    }
}
