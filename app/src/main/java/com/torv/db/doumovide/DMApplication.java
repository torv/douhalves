package com.torv.db.doumovide;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by admin on 16/6/25.
 */
public class DMApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
