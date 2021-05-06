package pe.com.sedapal.ofivirtual.presenter;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
public interface Presenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    void destroy();

}
