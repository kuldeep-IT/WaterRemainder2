package com.example.waterremainder.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.waterremainder.R
import com.example.waterremainder.databinding.FragmentHomeBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentHomeBinding
    private var mInterstitialAd: InterstitialAd? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadBannerAd()
        loadInterAd()

        binding.ivbAds.setOnClickListener(this)

        return binding.root
    }

    fun loadBannerAd() {
        MobileAds.initialize(requireActivity()) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        binding.adView.adListener = object : AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Toast.makeText(requireActivity(), "Ad clicked!!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Toast.makeText(requireActivity(), "onAdClosed!!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                Toast.makeText(requireActivity(), "onAdFailedToLoad!!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
                Toast.makeText(requireActivity(), "onAdImpression!!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Toast.makeText(requireActivity(), "onAdLoaded!!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Toast.makeText(requireActivity(), "onAdOpened!!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun loadInterAd() {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            requireActivity(),
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("AD_ERROR", adError?.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("AD_ERROR", "onAdLoaded")
                    mInterstitialAd = interstitialAd
                    Log.d("AD_ERROR_mInterstitialAd", "mInterstitialAd: "+mInterstitialAd)
                }

            })
    }

    fun showInterAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d("AD_ERROR", "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    Log.d("AD_ERROR", "Ad dismissed fullscreen content.")
                    mInterstitialAd = null
                    loadInterAd()

                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    // Called when ad fails to show.
                    Log.e("AD_ERROR", "Ad failed to show fullscreen content.")
                    mInterstitialAd = null
                    loadInterAd()
                }

                override fun onAdImpression() {
                    // Called when an impression is recorded for an ad.
                    Log.d("AD_ERROR", "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d("AD_ERROR", "Ad showed fullscreen content.")
                }
            }

            mInterstitialAd?.show(requireActivity())
        } else {
            Log.d("AD_ERROR_mInterstitialAd_else", "mInterstitialAd: "+mInterstitialAd)

            Toast.makeText(requireActivity(), "It is not ready right now. Please click again", Toast.LENGTH_SHORT).show()
            loadInterAd()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivbAds -> {
                showInterAd()
                Toast.makeText(requireActivity(),"clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}