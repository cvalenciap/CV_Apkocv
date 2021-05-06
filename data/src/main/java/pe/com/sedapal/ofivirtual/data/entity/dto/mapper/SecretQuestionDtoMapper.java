package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import pe.com.sedapal.ofivirtual.data.entity.SecretQuestionEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.SecretQuestionDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by jsaenz on 13/01/2017.
 * <p>
 * Mapper class used to mapFromDto {@link SecretQuestionDto} (in the data database) to {@link SecretQuestionEntity} in the
 * data layer.
 */
@Singleton
public class SecretQuestionDtoMapper {

    /**
     * Constructs a {@link SecretQuestionDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public SecretQuestionDtoMapper() {
    }


    /**
     * Transform a {@link SecretQuestionDto} into an {@link SecretQuestionEntity}.
     *
     * @param poSecretQuestionDto Object to be transformed.
     * @return {@link SecretQuestionEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public SecretQuestionEntity mapFromDto(SecretQuestionDto poSecretQuestionDto) {
        SecretQuestionEntity loSecretQuestionEntity = null;

        if (poSecretQuestionDto != null) {
            loSecretQuestionEntity = ParseUtil.parseObject(poSecretQuestionDto, SecretQuestionEntity.class);
        }
        return loSecretQuestionEntity;
    }

    /**
     * Transform a {@link SecretQuestionDto} into an {@link SecretQuestionEntity}.
     *
     * @param paoSecretQuestionDto List of Object to be transformed.
     * @return {@link SecretQuestionEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<SecretQuestionEntity> mapFromDto(List<SecretQuestionDto> paoSecretQuestionDto) {
        final List<SecretQuestionEntity> laoSecretQuestionEntity;

        if (paoSecretQuestionDto != null && !paoSecretQuestionDto.isEmpty()) {
            laoSecretQuestionEntity = new ArrayList<>(paoSecretQuestionDto.size());
            for (SecretQuestionDto loSecretQuestionDto : paoSecretQuestionDto) {
                final SecretQuestionEntity loSecretQuestionEntity = mapFromDto(loSecretQuestionDto);
                if (loSecretQuestionEntity != null) {
                    laoSecretQuestionEntity.add(loSecretQuestionEntity);
                }
            }
        } else {
            laoSecretQuestionEntity = Collections.emptyList();
        }

        return laoSecretQuestionEntity;
    }


    /**
     * Transform a {@link SecretQuestionEntity} into an {@link SecretQuestionDto}.
     *
     * @param poSecretQuestionEntity Object to be transformed.
     * @return {@link SecretQuestionDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public SecretQuestionDto mapToDto(SecretQuestionEntity poSecretQuestionEntity) {
        SecretQuestionDto loSecretQuestionDto = null;

        if (poSecretQuestionEntity != null) {
            loSecretQuestionDto = ParseUtil.parseObject(poSecretQuestionEntity, SecretQuestionDto.class);
        }
        return loSecretQuestionDto;
    }

    /**
     * Transform a {@link SecretQuestionEntity} into an {@link SecretQuestionDto}.
     *
     * @param paoSecretQuestionEntity List of Object to be transformed.
     * @return {@link SecretQuestionDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<SecretQuestionDto> mapToDto(List<SecretQuestionEntity> paoSecretQuestionEntity) {
        final List<SecretQuestionDto> laoSecretQuestionDto;

        if (paoSecretQuestionEntity != null && !paoSecretQuestionEntity.isEmpty()) {
            laoSecretQuestionDto = new ArrayList<>(paoSecretQuestionEntity.size());
            for (SecretQuestionEntity loSecretQuestionEntity : paoSecretQuestionEntity) {
                final SecretQuestionDto loSecretQuestionDto = mapToDto(loSecretQuestionEntity);
                if (loSecretQuestionDto != null) {
                    laoSecretQuestionDto.add(loSecretQuestionDto);
                }
            }
        } else {
            laoSecretQuestionDto = Collections.emptyList();
        }

        return laoSecretQuestionDto;
    }
}
