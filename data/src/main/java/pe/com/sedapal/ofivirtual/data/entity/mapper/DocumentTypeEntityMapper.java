package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.DocumentTypeEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
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
public class DocumentTypeEntityMapper {

    /**
     * Constructs a {@link DocumentTypeEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    DocumentTypeEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poDocumentTypeEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public DocumentType mapToEntity(DocumentTypeEntity poDocumentTypeEntity) {
        DocumentType loDocumentType = null;

        if (poDocumentTypeEntity != null) {
            loDocumentType = ParseUtil.parseObject(poDocumentTypeEntity, DocumentType.class);

        }
        return loDocumentType;
    }

    public List<DocumentType> mapToEntity(List<DocumentTypeEntity> poListDocumentTypeEntity) {

        final List<DocumentType> loDocumentType;

        if (poListDocumentTypeEntity != null && !poListDocumentTypeEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poListDocumentTypeEntity.size());
            for (DocumentTypeEntity loDocumentTypeEntity : poListDocumentTypeEntity) {
                final DocumentType loDocumentTypeDto = mapToEntity(loDocumentTypeEntity);
                if (loDocumentTypeDto != null) {
                    loDocumentType.add(loDocumentTypeDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
