package pe.com.sedapal.ofivirtual.util;

import android.content.Context;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pe.com.sedapal.ofivirtual.R;
import pe.com.sedapal.ofivirtual.data.preference.PreferenceBase;
import pe.com.sedapal.ofivirtual.ui.widget.ToastCustom;

public class CommonUtil {

    public static void mostrarToast(Context context, String mensaje, int tipoMensaje) {
        final ToastCustom toastCustom = new ToastCustom(context, mensaje, tipoMensaje);
        toastCustom.show();

       /* new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                toastCustom.show();
            }

            public void onFinish() {
                toastCustom.cancel();
            }
            }.start();*/
    }

    public static String getDateValueInvoice(Context context, String month) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return  calendar.get(Calendar.DATE) + "-" +
                monthOfYearDescriptionAbrv(context, calendar.get(Calendar.MONTH))+ "-" +
                calendar.get(Calendar.YEAR);
    }

    public static String getMonthValue(Context context, String month) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return monthOfYearDescription(context, calendar.get(Calendar.MONTH));
    }

    public static String getMonthAndYearValue(Context context, String strDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return monthOfYearDescription(context, calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR);
    }

    public static String getDaysBetween(String strFechVenc) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCurrent = new Date();
        String strDayCurrent = format.format(dateCurrent);

        try {
            Date date1 = format.parse(strFechVenc);
            Date date2 = format.parse(strDayCurrent);
            long diff = date2.getTime() - date1.getTime();
            float days = (diff / (1000 * 60 * 60 * 24));

            return String.format("%.0f", days);//String.valueOf(days);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String monthOfYearDescription(Context context, int monthOfYear) {
        String descriptionMonth = "";
        switch (monthOfYear) {
            case 0:
                descriptionMonth = context.getString(R.string.mes_enero);
                break;
            case 1:
                descriptionMonth = context.getString(R.string.mes_febrero);
                break;
            case 2:
                descriptionMonth = context.getString(R.string.mes_marzo);
                break;
            case 3:
                descriptionMonth = context.getString(R.string.mes_abril);
                break;
            case 4:
                descriptionMonth = context.getString(R.string.mes_mayo);
                break;
            case 5:
                descriptionMonth = context.getString(R.string.mes_junio);
                break;
            case 6:
                descriptionMonth = context.getString(R.string.mes_julio);
                break;
            case 7:
                descriptionMonth = context.getString(R.string.mes_agosto);
                break;
            case 8:
                descriptionMonth = context.getString(R.string.mes_septiembre);
                break;
            case 9:
                descriptionMonth = context.getString(R.string.mes_octubre);
                break;
            case 10:
                descriptionMonth = context.getString(R.string.mes_noviembre);
                break;
            case 11:
                descriptionMonth = context.getString(R.string.mes_diciembre);
                break;
        }
        return descriptionMonth;
    }

    public static String getCurrentDate(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf;
        Date date = currentTime;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getCurrentTime(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf;
        Date date = currentTime;
        sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    private static String monthOfYearDescriptionAbrv(Context context, int monthOfYear) {
        String descriptionMonth = "";
        switch (monthOfYear) {
            case 0:
                descriptionMonth = context.getString(R.string.mes_enero_abrv);
                break;
            case 1:
                descriptionMonth = context.getString(R.string.mes_febrero_abrv);
                break;
            case 2:
                descriptionMonth = context.getString(R.string.mes_marzo_abrv);
                break;
            case 3:
                descriptionMonth = context.getString(R.string.mes_abril_abrv);
                break;
            case 4:
                descriptionMonth = context.getString(R.string.mes_mayo_abrv);
                break;
            case 5:
                descriptionMonth = context.getString(R.string.mes_junio_abrv);
                break;
            case 6:
                descriptionMonth = context.getString(R.string.mes_julio_abrv);
                break;
            case 7:
                descriptionMonth = context.getString(R.string.mes_agosto_abrv);
                break;
            case 8:
                descriptionMonth = context.getString(R.string.mes_septiembre_abrv);
                break;
            case 9:
                descriptionMonth = context.getString(R.string.mes_octubre_abrv);
                break;
            case 10:
                descriptionMonth = context.getString(R.string.mes_noviembre_abrv);
                break;
            case 11:
                descriptionMonth = context.getString(R.string.mes_diciembre_abrv);
                break;
        }
        return descriptionMonth;
    }

    public static String getFormatDateValue(String month) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = format.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDateFormat = df.format(date);
        return strDateFormat;
    }

    public static String getFormatDateValue_hhmm(String strDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDateFormat = df.format(date);
        return strDateFormat;
    }

    public static String getFormatDateValue_yyymmddd(String month) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDateFormat = df.format(date);
        return strDateFormat;
    }

    public static String getFormatDateValueUpdateUser_yyymmddd(String month) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDateFormat = df.format(date);
        return strDateFormat;
    }

    public static String getMonthAndYearValueGraphics(Context context, String strDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        DateFormat df = new SimpleDateFormat("yy");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String yearFormat = df.format(date);

        return monthOfYearDescriptionAbrv(context, calendar.get(Calendar.MONTH)) + " " + yearFormat;
    }

    public static String valueDoubleFormat(double dblCantidad){
        //DecimalFormat formatDecimal = new DecimalFormat("####.##");
        //return formatDecimal.format(dblCantidad).replace(",",".");
        //return String.format("%.2f", dblCantidad);
        //return String.format("%.2f", dblCantidad).replace(",",".");
        return String.format(Locale.ROOT, "%.2f", dblCantidad);
    }

    public static boolean validateDayIsbefore(String strDateSelected, String strDateListLoad){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateSelected = format.parse(strDateSelected);
            Date dateListLoad = format.parse(strDateListLoad);

            //if(dateSelected.equals(dateListLoad)){
            //    return false;
            //}else {
                if(dateSelected.after(dateListLoad)){
                    return true;
                }
            //}

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    static String FIRST_LOGIN;
    public static void saveFirstLogin(Context context){
        PreferenceBase preferenceBase = new PreferenceBase(context);
        preferenceBase.saveOnSharePreferences(FIRST_LOGIN, true);
    }

    public static boolean getFirstLogin(Context context){
        PreferenceBase preferenceBase = new PreferenceBase(context);
        return preferenceBase.getPreferenceBoolean(FIRST_LOGIN);
    }

    static String LAST_LOCATION = "LAST_LOCATION";
    public static void saveLastLocation(Context context, double latitud, double longitud){
        Coordenates coordenates = new Coordenates();
        coordenates.setLongitud(longitud);
        coordenates.setLatitud(latitud);

        PreferenceBase preferenceBase = new PreferenceBase(context);
        preferenceBase.saveOnSharePreferences(LAST_LOCATION, new Gson().toJson(coordenates));
    }

    public static Coordenates getLastLocation(Context context){
        PreferenceBase preferenceBase = new PreferenceBase(context);
        return new Gson().fromJson(preferenceBase.getPreference(LAST_LOCATION),Coordenates.class);
    }

    public static class Coordenates{
        private double latitud = 0;
        private double longitud = 0;

        public double getLatitud() {
            return latitud;
        }

        public void setLatitud(double latitud) {
            this.latitud = latitud;
        }

        public double getLongitud() {
            return longitud;
        }

        public void setLongitud(double longitud) {
            this.longitud = longitud;
        }
    }

}
