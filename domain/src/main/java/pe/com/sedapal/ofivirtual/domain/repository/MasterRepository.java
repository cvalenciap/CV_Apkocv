package pe.com.sedapal.ofivirtual.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.ApplicantType;
import pe.com.sedapal.ofivirtual.domain.entity.CardType;
import pe.com.sedapal.ofivirtual.domain.entity.DatosImagenes;
import pe.com.sedapal.ofivirtual.domain.entity.DatosVisa;
import pe.com.sedapal.ofivirtual.domain.entity.District;
import pe.com.sedapal.ofivirtual.domain.entity.DocumentType;
import pe.com.sedapal.ofivirtual.domain.entity.ImagenesOnboarding;
import pe.com.sedapal.ofivirtual.domain.entity.SecretQuestion;
import pe.com.sedapal.ofivirtual.domain.entity.User;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 * <p>
 * Created by jsaenz on 7/02/2017.
 */

public interface MasterRepository {

    /**
     * Get an {@link Observable} which will emit a {@link User}.
     *
     * @return {@link Observable} which will emit a {@link User}.
     * @author jsaenz
     * @version 1.0
     * @since 12/2018
     */
    Observable<Void> sync();

    Observable<List<DocumentType>> getListDocumentType();

    Observable<List<District>> getListDistrict();

    Observable<List<ApplicantType>> getListApplicantType();

    Observable<List<SecretQuestion>> getListSecretQuestions();

    Observable<DatosVisa> getDatosVisa(String poCategoria);

    Observable<DatosImagenes> getDatosImagenes(String poCategoria);

    Observable<List<CardType>> getListCardType();

    Observable<List<ImagenesOnboarding>> getListImagenesOnboardingEntity();
}
