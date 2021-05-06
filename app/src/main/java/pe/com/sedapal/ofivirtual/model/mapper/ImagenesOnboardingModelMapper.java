package pe.com.sedapal.ofivirtual.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ImagenesOnboarding;
import pe.com.sedapal.ofivirtual.model.ImagenesOnboardingModel;

public class ImagenesOnboardingModelMapper {

    @Inject
    public ImagenesOnboardingModelMapper() {
    }

    public List<ImagenesOnboardingModel> transform(List<ImagenesOnboarding> paoImagenesOnboarding){
        List<ImagenesOnboardingModel> laoImagenesOnboardingModel = null;
        if(paoImagenesOnboarding != null && !paoImagenesOnboarding.isEmpty()){
            laoImagenesOnboardingModel = new ArrayList<>(paoImagenesOnboarding.size());
            for(ImagenesOnboarding loImagenesOnboarding : paoImagenesOnboarding){
                final ImagenesOnboardingModel loImagenesOnboardingModel = transform(loImagenesOnboarding);
                if(loImagenesOnboardingModel != null){
                    laoImagenesOnboardingModel.add(loImagenesOnboardingModel);
                }
            }
        }
        return laoImagenesOnboardingModel;
    }


    public ImagenesOnboardingModel transform(ImagenesOnboarding poImagenesOnboarding) {

        ImagenesOnboardingModel loImagenesOnboardingModel = null;
        if (poImagenesOnboarding != null) {
            loImagenesOnboardingModel = ParseUtil.parseObject(poImagenesOnboarding, ImagenesOnboardingModel.class);
        }
        return loImagenesOnboardingModel;
    }

}
