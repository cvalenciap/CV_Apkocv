package pe.com.sedapal.ofivirtual.navigation;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.model.ForgetPasswordSupplyModel;
import pe.com.sedapal.ofivirtual.model.ListPendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.LoginNewUserModel;
import pe.com.sedapal.ofivirtual.model.MunicipalitiesModel;
import pe.com.sedapal.ofivirtual.model.PayInvoicesModel;
import pe.com.sedapal.ofivirtual.model.PayLiquidationModel;
import pe.com.sedapal.ofivirtual.model.PendingInvoicesModel;
import pe.com.sedapal.ofivirtual.model.SubsidiaryModel;
import pe.com.sedapal.ofivirtual.ui.activity.CommercialOfficeActivity;
import pe.com.sedapal.ofivirtual.ui.activity.DetailInvoceActivity;
import pe.com.sedapal.ofivirtual.ui.activity.FindYourPlaceDetailActivity;
import pe.com.sedapal.ofivirtual.ui.activity.GraphicsActivity;
import pe.com.sedapal.ofivirtual.ui.activity.IncidentsMunicipalitiesActivity;
import pe.com.sedapal.ofivirtual.ui.activity.LoginActivity;
import pe.com.sedapal.ofivirtual.ui.activity.OlvContraCorreoActivity;
import pe.com.sedapal.ofivirtual.ui.activity.OlvContraSuministroActivity;
import pe.com.sedapal.ofivirtual.ui.activity.OnboardingActivity;
import pe.com.sedapal.ofivirtual.ui.activity.PayErrorActivity;
import pe.com.sedapal.ofivirtual.ui.activity.PaySuccessActivity;
import pe.com.sedapal.ofivirtual.ui.activity.PaySummaryActivity;
import pe.com.sedapal.ofivirtual.ui.activity.RecordatorioPasswordActivity;
import pe.com.sedapal.ofivirtual.ui.activity.RegisterUserActivity;
import pe.com.sedapal.ofivirtual.ui.activity.RegisterUserSuccessActivity;
import pe.com.sedapal.ofivirtual.ui.activity.SplashActivity;
import pe.com.sedapal.ofivirtual.ui.activity.TerminosCondicionesActivity;
import pe.com.sedapal.ofivirtual.ui.activity.UpdateDataUserActivity;
import pe.com.sedapal.ofivirtual.ui.activity.VerificationCodeActivity;
import pe.com.sedapal.ofivirtual.ui.fragment.MunicipalitiesAffectedFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PaymentPlaceFragment;
import pe.com.sedapal.ofivirtual.ui.service.PayLiquidationService;
import pe.com.sedapal.ofivirtual.ui.service.SyncService;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Class used to navigate through the application.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
public class Navigator {
    public static final int REQUEST_TERM_CONDIC = 1;
    public static final int REQUEST_PAY_SUCESS = 2;
    public static final int REQUEST_PAY_ERROR = 400;

    /**
     * Constructs a {@link Navigator}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public Navigator() {
        //empty
    }

    /**
     * Goes to the login screen.
     *
     * @param poContext A {@link Context} needed to open the destiny activity.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */

    public void navigateToSplash(Activity poContext) {
        if (poContext != null) {
            Intent loIntentLogin = SplashActivity.getCallingIntent(poContext);
            poContext.startActivity(loIntentLogin);
            poContext.finishAffinity();
        }
    }

    public void navigateToLogin(Activity poContext) {
        if (poContext != null) {
            Intent loIntentLogin = LoginActivity.getCallingIntent(poContext);
            poContext.startActivity(loIntentLogin);
            poContext.finishAffinity();
        }
    }

    public void navigateToRegistroUsuario(Activity poContext) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = RegisterUserActivity.getCallingIntent(poContext);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToTerminosCondiciones(Activity poContext, int poCodeRequest) {
        if (poContext != null) {
            Intent loIntentTerminosCondicioens = TerminosCondicionesActivity.getCallingIntent(poContext);
            poContext.startActivityForResult(loIntentTerminosCondicioens, poCodeRequest);
        }
    }

