package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;

public class PayErrorActivity extends BaseActivity {

    @BindView(R.id.toolbar_close)
    Toolbar toolbar_close;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.btnRegresar)
    Button btnRegresar;
    @BindView(R.id.txtDescripcionPago)
    TextView txtDescripcionPago;

    private static final String TAG = PayErrorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_error);
        ButterKnife.bind(this);
        setUpView();
    }

    public static Intent getCallingIntent(Context poContext) {
        Intent loIntent = new Intent(poContext, PayErrorActivity.class);
        return loIntent;
    }

    private void setUpView() {
        configToolbar();
        configNotificationBar();
    }

    private void configToolbar(){
        setSupportActionBar(toolbar_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_close.setNavigationIcon(R.drawable.ic_close_toolbar_gray);
        toolbar_title.setText("");
    }

    private void configNotificationBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.color_white));
        }
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

    @Override
    public void onBackPressed() {
        Intent mIntent = new Intent();
        setResult(RESULT_OK, mIntent);

        super.onBackPressed();
    }

    @OnClick(R.id.btnRegresar)
    public void btnRegresar(){
        onBackPressed();
    }
}
