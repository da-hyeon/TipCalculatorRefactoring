package com.hdh.hwangdahyeon.tipcalculator;

import android.app.Activity;
import android.app.Application;

import com.hdh.hwangdahyeon.tipcalculator.data.Location;
import com.hdh.hwangdahyeon.tipcalculator.data.Resolution;

public class MyApplication extends Application {

    private static MyApplication appInstance;
    private static Resolution resolution;
    private static Location location;
    private Activity mActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    /**
     * MyApplication Singleton
     */
    public static MyApplication getInstance() {
        if (appInstance == null) {
            appInstance = new MyApplication();
        }
        return appInstance;
    }

    /**
     * Resolution Singleton
     */
    public static Resolution getResolution() {
        if (resolution == null) {
            resolution = new Resolution();
        }
        return resolution;
    }

    /**
     * Location Singleton
     */
    public static Location getLocation() {
        if (location == null) {
            location = new Location();
        }
        return location;
    }

    /**
     * getActivity
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * setActivity
     */
    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
}
