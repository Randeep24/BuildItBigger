package com.randeep.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.randeep.jokedisplay.JokeDisplayActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements JokeReceiverAsyncTask.JokeListener {

    private static final String JOKE = "JOKE";
    private InterstitialAd mInterstitialAd;
    private Button jokeButton;
    private ProgressBar progressBar;

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = rootView.findViewById(R.id.tellJokeButton);
        progressBar = rootView.findViewById(R.id.progressBar);
        AdView adView = rootView.findViewById(R.id.adView);


        initBannerAd(adView);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jokeButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                mInterstitialAd.show();
                new JokeReceiverAsyncTask(MainFragment.this, getActivity()).execute();

            }
        });

        return rootView;
    }

    private void initBannerAd(AdView adView) {

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onJokeReceived(String joke) {

        jokeButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JOKE, joke);
        startActivity(intent);

    }
}
