package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.UserDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by jsaenz on 13/01/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserDto} (in the data database) to {@link UserEntity} in the
 * data layer.
 */
@Singleton
public class UserDtoMapper {

    /**
     * Constructs a {@link UserDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public UserDtoMapper() {
    }


    /**
     * Transform a {@link UserDto} into an {@link UserEntity}.
     *
     * @param poUserDto Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public UserEntity mapFromDto(UserDto poUserDto) {
        UserEntity loUserEntity = null;

        if (poUserDto != null) {
            loUserEntity = ParseUtil.parseObject(poUserDto, UserEntity.class);
        }
        return loUserEntity;
    }

    /**
     * Transform a {@link UserDto} into an {@link UserEntity}.
     *
     * @param paoUserDto List of Object to be transformed.
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<UserEntity> mapFromDto(List<UserDto> paoUserDto) {
        final List<UserEntity> laoUserEntity;

        if (paoUserDto != null && !paoUserDto.isEmpty()) {
            laoUserEntity = new ArrayList<>(paoUserDto.size());
            for (UserDto loUserDto : paoUserDto) {
                final UserEntity loUserEntity = mapFromDto(loUserDto);
                if (loUserEntity != null) {
                    laoUserEntity.add(loUserEntity);
                }
            }
        } else {
            laoUserEntity = Collections.emptyList();
        }

        return laoUserEntity;
    }


    /**
     * Transform a {@link UserEntity} into an {@link UserDto}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link UserDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public UserDto mapToDto(UserEntity poUserEntity) {
        UserDto loUserDto = null;

        if (poUserEntity != null) {
            loUserDto = ParseUtil.parseObject(poUserEntity, UserDto.class);
        }
        return loUserDto;
    }

    /**
     * Transform a {@link UserEntity} into an {@link UserDto}.
     *
     * @param paoUserEntity List of Object to be transformed.
     * @return {@link UserDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<UserDto> mapToDto(List<UserEntity> paoUserEntity) {
        final List<UserDto> laoUserDto;

        if (paoUserEntity != null && !paoUserEntity.isEmpty()) {
            laoUserDto = new ArrayList<>(paoUserEntity.size());
            for (UserEntity loUserEntity : paoUserEntity) {
                final UserDto loUserDto = mapToDto(loUserEntity);
                if (loUserDto != null) {
                    laoUserDto.add(loUserDto);
                }
            }
        } else {
            laoUserDto = Collections.emptyList();
        }

        return laoUserDto;
    }
}
