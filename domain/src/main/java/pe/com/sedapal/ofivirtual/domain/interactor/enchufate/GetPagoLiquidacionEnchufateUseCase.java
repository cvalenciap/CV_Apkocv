package pe.com.sedapal.ofivirtual.domain.interactor.enchufate;

import javax.inject.Inject;

import io.reactivex.Observable;
import pe.com.sedapal.ofivirtual.domain.entity.PayLiquidation;
import pe.com.sedapal.ofivirtual.domain.executor.PostExecutionThread;
import pe.com.sedapal.ofivirtual.domain.executor.ThreadExecutor;
import pe.com.sedapal.ofivirtual.domain.interactor.UseCase;
import pe.com.sedapal.ofivirtual.domain.repository.EnchufateRepository;

public class GetPagoLiquidacionEnchufateUseCase extends UseCase<PayLiquidation, GetPagoLiquidacionEnchufateUseCase.Params> {
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
    GetPagoLiquidacionEnchufateUseCase(EnchufateRepository poEnchufateRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.goEnchufateRepository = poEnchufateRepository;
    }

    @Override
    public Observable<PayLiquidation> buildUseCaseObservable(Params params) {
        return goEnchufateRepository.getCloudPagoLiquidacionEnchufate(
                params.customerEmail,
                params.numeroLiquidacion,
                params.nisRad,
                params.amount,
                params.TRANSACTION_ID,
                params.ACTION_CODE,
                params.STATUS,
                params.TRANSACTION_DATE,
                params.ACTION_DESCRIPTION,
                params.TRACE_NUMBER,
                params.CARD,
                params.BRAND,
                params.authCorreo,
                params.flagChannel,
                params.listaRegistros
        );
    }

    public static final class Params {
        private String customerEmail;
        private String numeroLiquidacion;
        private String nisRad;
        private String amount;
        private String TRANSACTION_ID;
        private String ACTION_CODE;
        private String STATUS;
        private String TRANSACTION_DATE;
        private String ACTION_DESCRIPTION;
        private String TRACE_NUMBER;
        private String CARD;
        private String BRAND;
        private String authCorreo;
        private String flagChannel;
        private String listaRegistros;

        public Params(String customerEmail,
                      String numeroLiquidacion,
                      String nisRad,
                      String amount,
                      String TRANSACTION_ID,
                      String ACTION_CODE,
                      String STATUS,
                      String TRANSACTION_DATE,
                      String ACTION_DESCRIPTION,
                      String TRACE_NUMBER,
                      String CARD,
                      String BRAND,
                      String authCorreo,
                      String flagChannel,
                      String listaRegistros) {
            this.customerEmail = customerEmail;
            this.numeroLiquidacion = numeroLiquidacion;
            this.nisRad = nisRad;
            this.amount = amount;
            this.TRANSACTION_ID = TRANSACTION_ID;
            this.ACTION_CODE = ACTION_CODE;
            this.STATUS = STATUS;
            this.TRANSACTION_DATE = TRANSACTION_DATE;
            this.ACTION_DESCRIPTION = ACTION_DESCRIPTION;
            this.TRACE_NUMBER = TRACE_NUMBER;
            this.CARD = CARD;
            this.BRAND = BRAND;
            this.authCorreo = authCorreo;
            this.flagChannel = flagChannel;
            this.listaRegistros = listaRegistros;
        }

        public static GetPagoLiquidacionEnchufateUseCase.Params forValidate(String customerEmail,
                                                                            String numeroLiquidacion,
                                                                            String nisRad,
                                                                            String amount,
                                                                            String TRANSACTION_ID,
                                                                            String ACTION_CODE,
                                                                            String STATUS,
                                                                            String TRANSACTION_DATE,
                                                                            String ACTION_DESCRIPTION,
                                                                            String TRACE_NUMBER,
                                                                            String CARD,
                                                                            String BRAND,
                                                                            String authCorreo,
                                                                            String flagChannel,
                                                                            String listaRegistros) {
            {
                return new GetPagoLiquidacionEnchufateUseCase.Params(customerEmail,
                        numeroLiquidacion,
                        nisRad,
                        amount,
                        TRANSACTION_ID,
                        ACTION_CODE,
                        STATUS,
                        TRANSACTION_DATE,
                        ACTION_DESCRIPTION,
                        TRACE_NUMBER,
                        CARD,
                        BRAND,
                        authCorreo,
                        flagChannel,
                        listaRegistros);
            }
        }
    }
}
