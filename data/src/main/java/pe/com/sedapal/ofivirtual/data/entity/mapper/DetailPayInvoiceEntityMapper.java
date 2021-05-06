package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.DetailPayInvoiceEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.DetailPayInvoice;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 06/12/2018.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class DetailPayInvoiceEntityMapper {

    /**
     * Constructs a {@link DetailPayInvoiceEntityMapper}.
     *
     * @author jsaenz
     * @version 1.0
     * @since 06/12/2018
     */
    @Inject
    DetailPayInvoiceEntityMapper() {
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
    public DetailPayInvoice mapToEntity(DetailPayInvoiceEntity poUserEntity) {
        DetailPayInvoice loUser = null;

        if (poUserEntity != null) {
            loUser = ParseUtil.parseObject(poUserEntity, DetailPayInvoice.class);

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
    public List<DetailPayInvoice> mapToEntity(List<DetailPayInvoiceEntity> poListDocumentTypeEntity) {

        final List<DetailPayInvoice> loDocumentType;

        if (poListDocumentTypeEntity != null && !poListDocumentTypeEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poListDocumentTypeEntity.size());
            for (DetailPayInvoiceEntity loDetailPayInvoiceTypeEntity : poListDocumentTypeEntity) {
                final DetailPayInvoice loDetailPayInvoiceDto = mapToEntity(loDetailPayInvoiceTypeEntity);
                if (loDetailPayInvoiceDto != null) {
                    loDocumentType.add(loDetailPayInvoiceDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
