package pe.com.sedapal.ofivirtual.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.com.sedapal.ofivirtual.model.GenderPersonModel;

public class ValidationUtil {
    public static int CANTIDAD_CARACTERES_SUMINSITRO = 7;
    public static int CANTIDAD_CARACTERES_REFERENCIA_COBRO = 10;
    public static int CANTIDAD_CARACTERES_MAX_PASSWORD = 18;
    public static int CANTIDAD_CARACTERES_TELEFONO = 9;

    /**
     * Tipo de documento
     */

    public static final int ID_CARNET_EXTRNJERIA = 7;
    public static final int ID_DNI = 6;
    public static final int ID_PARTIDA_NACIMIENTO = 10;
    public static final int ID_PASAPORTE = 9;
    public static final int ID_RUC = 8;

    public static int CANTIDAD_CARACTERES_CARNET_EXTRNJERIA = 12;
    public static int CANTIDAD_CARACTERES_DNI = 8;
    public static int CANTIDAD_CARACTERES_PARTIDA_NACIMIENTO = 15;
    public static int CANTIDAD_CARACTERES_PASAPORTE = 12;
    public static int CANTIDAD_CARACTERES_RUC = 11;

    public static String FLAG_RESPUESTA_DIALOGO_REGISTRAR = "A";

    public static boolean esValidoPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN_ESPECIAL_CARACTERS = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static boolean esValidoCorreo(final String correo) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(correo)
                .matches();
    }

    public static boolean esValidoNumeroSuministro(final String suministro) {
        if(suministro.length() == ValidationUtil.CANTIDAD_CARACTERES_SUMINSITRO){
            return true;
        }

        return false;
    }

    public static boolean esValidoNumeroTelefono(final String numTelefono) {
        if(numTelefono.length() <= ValidationUtil.CANTIDAD_CARACTERES_TELEFONO && numTelefono.length() >= 6){
            return true;
        }

        return false;
    }

    public static List<GenderPersonModel> lstValSpnGenero(){
        List<GenderPersonModel> poListGenderPerson = new ArrayList<>();
        GenderPersonModel poGenderPersonModel = new GenderPersonModel();
        poGenderPersonModel.setIdGender("M");
        poGenderPersonModel.setDescripcion("Masculino");
        poListGenderPerson.add(poGenderPersonModel);

        poGenderPersonModel = new GenderPersonModel();
        poGenderPersonModel.setIdGender("F");
        poGenderPersonModel.setDescripcion("Femenino");
        poListGenderPerson.add(poGenderPersonModel);
        return poListGenderPerson;
    }

    public static long isValidLong(String loNumber){
        long numero = 0;
        if(!loNumber.equalsIgnoreCase("")){
            numero = Long.parseLong(loNumber);
        }
        return numero;
    }

    /**
     * Permite configurar los campos según el tipo de documento
     */


    public static int configurarCantidadDigitosNumDocumento(int idTipoDocumento){
        int cantidadCaracteresNumDoc;
        switch (idTipoDocumento){
            case ValidationUtil.ID_CARNET_EXTRNJERIA:
                cantidadCaracteresNumDoc = 12;
                break;
            case ValidationUtil.ID_DNI:
                cantidadCaracteresNumDoc = 8;
                break;
            case ValidationUtil.ID_PARTIDA_NACIMIENTO:
                cantidadCaracteresNumDoc = 15;
                break;
            case ValidationUtil.ID_PASAPORTE:
                cantidadCaracteresNumDoc = 12;
                break;
            case ValidationUtil.ID_RUC:
                cantidadCaracteresNumDoc = 11;
                break;
            default:
                cantidadCaracteresNumDoc = 0;
                break;
        }
       return cantidadCaracteresNumDoc;
    }

    /**
     * Permite configurar los campos según el tipo de documento
     */

    public static boolean tieneNumeros(int idTipoDocumento){
        boolean isNumber;
        switch (idTipoDocumento){
            case ValidationUtil.ID_CARNET_EXTRNJERIA:
                isNumber = false;
                break;
            case ValidationUtil.ID_DNI:
                isNumber = true;
                break;
            case ValidationUtil.ID_PARTIDA_NACIMIENTO:
                isNumber = false;
                break;
            case ValidationUtil.ID_PASAPORTE:
                isNumber = false;
                break;
            case ValidationUtil.ID_RUC:
                isNumber = true;
                break;
            default:
                isNumber = false;
                break;
        }
        return isNumber;
    }

    /**
     * Validador de HTML
     */
    public final static String tagStart=
            "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";
    public final static String tagEnd=
            "\\</\\w+\\>";
    public final static String tagSelfClosing=
            "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";
    public final static String htmlEntity=
            "&[a-zA-Z][a-zA-Z0-9]+;";
    public final static Pattern htmlPattern=Pattern.compile(
            "("+tagStart+".*"+tagEnd+")|("+tagSelfClosing+")|("+htmlEntity+")",
            Pattern.DOTALL
    );

    public static boolean isHtml(String s) {
        boolean ret=false;
        if (s != null) {
            ret=htmlPattern.matcher(s).find();
        }
        return ret;
    }
}
