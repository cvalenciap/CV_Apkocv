package pe.com.sedapal.ofivirtual.data.db;

import io.realm.Realm;

/**
 * Created by jsaenz on 13/01/2017.
 */
public class LocalBase {

    Realm goRealm;

    /**
     * Constructs a {@link LocalBase}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public LocalBase() {
//        Activity loApplication = (Activity) goContext;
        this.goRealm = Realm.getDefaultInstance();
    }

    /**
     * Get a {@link Realm}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public Realm getRealm() {
        return goRealm;
    }
}
