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

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pe.com.sedapal.ofivirtual.AndroidApplication;
import pe.com.sedapal.ofivirtual.UIThread;
import pe.com.sedapal.ofivirtual.data.executor.JobExecutor;
import pe.com.sedapal.ofivirtual.data.repository.EnchufateDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.FileDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.ForgetPasswordDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.IncidentsDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.InsertarPagoMovilDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.InvoicesDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.MasterDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.NisDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.PayPlaceDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.TokenDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.UserDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.ValidDocumentTypeDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.ValidPayRefDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.ValidSupplyDataRepository;
import pe.com.sedapal.ofivirtual.data.repository.VersionDataRepository;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
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
 * Dagger module that provides geographic location related collaborators.
 * <p>
 * Created by jsaenz on 10/01/2017.
 */
@Module
public class ApplicationModule {
    private final Application goApplication;

    /**
     * Constructs a {@link ApplicationModule    }.
     *
     * @param poAndroidApplication {@link AndroidApplication}.
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public ApplicationModule(Application poAndroidApplication) {
        this.goApplication = poAndroidApplication;
    }

    public Application getAndroidApplication() {
        return this.goApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.goApplication;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor poJobExecutor) {
        return poJobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread poUIThread) {
        return poUIThread;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository poUserDataRepository) {
        return poUserDataRepository;
    }

    @Provides
    @Singleton
    MasterRepository provideMasterRepository(MasterDataRepository poMasterDataRepository) {
        return poMasterDataRepository;
    }

    @Provides
    @Singleton
    TokenRepository provideTokenRepository(TokenDataRepository poTokenDataRepository) {
        return poTokenDataRepository;
    }

    @Provides
    @Singleton
    ValidSupplyRepository provideValidSuppplyRepository(ValidSupplyDataRepository poValidSupplyDataRepository) {
        return poValidSupplyDataRepository;
    }

    @Provides
    @Singleton
    ValidPayRefRepository provideValidPayRefRepository(ValidPayRefDataRepository poValidPayRefDataRepository) {
        return poValidPayRefDataRepository;
    }

    @Provides
    @Singleton
    ValidDocumentTypeRepository provideValidDocumentTypeRepository(ValidDocumentTypeDataRepository poValidDocumentTypeRepository) {
        return poValidDocumentTypeRepository;
    }

    @Provides
    @Singleton
    ForgetPasswordRepository provideForgetPasswordRepository(ForgetPasswordDataRepository poForgetPasswordDataRepository) {
        return poForgetPasswordDataRepository;
    }

    @Provides
    @Singleton
    InsertarPagoMovilRepository providePagoVisaEnviarCorreoRepository(InsertarPagoMovilDataRepository poInsertarPagoMovilDataRepository) {
        return poInsertarPagoMovilDataRepository;
    }

    @Provides
    @Singleton
    InvoicesRepository provideInvoicesRepository(InvoicesDataRepository poInvoicesDataRepository) {
        return poInvoicesDataRepository;
    }

    @Provides
    @Singleton
    NisRepository provideNisRepository(NisDataRepository poNisDataRepository) {
        return poNisDataRepository;
    }

    @Provides
    @Singleton
    FileRepository provideFileRepository(FileDataRepository poFileDataRepository) {
        return poFileDataRepository;
    }

    @Provides
    @Singleton
    PayPlaceRepository providePayPlaceRepository(PayPlaceDataRepository poPayPlaceDataRepository) {
        return poPayPlaceDataRepository;
    }

    @Provides
    @Singleton
    IncidentsRepository provideIncidentsRepository(IncidentsDataRepository poIncidentsDataRepository) {
        return poIncidentsDataRepository;
    }

    @Provides
    @Singleton
    EnchufateRepository provideEnchufateRepository(EnchufateDataRepository poEnchufateDataRepository) {
        return poEnchufateDataRepository;
    }

    @Provides
    @Singleton
    VersionRepository provideVersionRepository(VersionDataRepository poVersionDataRepository) {
        return poVersionDataRepository;
    }
}
