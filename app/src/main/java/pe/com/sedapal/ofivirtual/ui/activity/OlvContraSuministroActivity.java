package pe.com.sedapal.ofivirtual.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.di.components.DaggerForgetPasswordSupplyComponent;
import pe.com.sedapal.ofivirtual.di.components.ForgetPasswordSupplyComponent;
import pe.com.sedapal.ofivirtual.model.ApplicantTypeModel;
import pe.com.sedapal.ofivirtual.model.ForgetPasswordSupplyModel;
import pe.com.sedapal.ofivirtual.model.SecretQuestionModel;
import pe.com.sedapal.ofivirtual.presenter.sedapal.ApplicantTypePresenter;
import pe.com.sedapal.ofivirtual.presenter.user.ForgetPasswordSupplyPresenter;
import pe.com.sedapal.ofivirtual.presenter.user.SecretQuestionPresenter;
import pe.com.sedapal.ofivirtual.presenter.view.ApplicantTypeView;
import pe.com.sedapal.ofivirtual.presenter.view.ForgetPasswordSupplyView;
import pe.com.sedapal.ofivirtual.presenter.view.SecretQuestionView;
import pe.com.sedapal.ofivirtual.ui.widget.MaterialSpinner;
import pe.com.sedapal.ofivirtual.util.ValidationUtil;

