package pe.com.sedapal.ofivirtual.util;

import android.content.Context;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;

import pe.com.sedapal.ofivirtual.R;

public class ImageUtil {
    public interface callbackImage {
        void onSucess();

        void onError();
    }


    public static void setGlideImagen(Context context, int imagen, ImageView contenedor, final callbackImage callbackOnlyImage){
        /*if(imagen != 0) {
            Glide.with(context).load(imagen).placeholder(R.drawable.ic_placeholder).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(contenedor);
        }*/
        if(imagen != 0) {
                Glide.with(context.getApplicationContext())
                        .load(imagen)
                        .fitCenter()
                        .listener(new RequestListener<Integer, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                                callbackOnlyImage.onError();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                callbackOnlyImage.onSucess();
                                return false;
                            }
                        })
                        .dontAnimate()
                        .into(contenedor);
            }

    }

    public static void setGlideImagen(Context context, int imagenDrawable, ImageView imageView) {
        if (imagenDrawable!=0){
            Glide.with(context.getApplicationContext())
                    .load(imagenDrawable)
                    .fitCenter()
                    .crossFade()
                    .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    public static void setGlideImagen(Context context, String imagenBase64, ImageView imageView) {
        if (imagenBase64!=null){
            byte[] imageByteArray = Base64.decode(imagenBase64, Base64.DEFAULT);
            Glide.with(context.getApplicationContext())
                    .load(imageByteArray)
                    .fitCenter()
                    .crossFade()
                    .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    public static void setGlideImagenUrl(Context context, String urlImage, ImageView imageView) {
        if (urlImage != null || !urlImage.equalsIgnoreCase("")) {
            Glide.with(context.getApplicationContext())
                    .load(urlImage)
                    .placeholder(R.drawable.ic_onboarding_1)
                    .dontAnimate()
                    .into(imageView);
        }
    }

    public static void setGlideImagenUrl(Context context, String urlImage, ImageView imageView, final callbackImage callbackImage) {
        if (urlImage != null || !urlImage.equalsIgnoreCase("")) {
            Glide.with(context.getApplicationContext())
                    .load(urlImage)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            callbackImage.onError();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            callbackImage.onSucess();
                            return false;
                        }
                    })
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(400,400)
                    .into(imageView);
        }
    }

    public static void setGlideImagenUrl(Context context, String urlImage, RelativeLayout relativeLayout, final callbackImage callbackImage) {
        if (urlImage != null || !urlImage.equalsIgnoreCase("")) {
            //.diskCacheStrategy(DiskCacheStrategy.ALL)

            Glide.with(context.getApplicationContext())
                    .load(urlImage)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            callbackImage.onError();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            callbackImage.onSucess();
                            return false;
                        }
                    })
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(400,400)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            relativeLayout.setBackground(resource);
                        }
                    });
        }
    }


    //public static void setGlideImagenUrl(Context context, String urlImage, RelativeLayout relativeLayout, final callbackImage callbackImage) {
    //    Glide.with(context)
    //            .load(urlImage)
    //            .asBitmap()
    //            .into(new SimpleTarget<Bitmap>() {
    //                @Override
    //                public void onResourceReady(Bitmap resource, GlideAnimation<? super
    //                        Bitmap> glideAnimation) {
    //                    try {
    //                        WallpaperManager.getInstance(context).setBitmap(resource);
    //                    } catch (IOException e) {
    //                        e.printStackTrace();
    //                    }
//
    //                }
    //            });
//
    //}
}
