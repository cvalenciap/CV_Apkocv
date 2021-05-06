package pe.com.sedapal.ofivirtual.di.components;

import javax.inject.Singleton;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.module.ApplicationModule;
import pe.com.sedapal.ofivirtual.domain.repository.EnchufateRepository;
import pe.com.sedapal.ofivirtual.domain.repository.FileRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ForgetPasswordRepository;
import pe.com.sedapal.ofivirtual.domain.repository.IncidentsRepository;
import pe.com.sedapal.ofivirtual.domain.repository.InsertarPagoMovilRepository;
import pe.com.sedapal.ofivirtual.domain.repository.InvoicesRepository;
import pe.com.sedapal.ofivirtual.domain.repository.MasterRepository;
import pe.com.sedapal.ofivirtual.domain.repository.NisRepository;
import pe.com.sedapal.ofivirtual.domain.repository.PayPlaceRepository;
import pe.com.sedapal.ofivirtual.domain.repository.TokenRepository;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidDocumentTypeRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidPayRefRepository;
import pe.com.sedapal.ofivirtual.domain.repository.ValidSupplyRepository;
import pe.com.sedapal.ofivirtual.domain.repository.VersionRepository;

/**
 * Created by sedapal on 9/06/17.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent extends MainComponent {

    UserRepository userRepository();

    MasterRepository masterRepository();

    TokenRepository tokenRepository();

    ValidSupplyRepository validSupplyRepository();

    ValidPayRefRepository validPayRefRepository();

    ValidDocumentTypeRepository validDocumentTypeRepository();

    ForgetPasswordRepository forgetPasswordRepository();

    InvoicesRepository invoicesRepository();

    NisRepository nisRepository();

    FileRepository fileRepository();

    PayPlaceRepository payPlaceRepository();

    IncidentsRepository incidentsRepository();

    EnchufateRepository enchufateRepository();

    InsertarPagoMovilRepository pagoVisaEnviarCorreoRepository();

    VersionRepository versionRepository();

}
