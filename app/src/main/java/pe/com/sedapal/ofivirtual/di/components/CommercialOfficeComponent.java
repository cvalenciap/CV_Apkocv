package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.CommercialOfficeModule;
import pe.com.sedapal.ofivirtual.ui.activity.CommercialOfficeActivity;
import pe.com.sedapal.ofivirtual.ui.fragment.BaseFragmentPlaces;
import pe.com.sedapal.ofivirtual.ui.fragment.IncidentPlaceFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.IncidentsListFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.IncidentsPlaceFragmentV2;
import pe.com.sedapal.ofivirtual.ui.fragment.OthersFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PaymentPlaceFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PaymentPlaceFragmentV2;
import pe.com.sedapal.ofivirtual.ui.fragment.HomeFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.MunicipalitiesAffectedFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PayInvoicesFragment;
import pe.com.sedapal.ofivirtual.ui.fragment.PendingInvoicesFragment;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CommercialOfficeModule.class})
public interface CommercialOfficeComponent extends ActivityComponent {
    void inject(CommercialOfficeActivity poCommercialOfficeActivity);
    void inject(HomeFragment poHomeFragment);
    void inject(PendingInvoicesFragment poPendingInvoicesFragment);
    void inject(PayInvoicesFragment poPendingInvoicesFragment);
    void inject(MunicipalitiesAffectedFragment poMunicipalitiesAffectedFragment);
    void inject(PaymentPlaceFragmentV2 poPaymentPlaceFragmentV2);
    void inject(OthersFragment poOthersFragment);
    void inject(IncidentsPlaceFragmentV2 poIncidentsPlaceFragmentV2);
    void inject(IncidentsListFragment poIncidentsPlacesFragment);
    void inject(BaseFragmentPlaces poIncidentsPlacesFragment);
    void inject(PaymentPlaceFragment poIncidentsPlacesFragment);
    void inject(IncidentPlaceFragment poIncidentsPlacesFragment);
}
