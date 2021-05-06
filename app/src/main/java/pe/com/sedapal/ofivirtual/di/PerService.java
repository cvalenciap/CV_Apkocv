package pe.com.sedapal.ofivirtual.di;

import javax.inject.Scope;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the activity to be memorized in the
 * correct component.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
@Scope
@Retention(RUNTIME)
public @interface PerService {
}
