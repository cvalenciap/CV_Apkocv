package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.SupplyLoginEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.LoginSupply;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Created by jsaenz on 7/02/2017.
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class SupplyLoginEntityMapper {

    /**
     * Constructs a {@link SupplyLoginEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    SupplyLoginEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poSupplyEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public LoginSupply mapToEntity(SupplyLoginEntity poSupplyEntity) {
        LoginSupply loLoginSupply = null;

        if (poSupplyEntity != null) {
            loLoginSupply = ParseUtil.parseObject(poSupplyEntity, LoginSupply.class);

        }
        return loLoginSupply;
    }

    public List<LoginSupply> mapToEntity(List<SupplyLoginEntity> poSupplyLoginEntity) {

        final List<LoginSupply> loDocumentType;

        if (poSupplyLoginEntity != null && !poSupplyLoginEntity.isEmpty()) {
            loDocumentType = new ArrayList<>(poSupplyLoginEntity.size());
            for (SupplyLoginEntity loSupplyTypeEntity : poSupplyLoginEntity) {
                final LoginSupply loLoginSupplyDto = mapToEntity(loSupplyTypeEntity);
                if (loLoginSupplyDto != null) {
                    loDocumentType.add(loLoginSupplyDto);
                }
            }
        } else {
            loDocumentType = Collections.emptyList();
        }

        return loDocumentType;
    }


}
