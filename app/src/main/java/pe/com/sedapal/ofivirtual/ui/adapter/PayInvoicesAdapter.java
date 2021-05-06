package pe.com.sedapal.ofivirtual.ui.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.util.CommonUtil;

/**
 * Created by jsaenz on 10/03/2019
 */

public class PayInvoicesAdapter extends BaseAdapterScrollLoad<PayInvoicesModel> {

    public interface OnItemClickListener {
        /**
         * On Item Clicked in list to {@link PayInvoicesModel}.
         *
         * @author jsaenz
         * @since 10/03/2019
         */

        void onPayInvoiceItemClicked(View view, PayInvoicesModel obj, int pos);

        void onPayInvoiceItemLongClick(View view, PayInvoicesModel obj, int pos);
    }

    // region Member Variables
    private Context context;
    private FooterViewHolder footerViewHolder;
    private OnItemClickListener onItemClickListener;
    /**
     * Multiple selection variables
     */
    private SparseBooleanArray selectedMultipleItemPayInvoice;
    private int currentMultiselectedidx = -1;
    // endregion

    // region Constructors
    public PayInvoicesAdapter(Context goContext) {
        super();
        context = goContext;
        selectedMultipleItemPayInvoice = new SparseBooleanArray();
    }
    // endregion

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pay_invoices, parent, false);

        final PayInvoicesViewHolder holder = new PayInvoicesViewHolder(v);
        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_load_resource_recyclerview, parent, false);

        final FooterViewHolder holder = new FooterViewHolder(v);
        holder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onReloadClickListener != null){
                    onReloadClickListener.onReloadClick();
                }
            }
        });

        return holder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }
    boolean isMultiSelect = false;

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final PayInvoicesViewHolder holder = (PayInvoicesViewHolder) viewHolder;

        final PayInvoicesModel loPayInvoiceViewHolder = getItem(position);
        if (loPayInvoiceViewHolder != null) {
            holder.txtMesRecibo.setText(CommonUtil.getDateValueInvoice(context,loPayInvoiceViewHolder.getMes()));
            holder.txtMontoTotal.setText(context.getResources().getString(R.string.lbl_money_soles) + " "+ CommonUtil.valueDoubleFormat(loPayInvoiceViewHolder.getCobrado()));
            holder.txtTipoConsumo.setText(loPayInvoiceViewHolder.getTipRec());

            if(loPayInvoiceViewHolder.getFecha_pago()==null || loPayInvoiceViewHolder.getFecha_pago().equals("")){
                holder.txtFechaPago.setText("");
                holder.txtEtiquetaFechaPago.setVisibility(View.GONE);
            }else
            {
                holder.txtFechaPago.setText(CommonUtil.getFormatDateValue(loPayInvoiceViewHolder.getFecha_pago()));
                holder.txtEtiquetaFechaPago.setVisibility(View.VISIBLE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(!isMultiSelect) {
                    //    onItemClickListener.onPayInvoiceItemClicked(items.get(position));
                    //} else {
                        onItemClickListener.onPayInvoiceItemClicked(v, loPayInvoiceViewHolder, position);
                    //}
                }
            });

            /**
             * Eventos para seleccionar las filas
             */

            holder.itemView.setActivated(selectedMultipleItemPayInvoice.get(position, false));

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isMultiSelect = true;
                    if (onItemClickListener == null) return false;
                    onItemClickListener.onPayInvoiceItemLongClick(v, loPayInvoiceViewHolder, position);
                    return true;
                }
            });
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;

        holder.loadingImageView.setIndeterminate(true);
    }

    @Override
    protected void displayLoadMoreFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        add(new PayInvoicesModel());
    }

    // region Inner Classes

    public static class PayInvoicesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgIconoEstado)
        ImageView imgIconoEstado;
        @BindView(R.id.txtMesRecibo)
        TextView txtMesRecibo;
        @BindView(R.id.txtMontoTotal)
        TextView txtMontoTotal;
        @BindView(R.id.txtTipoConsumo)
        TextView txtTipoConsumo;
        @BindView(R.id.txtFechaPago)
        TextView txtFechaPago;

        @BindView(R.id.txtEtiquetaFechaPago)
        TextView txtEtiquetaFechaPago;

        public PayInvoicesViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loadmore_progress)
        ProgressBar loadingImageView;
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.reload_btn)
        Button reloadButton;

        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * Set on item click listener of {@link OnItemClickListener}.
     *
     * @param poOnItemClickListener List of {@link OnItemClickListener}.
     * @author jsaenz
     * @version 1.0
     * @since 12/01/2017
     */
    public void setOnItemClickListener(OnItemClickListener poOnItemClickListener) {
        this.onItemClickListener = poOnItemClickListener;
    }

}