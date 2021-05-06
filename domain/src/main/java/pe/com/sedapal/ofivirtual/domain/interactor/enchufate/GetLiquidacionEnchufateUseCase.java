package pe.com.sedapal.ofivirtual.domain.interactor.enchufate;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.Liquidacion;
import pe.com.sedapal.ofivirtual.domain.entity.RequestLiquidacionEnchufate;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.EnchufateRepository;

public class GetLiquidacionEnchufateUseCase extends UseCase<Liquidacion, GetLiquidacionEnchufateUseCase.Params> {
    private final EnchufateRepository goEnchufateRepository;
    /**
     * Constructs a {@link UseCase}.
     *
     * @param threadExecutor      {@link ThreadExecutor}.
     * @param postExecutionThread {@link PostExecutionThread}.
     * @author jsaenz
     * @version 1.0
     * @since 02/04/2019
     */
    @Inject
    GetLiquidacionEnchufateUseCase(EnchufateRepository poEnchufateRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goEnchufateRepository = poEnchufateRepository;
    }

    @Override
    public Observable<Liquidacion> buildUseCaseObservable(Params params) {
        return goEnchufateRepository.getCloudLiquidacionEnchufate(params.poRequestLiquidacion);
    }

    public static final class Params {
        private final RequestLiquidacionEnchufate poRequestLiquidacion;

        public Params(RequestLiquidacionEnchufate poRequestLiquidacion) {
            this.poRequestLiquidacion = poRequestLiquidacion;
        }

        public static GetLiquidacionEnchufateUseCase.Params forValidate(RequestLiquidacionEnchufate poRequestLiquidacion) {
            return new GetLiquidacionEnchufateUseCase.Params(poRequestLiquidacion);
        }
    }
}
