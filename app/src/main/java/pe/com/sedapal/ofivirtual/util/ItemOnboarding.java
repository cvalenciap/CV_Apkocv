package pe.com.sedapal.ofivirtual.util;

import android.graphics.Bitmap;

/**
 * Created by jsaenz
 */

public class ItemOnboarding {
    int idImagen;
    String descripcion;
    Bitmap bitmapImageBack;
    Bitmap bitmapIcon;

    public ItemOnboarding() {
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Bitmap getBitmapImageBack() {
        return bitmapImageBack;
    }

    public void setBitmapImageBack(Bitmap bitmapImageBack) {
        this.bitmapImageBack = bitmapImageBack;
    }

    public Bitmap getBitmapIcon() {
        return bitmapIcon;
    }

    public void setBitmapIcon(Bitmap bitmapIcon) {
        this.bitmapIcon = bitmapIcon;
    }
}
