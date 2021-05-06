package pe.com.sedapal.ofivirtual.data;

import android.app.Application;
import io.realm.Realm;

public class DataApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
    }
}
