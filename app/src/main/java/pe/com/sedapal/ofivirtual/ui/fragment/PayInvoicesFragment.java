package pe.com.sedapal.ofivirtual.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.navigation.Navigator;
import pe.com.sedapal.ofivirtual.presenter.user.PayInvoicesPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.PayInvoicesView;
import pe.com.sedapal.ofivirtual.ui.adapter.PayInvoicesAdapter;

public class PayInvoicesFragment extends BaseFragment implements PayInvoicesView, PayInvoicesAdapter.OnReloadClickListener {
    @BindView(R.id.rcvRecibosPendientes)
    RecyclerView rcvRecibosPendientes;
    @BindView(R.id.llFelicNoRecibosPagados)
    LinearLayout llFelicNoRecibosPagados;
    @BindView(R.id.rlContentPayInvoice)
    RelativeLayout rlContentPayInvoice;
    @BindView(R.id.llCircleProgressBar)
    LinearLayout llCircleProgressBar;
    @BindView(R.id.swrlPayInvoice)
    SwipeRefreshLayout swrlPayInvoice;
    //error
    @BindView(R.id.llError)
    LinearLayout llError;
    @BindView(R.id.txtErrorDetail)
    TextView txtErrorDetail;

    private LinearLayoutManager layoutManager;
    PayInvoicesAdapter goPayInvoicesAdapter;

    //region CONSTANTS AND VARIABLES
    //INDICA EL NUMERO DE NIS RAD OBTENIDO
    long GET_BUNDLE_NIS_RAD = 0;
    //INDICA LA CANTIDAD DE ELEMENTOS A TRAER EN CADA LISTA
    public static final int PAGE_SIZE = 10;
    //INDICA LA PAGINA QUE SE REQUERIRA EN LA LISTA
    private int CURRENT_PAGE = 1;
    //INDICA SI SE INGRESA Y SE LLAMA A LA LISTA POR PRIMERA VEZ O SI SE LLAMARA A LAS SIGUIENTES
    //PAGINAS DE LA LISTA
    private boolean findPayInvoicesFirstFetchCallback = true;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    public boolean isMultiselect = false;
    //endregion

    @Inject
    public Navigator navigator;

    @Inject
    PayInvoicesPresenter opPayInvoicesPresenter;


    private static final String TAG = PayInvoicesFragment.class.getSimpleName();

    private static final String BUNDLE_DATA_NIS_RAD = "BUNDLE_DATA_NIS_RAD";

    public PayInvoicesFragment() {
        // Required empty public constructor
    }

    public static PayInvoicesFragment newInstance(long nisRad) {
        PayInvoicesFragment fragment = new PayInvoicesFragment();
        Bundle args = new Bundle();
        args.putLong(BUNDLE_DATA_NIS_RAD, nisRad);
        fragment.setArguments(args);
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
        final View loView = inflater.inflate(R.layout.fragment_pay_invoices, container, false);
        ButterKnife.bind(this, loView);

        eventControls();
        setUpRecyclerViewPayInvoices();
        return loView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle poSavedInstanceState) {
        super.onViewCreated(view, poSavedInstanceState);
        this.opPayInvoicesPresenter.setView(this);
        getBundles();
        if (poSavedInstanceState == null) {
            this.loadPayInvoices();
        }
    }

    public void getBundles(){
        GET_BUNDLE_NIS_RAD = getArguments().getLong(BUNDLE_DATA_NIS_RAD);
    }

    public void eventControls() {
        swrlPayInvoice.setColorSchemeColors(getResources().getColor(R.color.colorPrimarySwipe), getResources().getColor(R.color.colorSecondarySwipe));
        swrlPayInvoice.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        llFelicNoRecibosPagados.setVisibility(View.GONE);
        llError.setVisibility(View.GONE);
        txtErrorDetail.setVisibility(View.GONE);
    }

    public void setUpRecyclerViewPayInvoices() {
        layoutManager = new LinearLayoutManager(getActivity());
        rcvRecibosPendientes.setLayoutManager(layoutManager);

        goPayInvoicesAdapter = new PayInvoicesAdapter(getContext());
        rcvRecibosPendientes.setAdapter(goPayInvoicesAdapter);

        // Pagination
        rcvRecibosPendientes.addOnScrollListener(recyclerViewOnScrollListener);
        goPayInvoicesAdapter.setOnReloadClickListener(this);
        goPayInvoicesAdapter.setOnItemClickListener(onPayInvoiceItemClickListener);

        //rcvRecibosPendientes.setItemAnimator(new DefaultItemAnimator());
        //runAnimationRecyclerView(rcvRecibosPendientes);
    }

