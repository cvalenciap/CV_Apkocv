package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.DetailInvoicesEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DetailInvoices;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class DetailInvoicesEntityMapper {

    /**
     * Constructs a {@link DetailInvoicesEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    DetailInvoicesEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poUserEntity Object to be transformed.
     * @return {@link User}.
     * @author jsenz
     * @version 1.0
     * @since 07/12/2019
     */
    public DetailInvoices mapToEntity(DetailInvoicesEntity poUserEntity) {
        DetailInvoices loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, DetailInvoices.class);

        }
        return loUser;
    }

    /**
     * Transform a {@link User} into an {@link UserEntity}.
     *
     * @return {@link UserEntity}.
     * @author jsaenz
     * @version 1.0
     * @since 07/12/2019
     */
    public List<DetailInvoices> mapToEntity(List<DetailInvoicesEntity> poListDocumentTypeEntity) {

        final List<DetailInvoices> loDocumentType;

        if (poListDocumentTypeEntity != null && !poListDocumentTypeEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poListDocumentTypeEntity.size());
            for (DetailInvoicesEntity loDetailInvoicesTypeEntity : poListDocumentTypeEntity) {
                final DetailInvoices loDetailInvoicesDto = mapToEntity(loDetailInvoicesTypeEntity);
                if (loDetailInvoicesDto != null) {
                    loDocumentType.add(loDetailInvoicesDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
