/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pe.com.sedapal.ofivirtual.di.module;

import android.view.View;
import dagger.Module;
import dagger.Provides;
import pe.com.sedapal.ofivirtual.di.PerView;

/**
 * A module to wrap the VIew state and expose it to the graph.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
@Module
public class ViewModule {
    private final View goView;

    /**
     * Constructs a {@link ViewModule}.
     *
     * @param poView {@link View}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public ViewModule(View poView) {
        this.goView = poView;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerView
    View view() {
        return this.goView;
    }
}