    //region EVENTS RECYCLERVIEW
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }

        }
    };

    private PayInvoicesAdapter.OnItemClickListener onPayInvoiceItemClickListener =
            new PayInvoicesAdapter.OnItemClickListener() {
                @Override
                public void onPayInvoiceItemClicked(View view, PayInvoicesModel obj, int pos) {
                    PayInvoicesModel getPayInvoice = goPayInvoicesAdapter.getItem(pos);
                    goToDetailInvoiceActivity(getPayInvoice);

                }

                @Override
                public void onPayInvoiceItemLongClick(View view, PayInvoicesModel obj, int pos) {

                }
            };
    //endregion

    public void onRefreshSwipe() {
        //CLEAR AND INITIALIZE CONSTANTS AND VARIABLES
        goPayInvoicesAdapter.clear();
        CURRENT_PAGE = 1;
        findPayInvoicesFirstFetchCallback = true;
        isLastPage = false;
        isLoading = false;
        isMultiselect = false;
        goPayInvoicesAdapter.updateFooter(PayInvoicesAdapter.FooterType.LOAD_MORE);
        initializeGetValues(CURRENT_PAGE);
    }

    public void verifyIsRefreshingSwipe() {
        if (swrlPayInvoice.isRefreshing()) {
            swrlPayInvoice.setRefreshing(false);
        }
    }

    public void loadMoreItems() {
        findPayInvoicesFirstFetchCallback = false;
        isLoading = true;
        CURRENT_PAGE += 1;

        loadPayInvoices();
    }

    //region CALL WEBSERVICES

    public void loadPayInvoices() {
        //CAPTURANDO Y LLAMANDO AL WEBSERVICES CON EL NUMERO DE PAGINA SOLICITADA
        this.initializeGetValues(CURRENT_PAGE);
    }

    public void initializeGetValues(int currentPage) {
        Log.i("PAGINA", String.valueOf(currentPage));
        Log.i("GET_BUNDLE_NIS_RAD", String.valueOf(GET_BUNDLE_NIS_RAD));

        this.opPayInvoicesPresenter.initialize(GET_BUNDLE_NIS_RAD, currentPage, PAGE_SIZE);
    }

    //endregion

    //region RESPONSE WEBSERVICES

    @Override
    public void showSucessListPayInvoices(List<PayInvoicesModel> poPayInvoicesModel) {
        llCircleProgressBar.setVisibility(View.GONE);
        verifyIsRefreshingSwipe();

        if (poPayInvoicesModel.isEmpty()) {
            llFelicNoRecibosPagados.setVisibility(View.VISIBLE);
        } else {
            if (findPayInvoicesFirstFetchCallback) {
                isLoading = false;
                if (poPayInvoicesModel.size() > 0)
                    goPayInvoicesAdapter.addAll(poPayInvoicesModel);

                if (poPayInvoicesModel.size() >= PAGE_SIZE) {
                    goPayInvoicesAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            } else {
                goPayInvoicesAdapter.removeFooter();
                isLoading = false;

                if (poPayInvoicesModel.size() > 0)
                    goPayInvoicesAdapter.addAll(poPayInvoicesModel);

                if (poPayInvoicesModel.size() >= PAGE_SIZE) {
                    goPayInvoicesAdapter.addFooter();
                } else {
                    isLastPage = true;
                }
            }
        }
    }

    @Override
    public void showErrorMessagePay(String poErrorMessage) {
        verifyIsRefreshingSwipe();
        if (findPayInvoicesFirstFetchCallback) {
            llCircleProgressBar.setVisibility(View.GONE);
            llError.setVisibility(View.VISIBLE);
            txtErrorDetail.setVisibility(View.VISIBLE);
            txtErrorDetail.setText(poErrorMessage);
            llFelicNoRecibosPagados.setVisibility(View.GONE);
        } else {
            goPayInvoicesAdapter.updateFooter(PayInvoicesAdapter.FooterType.ERROR);
        }
    }

    //endregion

    @Override
    public void showError(String psMessage) {
        isLoading = false;
    }

    @Override
    public void onReloadClick() {
        goPayInvoicesAdapter.updateFooter(PayInvoicesAdapter.FooterType.LOAD_MORE);
        loadPayInvoices();
    }

    public void goToDetailInvoiceActivity(PayInvoicesModel getPayInvoice){
        getPayInvoice.setNisRad(GET_BUNDLE_NIS_RAD);
        PendingInvoicesModel poPendingInvoicesModel = new PendingInvoicesModel();
        navigator.navigateToDetailInvoiceActivity((Activity) getContext(), poPendingInvoicesModel,getPayInvoice);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public Context context() {
        return this.getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.opPayInvoicesPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.opPayInvoicesPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.opPayInvoicesPresenter.destroy();
    }
}