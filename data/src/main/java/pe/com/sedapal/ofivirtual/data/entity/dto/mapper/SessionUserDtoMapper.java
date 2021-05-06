package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.NewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestNewUserLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.SessionUserEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.SessionUserDto;
import pe.com.sedapal.ofivirtual.data.entity.dto.UserDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;


/**
 * Created by jsaenz on 24/04/19
 * <p>
 * Mapper class used to mapFromDto {@link UserDto} (in the data database) to {@link UserEntity} in the
 * data layer.
 */
@Singleton
public class SessionUserDtoMapper {

    /**
     * Constructs a {@link SessionUserDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    @Inject
    public SessionUserDtoMapper() {
    }


    /**
     * Transform a {@link UserDto} into an {@link UserEntity}.
     *
     * @param poSessionUserDto Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    public SessionUserEntity mapFromDto(SessionUserDto poSessionUserDto) {
        SessionUserEntity loSessionUserEntity = null;

        if (poSessionUserDto != null) {
            loSessionUserEntity = ParseUtil.parseObject(poSessionUserDto, SessionUserEntity.class);
        }
        return loSessionUserEntity;
    }

    /**
     * Transform a {@link UserDto} into an {@link UserEntity}.
     *
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    public List<SessionUserEntity> mapFromDto(List<SessionUserDto> paoSessionUserDto) {
        final List<SessionUserEntity> laoSessionUserEntity;

        if (paoSessionUserDto != null && !paoSessionUserDto.isEmpty()) {
            laoSessionUserEntity = new ArrayList<>(paoSessionUserDto.size());
            for (SessionUserDto loUserDto : paoSessionUserDto) {
                final SessionUserEntity loSessionUserEntity = mapFromDto(loUserDto);
                if (loSessionUserEntity != null) {
                    laoSessionUserEntity.add(loSessionUserEntity);
                }
            }
        } else {
            laoSessionUserEntity = Collections.emptyList();
        }

        return laoSessionUserEntity;
    }


    /**
     * Transform a {@link UserEntity} into an {@link UserDto}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link UserDto}.
     * @author jsaenz
     * @version 1.0
     * @since 24/04/19
     */
    public SessionUserDto mapToDto(RequestNewUserLoginEntity poUserEntity) {
        SessionUserDto loUserDto = null;

        if (poUserEntity != null) {
            loUserDto = ParseUtil.parseObject(poUserEntity, SessionUserDto.class);
        }
        return loUserDto;
    }
}
