package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by Hernan Pareja on 03/08/2017.
 */
@Singleton
public class FileDataStoreFactory {

    private static final String TAG = FileDataStoreFactory.class.getSimpleName();
    private final Context goContext;

    @Inject
    FileDataStoreFactory(@NonNull Context poContext) {
        this.goContext = poContext;
    }

    public FileDataStore createDisk() {
        return new LocalFileDiskDataStore(goContext);
    }

    public FileDataStore createCloud() {
        return new CloudFileDataStore(goContext);
    }
}
