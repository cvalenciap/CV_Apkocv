package pe.com.sedapal.ofivirtual.data.entity.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.sedapal.ofivirtual.data.entity.ImagenesOnboardingEntity;
import pe.com.sedapal.ofivirtual.data.entity.UserEntity;
import pe.com.sedapal.ofivirtual.data.util.ParseUtil;
import pe.com.sedapal.ofivirtual.domain.entity.ImagenesOnboarding;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * <p>
 * Mapper class used to mapFromDto {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
@Singleton
public class ImagenesOnboardingEntityMapper {

    /**
     * Constructs a {@link ImagenesOnboardingEntityMapper}.
     *
     * @author Hernan Pareja
     * @version 1.0
     * @since 7/02/2017
     */
    @Inject
    ImagenesOnboardingEntityMapper() {
    }


    /**
     * Transform a {@link UserEntity} into an {@link User}.
     *
     * @param poImagenesOnboardingEntity Object to be transformed.
     * @return {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 7/02/2017
     */
    public ImagenesOnboarding mapToEntity(ImagenesOnboardingEntity poImagenesOnboardingEntity) {
        ImagenesOnboarding loImagenesOnboarding = null;

        if (poImagenesOnboardingEntity != null) {
            loImagenesOnboarding = ParseUtil.parseObject(poImagenesOnboardingEntity, ImagenesOnboarding.class);

        }
        return loImagenesOnboarding;
    }

    public List<ImagenesOnboarding> mapToEntity(List<ImagenesOnboardingEntity> poListImagenesOnboardingEntity) {

        final List<ImagenesOnboarding> loImagenesOnboarding;

        if (poListImagenesOnboardingEntity != null && !poListImagenesOnboardingEntity.isEmpty()) {
            loImagenesOnboarding = new ArrayList<>(poListImagenesOnboardingEntity.size());
            for (ImagenesOnboardingEntity loImagenesOnboardingEntity : poListImagenesOnboardingEntity) {
                final ImagenesOnboarding loImagenesOnboardingDto = mapToEntity(loImagenesOnboardingEntity);
                if (loImagenesOnboardingDto != null) {
                    loImagenesOnboarding.add(loImagenesOnboardingDto);
                }
            }
        } else {
            loImagenesOnboarding = Collections.emptyList();
        }

        return loImagenesOnboarding;
    }


}
