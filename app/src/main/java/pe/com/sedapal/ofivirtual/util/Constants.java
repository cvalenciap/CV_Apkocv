package pe.com.sedapal.ofivirtual.util;

/**
 * Created by jsaenz on 20/03/2019
 */

public final class Constants {

    public static final class SESSION {
        //5 MINUTES
        public static final int TIME = 300;
        public static final String MESSAGE_END_SESSION = "El tiempo de sesión ha terminado";
        public final static String ERROR_DATA_SESSION_TOKEN = "40000";
    }

    public static final class PARAMETROS_CATEGORIA {
        public static final String DATOS_VISA = "DATOS_VISA_MOVIL";
        public static final String DATOS_IMAGENES = "DATOS_IMAGENES";
        public static final String DATOS_ENCHUFATE = "DATOS_ENCHUFATE";
    }

    public static final class PARAMETROS_BD {
        public static final String UPDATE_APP_PARAM_CODE = "err001";
    }

    public static final class VERSION_APP {
        public static final String FLAG_REQUIRED = "1";
        public static final String FLAG_CHANNEL = "2";
    }
}

