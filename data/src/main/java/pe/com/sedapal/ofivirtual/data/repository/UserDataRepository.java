package pe.com.sedapal.ofivirtual.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSendConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestValidateConfirmationCodeEntity;
import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.SessionUserDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.NewUserLoginEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.ObtainDataUserLoginEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.RegUserEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.SessionUserEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.SupplyLoginEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UpdateDataUserEntityMapper;
import pe.com.sedapal.ofivirtual.data.entity.mapper.UserEntityMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.RegUserDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.RegUserDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.TokenDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UpdateDataUserDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UpdateDataUserDataStoreFactory;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UserDataStore;
import pe.com.sedapal.ofivirtual.data.repository.datasource.UserDataStoreFactory;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.domain.entity.ObtainDataUserLogin;
import pe.com.sedapal.ofivirtual.domain.entity.RegisterUser;
import pe.com.sedapal.ofivirtual.domain.entity.SessionUser;
import pe.com.sedapal.ofivirtual.domain.entity.UpdateDataUser;
import pe.com.sedapal.ofivirtual.domain.repository.UserRepository;

/**
 * Created by Hernan Pareja on 5/01/2017.
 * </p>
 * {@link UserRepository} for retrieving User data.
 */
@Singleton
public class UserDataRepository implements UserRepository {
    private final static String DATE_TOKEN_SYNC = "DATE_TOKEN_SYNC";
    private static String TOKEN_SYNC;

    private final RegUserDataStoreFactory goRegUserDataStoreFactory;
    private final TokenDataStoreFactory goTokenDataStoreFactory;
    private final UpdateDataUserDataStoreFactory goUpdateDataUserDataStoreFactory;
    private final UserDataStoreFactory goUserDataStoreFactory;
    private final UserEntityMapper goUserEntityMapper;
    private final RegUserEntityMapper goRegUserEntityMapper;
    private final NewUserLoginEntityMapper goNewUserLoginEntityMapper;
    private final SupplyLoginEntityMapper goSupplyLoginEntityMapper;
    private final UpdateDataUserEntityMapper goUpdateDataUserEntityMapper;
    private final ObtainDataUserLoginEntityMapper goObtainDataUserLoginEntityMapper;
    private final SessionUserDtoMapper goSessionUserDtoMapper;
    private final SessionUserEntityMapper goSessionUserEntityMapper;


    /**
     * Constructs a {@link UserDataRepository}.
     *
     * @param poUserDataStoreFactory {@link UserDataStoreFactory} A factory to construct different data source implementations.
     * @param poUserEntityMapper     {@link UserEntityMapper}.
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    UserDataRepository(UserDataStoreFactory poUserDataStoreFactory,
                       UserEntityMapper poUserEntityMapper,
                       RegUserDataStoreFactory poRegUserDataStoreFactory,
                       RegUserEntityMapper poRegUserEntityMapper,
                       TokenDataStoreFactory poTokenDataStoreFactory,
                       NewUserLoginEntityMapper poNewUserLoginEntityMapper,
                       SupplyLoginEntityMapper poSupplyLoginEntityMapper,
                       UpdateDataUserEntityMapper poUpdateDataUserEntityMapper,
                       UpdateDataUserDataStoreFactory poUpdateDataUserDataStoreFactory,
                       ObtainDataUserLoginEntityMapper poObtainDataUserLoginEntityMapper,
                       SessionUserDtoMapper poSessionUserDtoMapper,
                       SessionUserEntityMapper poSessionUserEntityMapper) {
        this.goUserDataStoreFactory = poUserDataStoreFactory;
        this.goUserEntityMapper = poUserEntityMapper;
        this.goRegUserDataStoreFactory = poRegUserDataStoreFactory;
        this.goTokenDataStoreFactory = poTokenDataStoreFactory;
        this.goRegUserEntityMapper = poRegUserEntityMapper;
        this.goNewUserLoginEntityMapper = poNewUserLoginEntityMapper;
        this.goSupplyLoginEntityMapper = poSupplyLoginEntityMapper;
        this.goUpdateDataUserDataStoreFactory = poUpdateDataUserDataStoreFactory;
        this.goUpdateDataUserEntityMapper = poUpdateDataUserEntityMapper;
        this.goObtainDataUserLoginEntityMapper = poObtainDataUserLoginEntityMapper;
        this.goSessionUserDtoMapper = poSessionUserDtoMapper;
        this.goSessionUserEntityMapper = poSessionUserEntityMapper;
    }


    /**
     * Validate user login
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */

    @Override
    public Observable<String> register(RegisterUser psRegisterUser) {
        return Observable.create(loEmitter->{
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final RegUserDataStore loRegUserDataStore = goRegUserDataStoreFactory.createCloud();
            loRegUserDataStore.register(new CallbackDataStore<String>() {
                @Override
                public void onSuccess(String poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(poData);
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            },TOKEN_SYNC,goRegUserEntityMapper.mapToEntity(psRegisterUser));
        });
    }

    @Override
    public Observable<SessionUser> obtainSessionUser() {
        return Observable.create(loEmitter-> {
            SessionUser loSessionUser;
            UserDataStore loUserDataStore = goUserDataStoreFactory.createLocal();
            loSessionUser = goSessionUserEntityMapper.mapToEntity(loUserDataStore.obtainUserSession());
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(loSessionUser);
                loEmitter.onComplete();
            }
        });
    }

    @Override
    public Observable<Boolean> deleteSessionUser() {
        return Observable.create(loEmitter-> {
            boolean isDeleted;
            UserDataStore loUserDataStore = goUserDataStoreFactory.createLocal();
            isDeleted = loUserDataStore.deleteUserSession();
            if (!loEmitter.isDisposed()) {
                loEmitter.onNext(isDeleted);
                loEmitter.onComplete();
            }
        });
    }

