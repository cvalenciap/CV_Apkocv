package pe.com.sedapal.ofivirtual.di.components;

import android.content.Context;
import android.view.View;
import pe.com.sedapal.ofivirtual.AndroidApplication;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.ui.activity.BaseActivity;
import pe.com.sedapal.ofivirtual.ui.service.BaseService;

/**
 * Created by sedapal on 8/06/17.
 */

public interface MainComponent {


    void inject(AndroidApplication o);

    void inject(BaseActivity baseActivity);

    void inject(BaseService baseService);

    void inject(View view);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();


}
