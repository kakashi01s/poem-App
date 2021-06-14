package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.utils.Constants
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.MainActivity
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.WebActivity
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.adapter.DealsAdapter
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.viewmodel.DealsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DealFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    var rvInvest: RecyclerView? = null
    var dealsAdapter: DealsAdapter? = null
    var dealsViewModel: DealsViewModel? = null
    var investDataList: ArrayList<List<String>>? = ArrayList()
    var firebaseAnalytics: FirebaseAnalytics? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var btShowDeals: Button? = null
    private var interstitialFbAd: InterstitialAd? = null
    private var adView: AdView? = null
    var layoutShowVideo: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    var webViewDeals: WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        if (firebaseRemoteConfig == null) {
            firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        }
        if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {
            if (interstitialFbAd == null) {
                getDisplayAdsLayout()
                onLoadFbInterstitial()
            }
        } else {
            getDisplayDeals()
        }

        webViewSettings(webViewDeals!!)
        webViewDeals!!.loadUrl(firebaseRemoteConfig!!.getString(Constants().DEALS_URL))

        btShowDeals!!.setOnClickListener {
            if (interstitialFbAd!!.isAdLoaded) {
                interstitialFbAd!!.show()
            } else {
                Toast.makeText(
                    context,
                    "Congrats!! now you're able fetch fantastic stuff here!!",
                    Toast.LENGTH_SHORT
                ).show()
                getDisplayDeals()
            }
        }


    }

    fun initViews(view: View){
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())
        layoutShowVideo = view.findViewById(R.id.layoutShowVideo)
        btShowDeals = layoutShowVideo!!.findViewById(R.id.btShowDeals)
        webViewDeals = view.findViewById(R.id.webViewDeals)
    }

    fun onLoadFbInterstitial() {
        interstitialFbAd = InterstitialAd(context, Constants().getFbInterstitialWebExit())

        val interstitialAdListener: InterstitialAdListener = object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.")
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.")
                getDisplayDeals()
                Toast.makeText(
                    context,
                    "Congrats!! now you're able fetch fantastic stuff here!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(ad: Ad?, adError: AdError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage())
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!")
                // Show the ad
//                interstitialFbAd.show()
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!")
            }
        }

        interstitialFbAd!!.loadAd(
            interstitialFbAd!!.buildLoadAdConfig()
                .withAdListener(interstitialAdListener)
                .build()
        );

    }

    fun getDisplayDeals() {
        if (layoutShowVideo!!.visibility == View.VISIBLE) {
            layoutShowVideo!!.visibility = View.GONE
        }
        if (webViewDeals!!.visibility == View.GONE) {
            webViewDeals!!.visibility = View.VISIBLE
        }
    }

    fun getDisplayAdsLayout() {
        if (webViewDeals!!.visibility == View.VISIBLE) {
            webViewDeals!!.visibility = View.GONE
        }
        if (layoutShowVideo!!.visibility == View.GONE) {
            layoutShowVideo!!.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        dealsViewModel?.reset()
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InvestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            DealFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun webViewSettings(webView: WebView){
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess = true
//        webView.evaluateJavascript("\$(function() {\n" +
//                "    var style = document.createElement('style');\n" +
//                "    style.innerHTML = `\n" +
//                "    .ShopNewBtn > img {\n" +
//                "    display: none;\n" +
//                "    }\n" +
//                "    .original_price_p{\n" +
//                "      color:red;\n" +
//                "    }\n" +
//                "    .ShopNewBtn{\n" +
//                "      background-color: red;\n" +
//                "    }\n" +
//                "    .CuponMain{\n" +
//                "      border: 1px solid red;\n" +
//                "    }\n" +
//                "    .button-colors-green{\n" +
//                "      background-color:red;\n" +
//                "      border: 1px solid red;\n" +
//                "    }\n" +
//                "    `;\n" +
//                "    document.head.appendChild(style);\n" +
//                "});",null)
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.domStorageEnabled = true
        webView.clearView()
        webView.isHorizontalScrollBarEnabled = false
        webView.settings.setAppCacheEnabled(true)
        webView.isVerticalScrollBarEnabled = false
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.settings.allowFileAccess = true
        webView.settings.pluginState = WebSettings.PluginState.OFF
        webView.isScrollbarFadingEnabled = false
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.settings.defaultZoom = WebSettings.ZoomDensity.FAR
        webView.webViewClient = WebViewClient()
        webView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView.setInitialScale(1)

        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.loadUrl("javascript:\$(function() {\n" +
                        "    var style = document.createElement('style');\n" +
                        "    style.innerHTML = `\n" +
                        "    .ShopNewBtn > img {\n" +
                        "    display: none;\n" +
                        "    }\n" +
                        "    .original_price_p{\n" +
                        "      color:#53b9fb;\n" +
                        "    }\n" +
                        "    .ShopNewBtn{\n" +
                        "      background-color: #53b9fb;\n" +
                        "    }\n" +
                        "    .CuponMain{\n" +
                        "      border: 1px solid #53b9fb;\n" +
                        "    }\n" +
                        "    .button-colors-green{\n" +
                        "      background-color:#53b9fb;\n" +
                        "      border: 1px solid #53b9fb;\n" +
                        "    }\n" +
                        "    `;\n" +
                        "    document.head.appendChild(style);\n" +
                        "});")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Log.d(TAG, "shouldOverrideUrlLoading: deals "+request.url.toString())
                    val intent = Intent(activity, WebActivity::class.java)
                    intent.putExtra("title", webView.title)
                    intent.putExtra("url", request.url.toString())
                    intent.putExtra("app_icon", "https://firebasestorage.googleapis.com/v0/b/shopping-crate.appspot.com/o/hot-sale.png?alt=media&token=796d1a38-deba-41ec-9516-2fdb9b451b31")
                    intent.putExtra("color", "#666666")


                    val bundle = Bundle()
                    bundle.putString("title", webView.title)
                    bundle.putString("url", request.url.toString())

                    (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "deals_visited", true)


                    startActivity(intent)
                }
                return true
            }
        }
    }


}