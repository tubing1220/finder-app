package com.zjf.finder.base.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.module.GlideModule;
import com.zjf.finder.base.http.OKHttpHelper;

import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by yingwenjie on 3/20/17.
 */

public class OKHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new Factory());
    }

    public static class Factory extends OkHttpUrlLoader.Factory {
        private static final OkHttpClient sOkHttpClient = OKHttpHelper.createForImage();

        public Factory() {
            this(null);
        }

        public Factory(Call.Factory client) {
            super(sOkHttpClient);
        }

        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return super.build(context, factories);
        }
    }
}