    RequestNewUserLoginEntity loRequestNewUserLoginEntity;

    @Override
    public Observable<LoginNewUser> loginNewUser(String psemail, String psPassword) {
        loRequestNewUserLoginEntity = new RequestNewUserLoginEntity();
        loRequestNewUserLoginEntity.setEmail(psemail);
        loRequestNewUserLoginEntity.setPassword(psPassword);
        loRequestNewUserLoginEntity.setFlag("2");

        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final UserDataStore loUserDataStore = goUserDataStoreFactory.createCloudNewUser();
            loUserDataStore.loginNewUser(new CallbackDataStore<NewUserLoginEntity>() {
                @Override
                public void onSuccess(NewUserLoginEntity poData) {
                    if (!loEmitter.isDisposed()) {

                        //Cuando el usuario tiene pendiente confirmar su registro este no se almacena
                        //en la base de datos del dispositivo para una futura sesión.
                        //La sesión para los usuarios pendientes de registro se almacenará luego
                        //que sea correcta su confirmación

                        if (!poData.getPendingConfirmRegister()) {
                            UserDataStore loUserDataStore = goUserDataStoreFactory.createLocal();
                            loUserDataStore.saveUserSession(loRequestNewUserLoginEntity);
                        }

                        loEmitter.onNext(goNewUserLoginEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequestNewUserLoginEntity);

        });
    }

    @Override
    public Observable<LoginSupply> loginSupply(long psNisRad, String psPassword) {
        RequestSupplyLoginEntity loRequestSupplyLoginEntity = new RequestSupplyLoginEntity();
        loRequestSupplyLoginEntity.setNisRad(psNisRad);
        loRequestSupplyLoginEntity.setPassword(psPassword);

        return Observable.create(loEmitter-> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final UserDataStore loUserDataStore = goUserDataStoreFactory.createCloudSupply();
            loUserDataStore.loginSupply(new CallbackDataStore<SupplyLoginEntity>() {
                @Override
                public void onSuccess(SupplyLoginEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goSupplyLoginEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequestSupplyLoginEntity);
        });
    }

    @Override
    public Observable<String> updateDataUser(UpdateDataUser psUpdateDataUser) {
        return Observable.create(loEmitter->{
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final UpdateDataUserDataStore loUpdateDataUserDataStoreFactory = goUpdateDataUserDataStoreFactory.createCloud();

            loUpdateDataUserDataStoreFactory.update(new CallbackDataStore<String>() {
                @Override
                public void onSuccess(String poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(poData);
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }

            },TOKEN_SYNC,goUpdateDataUserEntityMapper.mapToEntity(psUpdateDataUser));
        });
    }

    @Override
    public Observable<ObtainDataUserLogin> obtainDataUser(long idCliente) {
        RequestObtainDataUserLoginEntity loRequestObtainDataUserLoginEntity = new RequestObtainDataUserLoginEntity();
        loRequestObtainDataUserLoginEntity.setIdCliente(idCliente);

        return Observable.create(loEmitter-> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final UserDataStore loUserDataStore = goUserDataStoreFactory.createCloudObtainDataUser();

            loUserDataStore.dataUser(new CallbackDataStore<ObtainDataUserLoginEntity>() {
                @Override
                public void onSuccess(ObtainDataUserLoginEntity poData) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onNext(goObtainDataUserLoginEntityMapper.mapToEntity(poData));
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequestObtainDataUserLoginEntity);
        });
    }

    @Override
    public Observable<String> validateConfirmationCode(String psEmailUser, String psCodeVerify) {
        RequestValidateConfirmationCodeEntity loRequest = new RequestValidateConfirmationCodeEntity();
        loRequest.setCorreo(psEmailUser);
        loRequest.setCodeVerify(psCodeVerify);

        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final RegUserDataStore loRegUserDataStore = goRegUserDataStoreFactory.createCloud();
            loRegUserDataStore.validateConfirmationCode(new CallbackDataStore<String>() {
                @Override
                public void onSuccess(String poData) {
                    if (!loEmitter.isDisposed()) {

                        //Cuando el usuario confirma su registro este se almacena
                        //en la base de datos del dispositivo para una futura sesión.

                        UserDataStore loUserDataStore = goUserDataStoreFactory.createLocal();
                        loUserDataStore.saveUserSession(loRequestNewUserLoginEntity);

                        loEmitter.onNext(poData);
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequest);
        });
    }

    @Override
    public Observable<String> sendConfirmationCode(String psEmailUser) {
        RequestSendConfirmationCodeEntity loRequest = new RequestSendConfirmationCodeEntity();
        loRequest.setCorreo(psEmailUser);

        return Observable.create(loEmitter -> {
            final TokenDataStore loTokenDataStore = goTokenDataStoreFactory.createLocal();
            TOKEN_SYNC = loTokenDataStore.getToken(DATE_TOKEN_SYNC);

            final RegUserDataStore loRegUserDataStore = goRegUserDataStoreFactory.createCloud();
            loRegUserDataStore.sendConfirmationCode(new CallbackDataStore<String>() {
                @Override
                public void onSuccess(String poData) {
                    if (!loEmitter.isDisposed()) {

                        loEmitter.onNext(poData);
                        loEmitter.onComplete();
                    }
                }

                @Override
                public void onError(Throwable poException) {
                    if (!loEmitter.isDisposed()) {
                        loEmitter.onError(poException);
                    }
                }
            }, TOKEN_SYNC, loRequest);
        });
    }
}
