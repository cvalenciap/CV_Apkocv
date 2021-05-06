package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.SecretQuestionEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.SecretQuestion;
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
public class SecretQuestionEntityMapper {

    /**
     * Constructs a {@link SecretQuestionEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    SecretQuestionEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poSecretQuestionEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public SecretQuestion mapToEntity(SecretQuestionEntity poSecretQuestionEntity) {
        SecretQuestion loSecretQuestion = null;

        if (poSecretQuestionEntity != null) {
            loSecretQuestion = ParseUtil.parseObject(poSecretQuestionEntity, SecretQuestion.class);

        }
        return loSecretQuestion;
    }

    public List<SecretQuestion> mapToEntity(List<SecretQuestionEntity> poListDocumentTypeEntity) {

        final List<SecretQuestion> loDocumentType;

        if (poListDocumentTypeEntity != null && !poListDocumentTypeEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poListDocumentTypeEntity.size());
            for (SecretQuestionEntity loSecretQuestionTypeEntity : poListDocumentTypeEntity) {
                final SecretQuestion loSecretQuestionDto = mapToEntity(loSecretQuestionTypeEntity);
                if (loSecretQuestionDto != null) {
                    loDocumentType.add(loSecretQuestionDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
