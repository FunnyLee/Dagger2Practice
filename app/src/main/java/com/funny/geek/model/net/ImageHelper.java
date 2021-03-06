package com.funny.geek.model.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.funny.geek.R;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Author: Funny
 * Time: 2018/10/22
 * Description: This is ImageHelper
 */
public class ImageHelper {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().centerCrop().error(R.drawable.ic_launcher))
                .into(imageView);
    }

    /**
     * 给LinearLayout,RelativeLayout等加载背景图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadBgImage(Context context, String url, View view) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().centerCrop().error(R.drawable.green_gradient_color_shape))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        view.setBackground(resource);
                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void loadBlurryImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().error(R.drawable.bg_error).centerCrop())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(12, 1)))
                .into(imageView);
    }

}
