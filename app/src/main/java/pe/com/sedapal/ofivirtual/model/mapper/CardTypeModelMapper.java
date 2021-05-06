package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.CardType;
import pe.com.sedapal.ofivirtual.model.CardTypeModel;

public class CardTypeModelMapper {

    @Inject
    public CardTypeModelMapper() {
    }

    public List<CardTypeModel> transform(List<CardType> paoCardType){
        List<CardTypeModel> laoCardTypeModel = null;
        if(paoCardType != null && !paoCardType.isEmpty()){
            laoCardTypeModel = new ArrayList<>(paoCardType.size());
            for(CardType loCardType : paoCardType){
                final CardTypeModel loCardTypeModel = transform(loCardType);
                if(loCardTypeModel != null){
                    laoCardTypeModel.add(loCardTypeModel);
                }
            }
        }
        return laoCardTypeModel;
    }


    public CardTypeModel transform(CardType poCardType) {

        CardTypeModel loCardTypeModel = null;
        if (poCardType != null) {
            loCardTypeModel = ParseUtil.parseObject(poCardType, CardTypeModel.class);
        }
        return loCardTypeModel;
    }

}
