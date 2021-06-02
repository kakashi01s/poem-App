package shopping.grocery.medicine.online.deals.coupons.compare.buy.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.ads.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_web.*
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.model.bookmark.Bookmarks
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Constants
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Pref


class WebActivity : AppCompatActivity() {

    var webView: WebView? = null
    var appTitle: String? = null
    var appUrl: String? = null
    var appIcon: String? = null
    var color: String? = null
    var searchUrl: String? = null

    var rlWebSplash: RelativeLayout? = null
    var ivAppIcon: ImageView? = null

    private val TAG: String = WebActivity::class.java.simpleName
    private val TIME_DELAY = 2000
    private var back_pressed: Long = 0

    private var interstitialFbAd: InterstitialAd? = null
    private var adView: AdView? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var mGeoLocationRequestOrigin: String? = null
    var mGeoLocationCallback: GeolocationPermissions.Callback? = null

    lateinit var btn1: FloatingActionButton
    lateinit var share: FloatingActionButton
    private lateinit var bkmark: FloatingActionButton
    private lateinit var bkmarkText: TextView
    private lateinit var shareText: TextView
    private var clicked = false

    var bookmarksList: ArrayList<Bookmarks>? = ArrayList()

    private val LOCATION_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        initViews()
        initData()

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {

            onFbBannerAds()
            onLoadFbInterstitial()
        }
        loadWebSplash()

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_CODE
        )

        webViewSettings()

        if (searchUrl == null) {
            webView?.loadUrl(appUrl!!)
        } else {
            webView?.loadUrl(searchUrl!!)
        }

        btn1.setOnClickListener {
            onButtonClicked()
        }



        share.setOnClickListener {
            val sendIntent: Intent = Intent().setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(
                Intent.EXTRA_TEXT, appUrl
            )
            sendIntent.type = "text/simple"
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        bkmark.setOnClickListener {
            val bookmarks: Bookmarks? = Bookmarks()
            bookmarks!!.bookmarkTitle = webView!!.title
            bookmarks.bookmarkStoreTitle = appTitle
            bookmarks.bookmarkUrl = webView!!.url
            bookmarks.bookmarkLogo = appIcon

            bookmarksList!!.add(bookmarks)

            val bundle = Bundle()
            bundle.putString("bookmarkTitle", webView!!.title)
            bundle.putString("bookmarkStoreTitle", appTitle)
            bundle.putString("bookmarkUrl", webView!!.url)
            firebaseAnalytics!!.logEvent("bookmarks_Usage", bundle)

            setBookmarks()
        }
    }

    fun initViews() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this@WebActivity)
        webView = findViewById(R.id.webViewMain)
        rlWebSplash = findViewById(R.id.rlWebSplash)
        ivAppIcon = findViewById(R.id.ivAppIcon)
        share = findViewById(R.id.button2)
        bkmark = findViewById(R.id.button3)
        btn1 = findViewById(R.id.button1)
        shareText = findViewById(R.id.share)
        bkmarkText = findViewById(R.id.bkmark)
    }

    fun initData() {

        val bundle: Bundle? = intent.extras
        appUrl = bundle?.getString("url")
        appIcon = bundle?.getString("app_icon")
        appTitle = bundle?.getString("title")
        color = bundle?.getString("color")
        searchUrl = bundle?.getString("search_url")

        Log.d("TAG", "initData: " + bundle?.getString("url"))
        Log.d("colorweb", "initData: " + bundle?.getString("color"))
        Log.d("search_url", "initData: " + bundle?.getString("search_url"))

    }

    private fun setBookmarks() {
        Log.d("ShareBookmark", bookmarksList.toString())
        val bookmarksData = Gson().toJson(bookmarksList)
        Pref.instance!!.bookmarksData = bookmarksData
    }

    fun removeBookmark(bookmarkurl: String?) {
        if (bookmarksList != null) {
            var i = 0
            while (i < bookmarksList!!.size) {
                val bookmarkData: Bookmarks = bookmarksList!!.get(i)
                if (bookmarkData.getBookmarkUrlWithoutAffiliate() != null && bookmarkData.getBookmarkUrlWithoutAffiliate()
                        .equals(bookmarkurl)
                ) {
                    bookmarksList!!.removeAt(i)
                    //                    Toast.makeText(WebActivity.this,"Bookmark removed successfully",Toast.LENGTH_SHORT).show();
                    val toast = Toast.makeText(
                        this@WebActivity,
                        "Item removed from wishlist",
                        Toast.LENGTH_SHORT
                    )
                    val view = toast.view

//Gets the actual oval background of the Toast then sets the colour filter
                    view?.background?.setColorFilter(
                        resources.getColor(R.color.black_75),
                        PorterDuff.Mode.SRC_IN
                    )

//Gets the TextView from the Toast so it can be editted
                    var text: TextView? = null
                    if (view != null) {
                        text = view.findViewById(android.R.id.message)
                    }
                    text?.setTextColor(resources.getColor(R.color.white))
                    toast.show()
                    Log.d("TAG", "removeBookmark: removed")
                }
                i++
            }
            setBookmarks()
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun webViewSettings() {
        webView!!.settings.loadsImagesAutomatically = true
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.allowContentAccess = true

        webView!!.settings.useWideViewPort = true
        webView!!.settings.loadWithOverviewMode = true
        webView!!.settings.domStorageEnabled = true
        webView!!.clearView()
        webView!!.isHorizontalScrollBarEnabled = false
        webView!!.settings.setAppCacheEnabled(true)
        webView!!.settings.databaseEnabled = true
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView!!.settings.databasePath = "/data/data/" + this.packageName + "/databases/"
        }
        webView!!.isVerticalScrollBarEnabled = false
        webView!!.settings.builtInZoomControls = true
        webView!!.settings.displayZoomControls = false
        webView!!.settings.allowFileAccess = true
        webView!!.settings.pluginState = WebSettings.PluginState.OFF
        webView!!.isScrollbarFadingEnabled = false
        webView!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView!!.settings.defaultZoom = WebSettings.ZoomDensity.FAR
        webView!!.webViewClient = WebViewClient()
        webView!!.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView!!.setInitialScale(1)

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.url.toString())
                }
                return false
            }
        }

        webView!!.webChromeClient = object : WebChromeClient() {

            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback?
            ) {
                if (ContextCompat.checkSelfPermission(
                        this@WebActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this@WebActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        AlertDialog.Builder(this@WebActivity)
                            .setMessage("Please turn ON the GPS to make app work smoothly")
                            .setNeutralButton(
                                android.R.string.ok,
                                DialogInterface.OnClickListener { dialogInterface, i ->
                                    mGeoLocationCallback = callback
                                    mGeoLocationRequestOrigin = origin
                                    ActivityCompat.requestPermissions(
                                        this@WebActivity,
                                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                        1001
                                    )

                                })
                            .show()

                    } else {
                        //no explanation need we can request the locatio
                        mGeoLocationCallback = callback
                        mGeoLocationRequestOrigin = origin
                        ActivityCompat.requestPermissions(
                            this@WebActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1001
                        )
                    }
                } else {
                    //tell the webview that permission has granted
                    callback!!.invoke(origin, true, true)
                }
            }


            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress >= 80) {
                    rlWebSplash!!.visibility = View.GONE
                }
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1001 -> {
                //if permission is cancel result array would be empty
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    if (mGeoLocationCallback != null) {
                        mGeoLocationCallback!!.invoke(mGeoLocationRequestOrigin, true, true)
                    }
                } else {
                    //permission denied
                    if (mGeoLocationCallback != null) {
                        mGeoLocationCallback!!.invoke(mGeoLocationRequestOrigin, false, false)
                    }
                }
            }
        }

    }

    fun loadWebSplash() {

        rlWebSplash!!.setBackgroundColor(Color.parseColor(color))

        Glide.with(ivAppIcon!!.context)
            .load(appIcon)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivAppIcon!!)
    }

    fun onLoadFbInterstitial() {
        interstitialFbAd = InterstitialAd(this, Constants().getFbInterstitialWebExit())

        val interstitialAdListener: InterstitialAdListener = object : InterstitialAdListener {
            override fun onInterstitialDisplayed(ad: Ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.")
            }

            override fun onInterstitialDismissed(ad: Ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.")
                finish()
            }

            override fun onError(ad: Ad?, adError: AdError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage())
            }

            override fun onAdLoaded(ad: Ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!")
                // Show the ad
//                interstitialFbAd.show()
            }

            override fun onAdClicked(ad: Ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!")
            }
        }

        interstitialFbAd!!.loadAd(
            interstitialFbAd!!.buildLoadAdConfig()
                .withAdListener(interstitialAdListener)
                .build()
        );

    }

    fun onFbBannerAds() {
        adView = AdView(this, Constants().getFbBannerWeb(), AdSize.BANNER_HEIGHT_50)

        // Find the Ad Container
        val adContainer = findViewById<View>(R.id.banner_container) as LinearLayout

        // Add the ad view to your activity layout
        adContainer.addView(adView)

        val adListenerBanner: com.facebook.ads.AdListener = object : com.facebook.ads.AdListener {
            override fun onError(ad: Ad?, adError: AdError) {
                // Ad error callback
            }

            override fun onAdLoaded(ad: Ad?) {
                Log.d(TAG, "onAdLoaded: Banner")
            }

            override fun onAdClicked(ad: Ad?) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad?) {
                // Ad impression logged callback
            }
        }

        // Request an ad
        adView!!.loadAd(adView!!.buildLoadAdConfig().withAdListener(adListenerBanner).build())
    }

    public override fun onPause() {
//        adView.pause()
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
//        adView.resume()
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        if (adView != null) {
            adView!!.destroy()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {

        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            Toast.makeText(
                getBaseContext(), "Double click to exit!",
                Toast.LENGTH_SHORT
            ).show();
            if (webView!!.canGoBack()) {
                webView!!.goBack()
            } else {
                if (interstitialFbAd != null && interstitialFbAd!!.isAdLoaded) {
                    if (interstitialFbAd!!.isAdInvalidated) {
                    } else {
                        interstitialFbAd!!.show()
                    }
                } else {
                    super.onBackPressed()
                }

            }
        }
        back_pressed = System.currentTimeMillis();

    }

    private fun onButtonClicked() {

        if (!clicked) {
            share.visibility = View.VISIBLE
            bkmark.visibility = View.VISIBLE
            shareText.visibility = View.VISIBLE
            bkmarkText.visibility = View.VISIBLE
        } else {
            share.visibility = View.INVISIBLE
            bkmark.visibility = View.INVISIBLE
            shareText.visibility = View.INVISIBLE
            bkmarkText.visibility = View.INVISIBLE
        }

        if (!clicked) {
            share.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.from_bottom
                )
            )
            bkmark.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.from_bottom
                )
            )
            shareText.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.from_left
                )
            )
            bkmarkText.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.from_left
                )
            )
            btn1.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.rotate_open
                )
            )
        } else {
            share.startAnimation(AnimationUtils.loadAnimation(applicationContext, R.anim.to_bottom))
            bkmark.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.to_bottom
                )
            )

            shareText.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.to_left
                )
            )
            bkmarkText.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.to_left
                )
            )
            btn1.startAnimation(
                AnimationUtils.loadAnimation(
                    applicationContext,
                    R.anim.rotate_close
                )
            )
        }

        if (!clicked) {
            share.isClickable = true
            bkmark.isClickable = true
            shareText.isClickable = true
            bkmarkText.isClickable = true
        } else {
            share.isClickable = false
            bkmark.isClickable = false
            shareText.isClickable = false
            bkmarkText.isClickable = false
        }
        clicked = !clicked
    }
//

}