package pe.com.sedapal.ofivirtual.data.repository.datasource;

import android.content.Context;

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
 * DataStore el cual eprmite almacenar los parametros de sesion en shared preferences
 */
public class LocalUserSPDataStore extends PreferenceBase implements UserDataStore {
    public static String KEY_USER_SESSION_SP = "KEY_USER_SESSION_SP";
    private final UserDtoMapper goUserDtoMapper;
    private final SessionUserDtoMapper goSessionUserDtoMapper;

    /**
     * Constructs a {@link LocalBase}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    public LocalUserSPDataStore(Context context) {
        super(context);
        this.goUserDtoMapper = new UserDtoMapper();
        this.goSessionUserDtoMapper = new SessionUserDtoMapper();
    }

    @Override
    public SessionUserEntity obtainUserSession() {
        SessionUserEntity lsSessionUserEntity;

        SessionUserDto loResult =  new Gson().fromJson(getPreference(KEY_USER_SESSION_SP),SessionUserDto.class);

        if(loResult != null){
            lsSessionUserEntity = goSessionUserDtoMapper.mapFromDto(loResult);
        }else{
            lsSessionUserEntity = new SessionUserEntity();
        }

        return lsSessionUserEntity;
    }

    @Override
    public void saveUserSession(RequestNewUserLoginEntity poRequestNewUserLoginEntity) {
        if (poRequestNewUserLoginEntity != null) {
            SessionUserDto loSessionUserDto = goSessionUserDtoMapper.mapToDto(poRequestNewUserLoginEntity);

            String userSession = new Gson().toJson(loSessionUserDto);
            saveOnSharePreferences(KEY_USER_SESSION_SP,userSession);
        }
    }

    @Override
    public Boolean deleteUserSession() {
       try{

           SessionUserDto loSessionUserDto = goSessionUserDtoMapper.mapToDto(new RequestNewUserLoginEntity());
           String userSession = new Gson().toJson(loSessionUserDto);
           saveOnSharePreferences(KEY_USER_SESSION_SP,userSession);

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
