/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package pe.com.sedapal.ofivirtual.presenter.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
public interface LoadDataView {
    /**
     * Show a view with a progress bar indicating a loading process.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void showLoading(String descripcion);

    /**
     * Hide a loading view.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void hideLoading();

    /**
     * Show a retry view in case of an error when retrieving data.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void showRetry();

    /**
     * Show a retry view in case of an error when retrieving data.
     *
     * @param message A string representing an error.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void hideRetry();

    void showError(String psMessage);


    /**
     * Get a {@link android.content.Context}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    Context context();

    void onSessionExpired(String psMessage);
}
