package shayri.status.gif.love.sad.wallpaper.boys.map.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAdView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.card_poem.*
import kotlinx.android.synthetic.main.fragment_poemm.*
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.BaseFragment
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.utils.Constants
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.PoemAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.poem.PoemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.viewmodel.PoemViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PoemmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PoemmFragment : BaseFragment(), PoemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    var rvpoem: RecyclerView? = null
    var poemadapter: PoemAdapter? = null
    var poemViewModel: PoemViewModel? = null

    var currentNativeAd: com.google.android.gms.ads.nativead.NativeAd? = null
    var dialogNativeAd: com.google.android.gms.ads.nativead.NativeAd? = null


    var Sharebody : String? = null
    var poembody : TextView? = null
    var sharepoem : ImageView? = null


    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    private var nativeAdFB1: NativeAd? = null
    private var nativeAdFB2: NativeAd? = null
    private var nativeAdLayout: NativeAdLayout? = null
    private var adView: LinearLayout? = null

    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_poemm, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews(view)
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        setRecyclerView()

        poemViewModel = ViewModelProvider(requireActivity()).get(PoemViewModel::class.java)
        poemViewModel?.loadData()

        poemViewModel?.poemLiveData?.observe(viewLifecycleOwner, Observer { t ->
            poemadapter!!.setItems(t)
        })
        if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {

            onLoadNativeAd1()
        }
    }

    fun initViews(view: View) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())

        rvpoem = view.findViewById(R.id.rvPoems)
        poembody = view.findViewById(R.id.poem_body)
        sharepoem = view.findViewById(R.id.share_poem)
    }

    fun setRecyclerView() {
        poemadapter = PoemAdapter(context)
        rvpoem.apply {
            rvpoem?.layoutManager = LinearLayoutManager(activity)
            rvpoem?.adapter = poemadapter
        }
    }

    fun onLoadNativeAd1(){
        val builder = AdLoader.Builder(context, Constants().getFbNativeHome1())
            .forNativeAd { nativeAd ->
                // Assumes that your ad layout is in a file call native_ad_layout.xml
                // in the res/layout folder
                currentNativeAd?.destroy()
                currentNativeAd = nativeAd
                val adView = layoutInflater
                    .inflate(R.layout.ad_unified, null) as NativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
                populateNativeAdView(nativeAd, adView)
                // Assumes you have a placeholder FrameLayout in your View layout
                // (with id ad_frame) where the ad is to be placed.
                ad_frame_home_1.removeAllViews()
                ad_frame_home_1.addView(adView)
            }

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d("TAG", "onAdFailedToLoad: ad_frame_home_1"+loadAdError.message)
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    private fun populateNativeAdView(nativeAd: com.google.android.gms.ads.nativead.NativeAd, adView: NativeAdView) {
        // Set the media view.
        adView.mediaView = adView.findViewById(R.id.ad_media)

        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
        adView.priceView = adView.findViewById(R.id.ad_price)
        adView.starRatingView = adView.findViewById(R.id.ad_stars)
        adView.storeView = adView.findViewById(R.id.ad_store)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        // The headline and media content are guaranteed to be in every UnifiedNativeAd.
        (adView.headlineView as TextView).text = nativeAd.headline
        adView.mediaView.setMediaContent(nativeAd.mediaContent)

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.body == null) {
            adView.bodyView.visibility = View.INVISIBLE
        } else {
            adView.bodyView.visibility = View.VISIBLE
            (adView.bodyView as TextView).text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.INVISIBLE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            adView.iconView.visibility = View.GONE
        } else {
            (adView.iconView as ImageView).setImageDrawable(
                nativeAd.icon.drawable
            )
            adView.iconView.visibility = View.VISIBLE
        }

        if (nativeAd.price == null) {
            adView.priceView.visibility = View.INVISIBLE
        } else {
            adView.priceView.visibility = View.VISIBLE
            (adView.priceView as TextView).text = nativeAd.price
        }

        if (nativeAd.store == null) {
            adView.storeView.visibility = View.INVISIBLE
        } else {
            adView.storeView.visibility = View.VISIBLE
            (adView.storeView as TextView).text = nativeAd.store
        }

        if (nativeAd.starRating == null) {
            adView.starRatingView.visibility = View.INVISIBLE
        } else {
            (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            adView.starRatingView.visibility = View.VISIBLE
        }

        if (nativeAd.advertiser == null) {
            adView.advertiserView.visibility = View.INVISIBLE
        } else {
            (adView.advertiserView as TextView).text = nativeAd.advertiser
            adView.advertiserView.visibility = View.VISIBLE
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd)

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
    }


    override fun onDestroy() {
        if(currentNativeAd!=null){
            currentNativeAd!!.destroy()
        }
        Log.d("TAG", "onDestroy: ")
        super.onDestroy()
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PoemmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            PoemmFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun Onpoemcardclick(item: List<String>) {
        Log.d("ITEM",item.toString())
        sharepoem?.setOnClickListener {
            Sharebody = poembody!!.text.toString()
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra("body",Sharebody)
//            startActivity(Intent.createChooser(intent,"Send to..."))

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, Sharebody)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}