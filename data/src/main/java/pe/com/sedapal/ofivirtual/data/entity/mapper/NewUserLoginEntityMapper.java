package pe.com.sedapal.ofivirtual.data.entity.mapper;
import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.LoginNewUser;
import pe.com.sedapal.ofivirtual.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class NewUserLoginEntityMapper {

    /**
     * Constructs a {@link NewUserLoginEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    NewUserLoginEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poNewUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public LoginNewUser mapToEntity(NewUserLoginEntity poNewUserEntity) {
        LoginNewUser loLoginNewUser = null;



        if (poNewUserEntity != null) {
            loLoginNewUser = ParseUtil.parseObject(poNewUserEntity, LoginNewUser.class);

        }
        //clb
        if(poNewUserEntity.getAdmin()==1 || poNewUserEntity.getAdminEtic()==1){
            loLoginNewUser.setAdmin(1);
        }




        return loLoginNewUser;
    }

    public List<LoginNewUser> mapToEntity(List<NewUserLoginEntity> poNewUserLoginEntity) {

        final List<LoginNewUser> loDocumentType;

        if (poNewUserLoginEntity != null && !poNewUserLoginEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poNewUserLoginEntity.size());
            for (NewUserLoginEntity loNewUserTypeEntity : poNewUserLoginEntity) {
                final LoginNewUser loLoginNewUserDto = mapToEntity(loNewUserTypeEntity);
                if (loLoginNewUserDto != null) {
                    loDocumentType.add(loLoginNewUserDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


    public NewUserLoginEntity mapToEntity(LoginNewUser poNewUser) {
        NewUserLoginEntity loLoginNewUser = null;

        if (poNewUser != null) {
            loLoginNewUser = ParseUtil.parseObject(poNewUser, NewUserLoginEntity.class);

        }
        return loLoginNewUser;
    }

}
