package pe.com.sedapal.ofivirtual.di.components;

import dagger.Component;
import pe.com.sedapal.ofivirtual.di.PerActivity;
import pe.com.sedapal.ofivirtual.di.module.ActivityModule;
import pe.com.sedapal.ofivirtual.di.module.UserModule;
import pe.com.sedapal.ofivirtual.ui.activity.LoginActivity;
import pe.com.sedapal.ofivirtual.ui.activity.RegisterUserSuccessActivity;
import pe.com.sedapal.ofivirtual.ui.activity.VerificationCodeActivity;

/**
 * A scope {@link PerActivity} component.
 * Injects geographic location specific Fragments.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {

    void inject(LoginActivity poLoginActivity);

    void inject(VerificationCodeActivity poVerificationCodeActivity);

    void inject(RegisterUserSuccessActivity poRegisterUserSuccessActivity);

}
