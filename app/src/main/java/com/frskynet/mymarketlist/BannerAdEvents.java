package com.frskynet.mymarketlist;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;

/**
 * Created by F R Summit on 27th September,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class BannerAdEvents {

    public void loadAd(final Context context, AdView adView) {
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(context, "Ad is loaded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(context, "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(context, "Ad is opened!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(context, "Ad is clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(context, "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(context, "Ad is closed!", Toast.LENGTH_SHORT).show();
//                adView.loadAd(new AdRequest.Builder().build());
            }
        });
    }
}
