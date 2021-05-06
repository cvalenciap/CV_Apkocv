package pe.com.sedapal.ofivirtual.model.mapper;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.NisDetail;
import pe.com.sedapal.ofivirtual.model.NisDetailModel;

public class NisDetailModelMapper {

    @Inject
    public NisDetailModelMapper() {
    }

    public NisDetailModel transform(NisDetail poNisDetail) {

        NisDetailModel loNisDetailModel = null;
        if (poNisDetail != null) {
            loNisDetailModel = ParseUtil.parseObject(poNisDetail, NisDetailModel.class);
        }
        return loNisDetailModel;
    }
}
