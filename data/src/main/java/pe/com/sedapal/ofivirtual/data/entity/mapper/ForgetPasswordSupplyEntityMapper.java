package pe.com.sedapal.ofivirtual.data.entity.mapper;

import pe.com.sedapal.ofivirtual.data.entity.ForgetPasswordSupplyEntity;
import pe.com.sedapal.ofivirtual.data.entity.RequestForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ForgetPasswordSupply;
import pe.com.sedapal.ofivirtual.domain.entity.ReqForgetPasswordSupply;
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
public class ForgetPasswordSupplyEntityMapper {

    /**
     * Constructs a {@link ForgetPasswordSupplyEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ForgetPasswordSupplyEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poForgetPasswordSupplyEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ForgetPasswordSupply mapToEntity(ForgetPasswordSupplyEntity poForgetPasswordSupplyEntity) {
        ForgetPasswordSupply loForgetPasswordSupply = null;

        if (poForgetPasswordSupplyEntity != null) {
            loForgetPasswordSupply = ParseUtil.parseObject(poForgetPasswordSupplyEntity, ForgetPasswordSupply.class);

        }
        return loForgetPasswordSupply;
    }

    public RequestForgetPasswordSupply mapToEntity(ReqForgetPasswordSupply poReqForgetPasswordSupply) {
        RequestForgetPasswordSupply loRequestForgetPasswordSupply = null;

        if (poReqForgetPasswordSupply != null) {
            loRequestForgetPasswordSupply = ParseUtil.parseObject(poReqForgetPasswordSupply, RequestForgetPasswordSupply.class);

        }
        return loRequestForgetPasswordSupply;
    }


}
