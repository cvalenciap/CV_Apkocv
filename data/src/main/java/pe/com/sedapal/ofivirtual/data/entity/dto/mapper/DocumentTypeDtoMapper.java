package pe.com.sedapal.ofivirtual.data.entity.dto.mapper;

import pe.com.sedapal.ofivirtual.data.entity.DocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.dto.DocumentTypeDto;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by jsaenz on 13/01/2017.
 * <p>
 * Mapper class used to mapFromDto {@link DocumentTypeDto} (in the data database) to {@link DocumentTypeEntity} in the
 * data layer.
 */
@Singleton
public class DocumentTypeDtoMapper {

    /**
     * Constructs a {@link DocumentTypeDtoMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 10/01/2017
     */
    @Inject
    public DocumentTypeDtoMapper() {
    }


    /**
     * Transform a {@link DocumentTypeDto} into an {@link DocumentTypeEntity}.
     *
     * @param poDocumentTypeDto Object to be transformed.
     * @return {@link DocumentTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public DocumentTypeEntity mapFromDto(DocumentTypeDto poDocumentTypeDto) {
        DocumentTypeEntity loDocumentTypeEntity = null;

        if (poDocumentTypeDto != null) {
            loDocumentTypeEntity = ParseUtil.parseObject(poDocumentTypeDto, DocumentTypeEntity.class);
        }
        return loDocumentTypeEntity;
    }

    /**
     * Transform a {@link DocumentTypeDto} into an {@link DocumentTypeEntity}.
     *
     * @param paoDocumentTypeDto List of Object to be transformed.
     * @return {@link DocumentTypeEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<DocumentTypeEntity> mapFromDto(List<DocumentTypeDto> paoDocumentTypeDto) {
        final List<DocumentTypeEntity> laoDocumentTypeEntity;

        if (paoDocumentTypeDto != null && !paoDocumentTypeDto.isEmpty()) {
            laoDocumentTypeEntity = new ArrayList<>(paoDocumentTypeDto.size());
            for (DocumentTypeDto loDocumentTypeDto : paoDocumentTypeDto) {
                final DocumentTypeEntity loDocumentTypeEntity = mapFromDto(loDocumentTypeDto);
                if (loDocumentTypeEntity != null) {
                    laoDocumentTypeEntity.add(loDocumentTypeEntity);
                }
            }
        } else {
            laoDocumentTypeEntity = Collections.emptyList();
        }

        return laoDocumentTypeEntity;
    }


    /**
     * Transform a {@link DocumentTypeEntity} into an {@link DocumentTypeDto}.
     *
     * @param poDocumentTypeEntity Object to be transformed.
     * @return {@link DocumentTypeDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public DocumentTypeDto mapToDto(DocumentTypeEntity poDocumentTypeEntity) {
        DocumentTypeDto loDocumentTypeDto = null;

        if (poDocumentTypeEntity != null) {
            loDocumentTypeDto = ParseUtil.parseObject(poDocumentTypeEntity, DocumentTypeDto.class);
        }
        return loDocumentTypeDto;
    }

    /**
     * Transform a {@link DocumentTypeEntity} into an {@link DocumentTypeDto}.
     *
     * @param paoDocumentTypeEntity List of Object to be transformed.
     * @return {@link DocumentTypeDto}.
     * @author jsaenz
     * @version 1.0
     * @since 16/01/2017
     */
    public List<DocumentTypeDto> mapToDto(List<DocumentTypeEntity> paoDocumentTypeEntity) {
        final List<DocumentTypeDto> laoDocumentTypeDto;

        if (paoDocumentTypeEntity != null && !paoDocumentTypeEntity.isEmpty()) {
            laoDocumentTypeDto = new ArrayList<>(paoDocumentTypeEntity.size());
            for (DocumentTypeEntity loDocumentTypeEntity : paoDocumentTypeEntity) {
                final DocumentTypeDto loDocumentTypeDto = mapToDto(loDocumentTypeEntity);
                if (loDocumentTypeDto != null) {
                    laoDocumentTypeDto.add(loDocumentTypeDto);
                }
            }
        } else {
            laoDocumentTypeDto = Collections.emptyList();
        }

        return laoDocumentTypeDto;
    }
}
