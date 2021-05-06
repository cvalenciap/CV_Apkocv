package pe.com.sedapal.ofivirtual.data.repository.datasource;

import com.google.gson.Gson;

import io.realm.RealmResults;
import pe.com.sedapal.ofivirtual.data.db.LocalBase;
import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.ObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestObtainDataUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestSupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.SessionUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.SessionUserDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.SessionUserDtoMapper;
import pe.com.sedapal.ofivirtual.data.entity.dto.mapper.UserDtoMapper;
import pe.com.sedapal.ofivirtual.data.net.CallbackDataStore;
import pe.com.sedapal.ofivirtual.data.preference.PreferenceBase;

/**
 * Created by Hernan Pareja on 7/02/2017.
 */

public class LocalUserDataStore extends LocalBase implements UserDataStore {

    private final UserDtoMapper goUserDtoMapper;
    private final SessionUserDtoMapper goSessionUserDtoMapper;

    /**
     * Constructs a {@link LocalBase}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public LocalUserDataStore() {
        super();

        this.goUserDtoMapper = new UserDtoMapper();
        this.goSessionUserDtoMapper = new SessionUserDtoMapper();
    }

    @Override
    public SessionUserEntity obtainUserSession() {
        SessionUserEntity lsSessionUserEntity;
        RealmResults<SessionUserDto> loResult = getRealm().where(SessionUserDto.class).findAll();
        if(loResult != null){
            if(loResult.isEmpty()) {
                lsSessionUserEntity = new SessionUserEntity();
            }else {
                lsSessionUserEntity = goSessionUserDtoMapper.mapFromDto(loResult).get(0);
            }
        }else{
            lsSessionUserEntity = new SessionUserEntity();
        }

        return lsSessionUserEntity;
    }

    @Override
    public void saveUserSession(RequestNewUserLoginEntity poRequestNewUserLoginEntity) {
        if (poRequestNewUserLoginEntity != null) {
            SessionUserDto loSessionUserDto = goSessionUserDtoMapper.mapToDto(poRequestNewUserLoginEntity);
            RealmResults<SessionUserDto> loResult = getRealm().where(SessionUserDto.class).findAll();
            getRealm().beginTransaction();
            loResult.deleteAllFromRealm();
            getRealm().insert(loSessionUserDto);
            getRealm().commitTransaction();
        }
    }

    @Override
    public Boolean deleteUserSession() {
       try{
           RealmResults<SessionUserDto> loResult = getRealm().where(SessionUserDto.class).findAll();
           getRealm().beginTransaction();
           loResult.deleteAllFromRealm();
           getRealm().commitTransaction();

           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public void loginNewUser(CallbackDataStore<NewUserLoginEntity> poCallback, String token, RequestNewUserLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loginSupply(CallbackDataStore<SupplyLoginEntity> poCallback, String poToken, RequestSupplyLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dataUser(CallbackDataStore<ObtainDataUserLoginEntity> poCallback, String poToken, RequestObtainDataUserLoginEntity poRequest) {
        throw new UnsupportedOperationException();
    }
}
