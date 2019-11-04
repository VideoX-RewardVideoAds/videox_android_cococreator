package org.cocos2dx.javascript;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxJavascriptJavaBridge;

import mobi.anasutil.anay.lite.AnalyticsBuilder;
import mobi.anasutil.anay.lite.AnalyticsSdk;
import mobi.android.InterstitialAd;
import mobi.android.RewardAd;
import mobi.android.StormSdkH;
import mobi.android.base.AdLoadListener;
import mobi.android.base.AdShowListener;
import mobi.android.base.ComponentHolder;
import mobi.android.base.InterAdLoadListener;
import mobi.android.base.InterstitialAdData;

public class SdkBridge {

    public static Handler mHandler = new Handler(Looper.getMainLooper());
    public static Activity mContext = null;   // 在MainActivity的onCreate里设置

    //------------------------------------------------------------------------
    private static String APP_NAME = "appname";

    private static String AD_SERVER_HOST = "http://192.168.5.222:13107";
    private static String AD_PUB_ID = "508";
    private static String AD_PUB_KEY = "FvijHlomwg8xwxds";

    private static String ANALYTICS_SERVER_HOST = "http://192.168.5.222:11011";
    private static String ANALYTICS_APPFLYER_KEY = "ELctKLYrDm4fb6desm4gmm";
    private static String ANALYTICS_TRAFFIC_ID = "400";
    private static String ANALYTICS_CHANNEL = "gp";
    private static String ANALYTICS_BUGLY_ID = "bba9c62404";
    private static String ANALYTICS_APP_ID = "0DNm0loY2qrkLUvNpU";
    private static String ANALYTICS_ENCODE_KEY = "kRJCSN1RAo6TFOY4FV";
    private static String ANALYTICS_DECODE_KEY = "0DNm0loY2qrkLUvNpUJORsciTuZ0gIEwunX9";
    //------------------------------------------------------------------------


    public static void initAdSdk(Context context) {
//        ComponentHolder.setUnity(true);
//        ComponentHolder.setUnityActivity(mContext);

        AnalyticsSdk.init(context, new AnalyticsBuilder.Builder()
                .setAnalyticsUrl(ANALYTICS_SERVER_HOST)
                .setAppsFlyerKey(ANALYTICS_APPFLYER_KEY)
                .setTrafficId(ANALYTICS_TRAFFIC_ID)
                .setChannel(ANALYTICS_CHANNEL)
                .setBuglyAppId(ANALYTICS_BUGLY_ID)
                .setAppId(ANALYTICS_APP_ID)
                .setEncodeKey(ANALYTICS_ENCODE_KEY)
                .setDecodeKey(ANALYTICS_DECODE_KEY)
                .build()
        );

        StormSdkH.initSdk("default", context, AD_SERVER_HOST, AD_PUB_ID, AD_PUB_KEY);

    }

    public static void initAdSdk() {

    }

    public static void loadInterstitialAd(String slotId) {
        InterstitialAd.loadAd(slotId, interAdLoadListener);
    }

    public static boolean isInterstitialAdReady(String slotId) {
        boolean flag = InterstitialAd.isReady(slotId);
//        ExportJavaFunction.CallBackToJS(SdkBridge.class, "isInterstitialAdReady", flag);
        return flag;
    }

    public static void showInterstitialAd(String slotId) {
        InterstitialAd.show(slotId);
    }

    public static void loadRewardAd(String slotId) {
        RewardAd.loadAd(slotId, adLoadListener);
    }

    public static boolean isRewardAdReady(String slotId) {
        boolean flag = RewardAd.isReady(slotId);
//        ExportJavaFunction.CallBackToJS(SdkBridge.class, "isRewardAdReady", flag);
        return flag;
    }

    public static void showRewardlAd(String slotId) {
        RewardAd.show(slotId, adShowListener);
    }


    public static void sendEvent(String act, String lab, String val, String extra, String eid) {
        AnalyticsSdk.sendEvent(APP_NAME, act, lab, val, extra, eid);
//        DataReporter.sendEvent(act, lab, val, extra, eid);
    }


    private static InterAdLoadListener interAdLoadListener = new InterAdLoadListener() {
        @Override
        public void onError(final String s, final String s1) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onInterstitialAdLoadError(\'" + s + "\',\'" + s1 + "\')");
                }
            });

//            ConchJNI.RunJS("onInterstitialAdLoadError(\'" + s + "\',\'" + s1 +"\')");
        }

        @Override
        public void onAdLoaded(final String s, InterstitialAdData interstitialAdData) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onInterstitialAdLoadSuccess(\'" + s + "\')");

                }
            });

//            ConchJNI.RunJS("onInterstitialAdLoadSuccess(\'" + s +"\')");
        }

        @Override
        public void onAdClosed(final String s) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onInterstitialAdClose(\'" + s + "\')");

                }
            });

//            ConchJNI.RunJS("onInterstitialAdClose(\'" + s +"\')");
        }

        @Override
        public void onAdClicked(final String s) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onInterstitialAdClick(\'" + s + "\')");

                }
            });

//            ConchJNI.RunJS("onInterstitialAdClick(\'" + s +"\')");
        }
    };

    private static AdLoadListener adLoadListener = new AdLoadListener() {
        @Override
        public void onLoad(final String s) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onRewardAdLoadSuccess(\'" + s + "\')");

                }
            });

//            ConchJNI.RunJS("onRewardAdLoadSuccess(\'" + s +"\')");
        }

        @Override
        public void onError(final String s, final String s1) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onRewardAdLoadError(\'" + s + "\',\'" + s1 + "\')");

                }
            });

//            ConchJNI.RunJS("onRewardAdLoadError(\'" + s + "\',\'" + s1 +"\')");
        }
    };

    private static AdShowListener adShowListener = new AdShowListener() {
        @Override
        public void onStart(final String s) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onRewardAdLoadOnShow(\'" + s + "\')");

                }
            });

//            ConchJNI.RunJS("onRewardAdLoadOnShow(\'" + s +"\')");
        }

        @Override
        public void onFinish(final String s, final boolean b) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onRewardAdLoadOnClose(\'" + s + "\'," + b + ")");

                }
            });

//            ConchJNI.RunJS("onRewardAdLoadOnClose(\'" + s + "\'," + b +")");
        }

        @Override
        public void onError(final String s, final String s1) {
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                @Override
                public void run() {
                    Cocos2dxJavascriptJavaBridge.evalString("onRewardAdLoadOnShowError(\'" + s + "\',\'" + s1 + "\')");

                }
            });

//            ConchJNI.RunJS("onRewardAdLoadOnShowError(\'" + s + "\',\'" + s1 +"\')");
        }

        @Override
        public void onClick(String s) {
            //
        }
    };


    public static void ShowToast(final String msg) {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
