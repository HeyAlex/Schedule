package com.alex.miet.mobile.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.content.res.AppCompatResources;

/**
 * Vector Utils for pre lolipop devices
 */
public class VectorUtil {

    public static BitmapDrawable vectorToBitmapDrawable(Context ctx, @DrawableRes int resVector) {
        return new BitmapDrawable(ctx.getResources(), vectorToBitmap(ctx, resVector));
    }

    public static Bitmap vectorToBitmap(Context ctx, @DrawableRes int resVector) {
        Drawable drawable = AppCompatResources.getDrawable(ctx, resVector);
        Bitmap b = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        drawable.setBounds(0, 0, c.getWidth(), c.getHeight());
        drawable.draw(c);
        return b;
    }
}
