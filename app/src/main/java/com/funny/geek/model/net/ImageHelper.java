package com.funny.geek.model.net;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.funny.geek.R;

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

}