    public void navigateToPaySucess(Activity poContext, int poCodeRequest, PayLiquidationModel poPayLiquidationModel, double usoCanalOnline) {
        if (poContext != null) {
            Intent loIntentPaySuccess = PaySuccessActivity.getCallingIntent(poContext, poPayLiquidationModel, usoCanalOnline);
            poContext.startActivityForResult(loIntentPaySuccess, poCodeRequest);
        }
    }

    public void navigateToPayError(Activity poContext, int poCodeRequest) {
        if (poContext != null) {
            Intent loIntentPaySuccess = PayErrorActivity.getCallingIntent(poContext);
            poContext.startActivityForResult(loIntentPaySuccess, poCodeRequest);
        }
    }

    public void navigateToRecordatorioContrasenia(Activity poContext, ForgetPasswordSupplyModel poForgetPasswordSupplyModel, String poSupply) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = RecordatorioPasswordActivity.getCallingIntent(poContext, poForgetPasswordSupplyModel, poSupply);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToOlvContraCorreoActivity(Activity poContext) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = OlvContraCorreoActivity.getCallingIntent(poContext);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToOlvContraSuministroActivity(Activity poContext) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = OlvContraSuministroActivity.getCallingIntent(poContext);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToOnboardingActivity(Activity poContext) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = OnboardingActivity.getCallingIntent(poContext);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToCommercialOfficeActivity(Activity poContext, LoginNewUserModel poLoginNewUserModel) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = CommercialOfficeActivity.getCallingIntent(poContext, poLoginNewUserModel);
            poContext.startActivity(loIntentRegistroUsuario);
            poContext.finishAffinity();
        }
    }

    public void navigateToUpdateDataUserActivity(Activity poContext, LoginNewUserModel poUserModel) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = UpdateDataUserActivity.getCallingIntent(poContext, poUserModel);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToPaySummaryActivity(Activity poContext, ListPendingInvoicesModel poListPendingInvoicesModel, long GET_BUNDLE_NIS_RAD) {
        if (poContext != null) {
            Intent loIntentPaySummaryActivity = PaySummaryActivity.getCallingIntent(poContext, poListPendingInvoicesModel, GET_BUNDLE_NIS_RAD);
            poContext.startActivity(loIntentPaySummaryActivity);
        }
    }

    public void navigateToIncidentsMunicipalitiesActivity(Activity poContext, MunicipalitiesModel poMunicipalitiesModel) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = IncidentsMunicipalitiesActivity.getCallingIntent(poContext, poMunicipalitiesModel);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public static PendingInvoicesModel goPendingInvoicesModel;
    public static PayInvoicesModel goPayInvoicesModel;

    public void navigateToDetailInvoiceActivity(Activity poContext, PendingInvoicesModel poPendingInvoicesModel, PayInvoicesModel poPayInvoicesModel) {
        goPendingInvoicesModel = poPendingInvoicesModel;
        goPayInvoicesModel = poPayInvoicesModel;

        if (poContext != null) {
            List<String> lasPermission = new ArrayList<>();
            lasPermission.add(WRITE_EXTERNAL_STORAGE);
            if (!mayPermission(poContext, lasPermission, Type.INVOCE_DETAIL_ACTIVITY.ordinal()))
                return;

            Intent loIntentInvoiceDetail = DetailInvoceActivity.getCallingIntent(poContext, goPendingInvoicesModel, goPayInvoicesModel);
            poContext.startActivity(loIntentInvoiceDetail);
        }
    }

    public void navigateToGraphicsActivity(Activity poContext, long nisRad) {
        if (poContext != null) {
            Intent loIntentRegistroUsuario = GraphicsActivity.getCallingIntent(poContext, nisRad);
            poContext.startActivity(loIntentRegistroUsuario);
        }
    }

    public void navigateToFindYourLocalDetail(Context poContext, SubsidiaryModel poUbigeoModel) {
        if (poContext != null) {
            Intent loIntentToLaunch = FindYourPlaceDetailActivity.getCallingIntent(poContext, poUbigeoModel);
            poContext.startActivity(loIntentToLaunch);
        }
    }

    public void navigateToVerificationCodeActivity(Activity poContext, String psEmaillUser, LoginNewUserModel poUserModel) {
        if (poContext != null) {
            Intent loVerificationCode = VerificationCodeActivity.getCallingIntent(poContext, psEmaillUser, poUserModel);
            poContext.startActivity(loVerificationCode);
        }
    }

    public void navigateToRegisterUserSuccessActivity(Activity poContext, String psMesssage, String psEmail) {
        if (poContext != null) {
            Intent loIntent = RegisterUserSuccessActivity.getCallingIntent(poContext, psMesssage, psEmail);
            poContext.startActivity(loIntent);
        }
    }

    public void startSyncService(Context poContext) {
        if (poContext != null) {
            Intent loIntentToLaunch = SyncService.getCallingIntent(poContext);
            poContext.startService(loIntentToLaunch);
        }
    }


    public void startPayLiquidationService(
            Context poContext,
            String customerEmail,
            String numeroLiquidacion,
            String nisRad,
            String amount,
            String TRANSACTION_ID,
            String ACTION_CODE,
            String STATUS,
            String TRANSACTION_DATE,
            String ACTION_DESCRIPTION,
            String TRACE_NUMBER,
            String CARD,
            String BRAND,
            String authCorreo,
            String flagChannel,
            String listaRegistros) {

        if (poContext != null) {
            Intent loIntentToLaunch = PayLiquidationService.getCallingIntent(
                    poContext,
                    customerEmail,
                    numeroLiquidacion,
                    nisRad,
                    amount,
                    TRANSACTION_ID,
                    ACTION_CODE,
                    STATUS,
                    TRANSACTION_DATE,
                    ACTION_DESCRIPTION,
                    TRACE_NUMBER,
                    CARD,
                    BRAND,
                    authCorreo,
                    flagChannel,
                    listaRegistros);
            poContext.startService(loIntentToLaunch);
        }
    }

    /**
     * Solicitando permisos al navegar por los activities y/o fragments
     */

    public enum Type implements Serializable {
        //LOGIN, FIND_YOUR_PLACE, FIND_YOUR_PLACE_FRAGMENT;
        INVOCE_DETAIL_ACTIVITY, FIND_YOUR_PLACE_FRAGMENT, MUNICIPALITIES_FRAGMENT;

        public void go(Activity poActivity, Navigator poNavigator) {
            if (INVOCE_DETAIL_ACTIVITY.equals(this)) {
                poNavigator.navigateToDetailInvoiceActivity(poActivity, goPendingInvoicesModel, goPayInvoicesModel);
            }

            if (FIND_YOUR_PLACE_FRAGMENT.equals(this)) {
                CommercialOfficeActivity main = (CommercialOfficeActivity) poActivity;
                PaymentPlaceFragment fyp = PaymentPlaceFragment.newInstance();
                main.onFragmentInteraction(fyp);
            }

            if (MUNICIPALITIES_FRAGMENT.equals(this)) {
                CommercialOfficeActivity main = (CommercialOfficeActivity) poActivity;
                MunicipalitiesAffectedFragment fyp = MunicipalitiesAffectedFragment.newInstance();
                main.onFragmentInteraction(fyp);
            }
        }
    }

    public void navigateToPermission(Context poContext) {
        if (poContext != null) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", poContext.getPackageName(), null);
            intent.setData(uri);
            ((Activity) poContext).startActivityForResult(intent, 100);
        }
    }

    public boolean mayPermission(Activity poContext, List<String> lasPermission, int piType) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> lasPermissionPending = new ArrayList<>();
        for (String lsPermission : lasPermission) {
            if (poContext.checkSelfPermission(lsPermission) != PackageManager.PERMISSION_GRANTED) {
                lasPermissionPending.add(lsPermission);
            }
        }
        if (lasPermissionPending.isEmpty()) {
            return true;
        } else {
            poContext.requestPermissions(lasPermissionPending.toArray(new String[lasPermissionPending.size()]), piType);
            return false;
        }
    }
}
