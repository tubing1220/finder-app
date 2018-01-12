package com.zjf.finder.base.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class CircleTransform extends BitmapTransformation {

    private int mCoverColor;

    public CircleTransform(Context context) {
        super(context);
    }

    public int getCoverColor() {
        return mCoverColor;
    }

    public void setCoverColor(int coverColor) {
        mCoverColor = coverColor;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        if (mCoverColor != 0) {
            Canvas canvas = new Canvas(squared);
            canvas.drawColor(mCoverColor);
        }

        Bitmap result = pool.get(size, size, Bitmap.Config.RGB_565);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    @Override
    public String getId() {
        if (mCoverColor == 0) {
            return getClass().getName();
        }
        return getClass().getName() + ".mCoverColor=" + mCoverColor;
    }
}