public class OlvContraSuministroActivity extends BaseActivity implements ApplicantTypeView, SecretQuestionView, ForgetPasswordSupplyView {
    @BindView(R.id.toolbar_logo)
    Toolbar toolbar_logo;
    @BindView(R.id.edtxtSuminsitro)
    EditText edtxtSuminsitro;
    @BindView(R.id.spnTipoSolicitante)
    MaterialSpinner spnTipoSolicitante;
    @BindView(R.id.spnPreguntaSecreta)
    MaterialSpinner spnPreguntaSecreta;
    @BindView(R.id.edtxtRespuesta)
    EditText edtxtRespuesta;
    @BindView(R.id.btnEnviarRecuContra_suministro)
    Button btnEnviarRecuContra;
    @BindView(R.id.txtVolverRecuContra)
    TextView txtVolverRecuContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olv_contra_suministro);
        initializeInjector();
        ButterKnife.bind(this);
        setUpView();
    }

    private ForgetPasswordSupplyComponent goForgetPasswordSupplyComponent;

    @Inject
    ApplicantTypePresenter goApplicantTypePresenter;

    @Inject
    SecretQuestionPresenter goSecretQuestionPresenter;

    @Inject
    ForgetPasswordSupplyPresenter goForgetPasswordSupplyPresenter;

    private void initializeInjector() {
        this.goForgetPasswordSupplyComponent = DaggerForgetPasswordSupplyComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.goForgetPasswordSupplyComponent.inject(OlvContraSuministroActivity.this);
    }

    private void setUpView() {
        this.goApplicantTypePresenter.setView(this);
        this.goSecretQuestionPresenter.setView(this);
        this.goForgetPasswordSupplyPresenter.setView(this);

        configToolbar();
        configLayout();
        eventosControles();
        loadControls();
    }

    /**
     * Carga y configurcion de los controles del layout
     */
    private void configToolbar(){
        setSupportActionBar(toolbar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_logo.setNavigationIcon(R.drawable.ic_back_toolbar);
    }

    public void configLayout(){
        edtxtSuminsitro.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtil.CANTIDAD_CARACTERES_SUMINSITRO)});

        spnTipoSolicitante.setEnabled(false);
        spnPreguntaSecreta.setEnabled(false);
        edtxtRespuesta.setEnabled(false);
    }

    public void loadControls(){
        goApplicantTypePresenter.initialize();
        goSecretQuestionPresenter.obtainListSecretQuestion();
        btnEnviarRecuContra.setEnabled(false);
    }

    /**
     * Validaciones para realizar eventos con los editText luego de la escritura de carácteres
     */
    public void eventosControles() {
        edtxtRespuesta.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    handled = true;
                    onClickRecordarContrasenia();
                }
                return handled;
            }
        });

        edtxtSuminsitro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == ValidationUtil.CANTIDAD_CARACTERES_SUMINSITRO) {
                    habPostEvalSuminsitro();
                    hideKeyboard();
                    //goSuminsitroPresenter.initialize(edtxtSuminsitro.getText().toString());
                } else {
                    desaEvalSuminsitro();
                    //desaCampNumSuministr();
                }
            }
        });

        edtxtRespuesta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >=1) {
                    btnEnviarRecuContra.setEnabled(true);
                } else {
                    btnEnviarRecuContra.setEnabled(false);
                }
            }
        });
    }

    public void habPostEvalSuminsitro(){
        spnTipoSolicitante.setEnabled(true);
        spnPreguntaSecreta.setEnabled(true);
        edtxtRespuesta.setEnabled(true);
    }

    public void desaEvalSuminsitro(){
        spnTipoSolicitante.setEnabled(false);
        spnPreguntaSecreta.setEnabled(false);
        edtxtRespuesta.setEnabled(false);

        spnTipoSolicitante.setSelection(0);
        spnPreguntaSecreta.setSelection(0);
        edtxtRespuesta.getText().clear();
    }

    @Override
    public void showSucessApplicantType(List<ApplicantTypeModel> poListApplicantTypeModel) {
        ArrayAdapter<ApplicantTypeModel> adapter = new ArrayAdapter<ApplicantTypeModel>(this, android.R.layout.simple_spinner_item, poListApplicantTypeModel);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnTipoSolicitante.setAdapter(adapter);
        spnTipoSolicitante.setPaddingSafe(0, 0, 0, 0);
    }
    @Override
    public void showSucessListSecretQuestion(List<SecretQuestionModel> poListSecretQuestionModel) {
        ArrayAdapter<SecretQuestionModel> adapter = new ArrayAdapter<SecretQuestionModel>(this, android.R.layout.simple_spinner_item, poListSecretQuestionModel);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spnPreguntaSecreta.setAdapter(adapter);
        spnPreguntaSecreta.setPaddingSafe(0, 0, 0, 0);
    }

    @Override
    public void showForgetPasswordSupplySuccess(ForgetPasswordSupplyModel poForgetPasswordSupplyModel) {
        navigator.navigateToRecordatorioContrasenia(this, poForgetPasswordSupplyModel, edtxtSuminsitro.getText().toString());
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

    public static Intent getCallingIntent(Context poContext) {
        Intent loIntent = new Intent(poContext, OlvContraSuministroActivity.class);
        return loIntent;
    }

    @OnClick(R.id.txtVolverRecuContra)
    public void txtVolver(){
        onBackPressed();
    }

    @OnClick(R.id.btnEnviarRecuContra_suministro)
    public void onClickRecordarContrasenia(){
        ApplicantTypeModel spnApplicantTypeModel = spnTipoSolicitante.getSelectedItem() == null ? new ApplicantTypeModel() : (ApplicantTypeModel)spnTipoSolicitante.getSelectedItem();
        SecretQuestionModel spnSecretQuestionModel = spnPreguntaSecreta.getSelectedItem() == null ? new SecretQuestionModel() : (SecretQuestionModel)spnPreguntaSecreta.getSelectedItem();
        String strSuministro = edtxtSuminsitro.getText().toString();
        String strRespuesta = edtxtRespuesta.getText().toString();

        if(!strSuministro.equalsIgnoreCase("") && !strRespuesta.equalsIgnoreCase("")) {
            goForgetPasswordSupplyPresenter.initialize(
                    Integer.parseInt(strSuministro),
                    spnApplicantTypeModel.getIdTipoSolicitante(),
                    spnSecretQuestionModel.getIdPreguntaSecreta(),
                    strRespuesta
            );
        }
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String psMessage) {
        showDialogError(psMessage);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.goApplicantTypePresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.goApplicantTypePresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.goApplicantTypePresenter.destroy();
    }
}
