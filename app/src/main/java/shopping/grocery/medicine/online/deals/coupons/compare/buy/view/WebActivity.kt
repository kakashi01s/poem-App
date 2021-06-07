package shopping.grocery.medicine.online.deals.coupons.compare.buy.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.ads.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.custom_toast.*
import me.toptas.fancyshowcase.FancyShowCaseView
import me.toptas.fancyshowcase.FocusShape
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.model.bookmark.Bookmarks
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Constants
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Helper


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

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

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

        val typeface =
            ResourcesCompat.getFont(this, R.font.montserrat_semibold)

        FancyShowCaseView.Builder(this)
            .focusOn(btn1)
            .focusShape(FocusShape.CIRCLE)
            .roundRectRadius(90)
            .titleGravity(Gravity.CENTER)
            .titleSize(28, TypedValue.COMPLEX_UNIT_SP)
            .typeface(typeface)
            .enableAutoTextPosition()
            .showOnce("FANCY_FAB")
            .title("Click to add this item to your wishlist to explore later and also to share this item where you want!!")
            .build()

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        sharedPreferences =
            getSharedPreferences(Constants().BOOKMARKS_DATA, MODE_PRIVATE)
        editor = sharedPreferences!!.edit()

        bkmark.isEnabled = false

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

            onButtonClicked()

            val intent = Intent(Intent.ACTION_SEND)
            /*This will be the actual content you wish you share.*/
            /*This will be the actual content you wish you share.*/
            val shareBody = "Hey!! checkout this product"+"\n"+ webView!!.url
            /*The type of the content is text, obviously.*/
            /*The type of the content is text, obviously.*/intent.type = "text/plain"
            /*Applying information Subject and Body.*/
            /*Applying information Subject and Body.*/
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            /*Fire!*/
            /*Fire!*/startActivity(
            Intent.createChooser(
                intent,
                "Share via"
            )
        )
        }

        bkmark.setOnClickListener {

            onButtonClicked()

            Log.d(TAG, "onCreate: bookmarks " + bkmark.tag)

            if (bkmark.tag as Int === R.drawable.ic_bookmark) {
                val bookmarks: Bookmarks? = Bookmarks()
                bookmarks!!.bookmarkTitle = webView!!.title
                bookmarks.bookmarkStoreTitle = appTitle
                bookmarks.bookmarkUrl = webView!!.url
                bookmarks.setBookmarkUrlWithoutAffiliate(getUrlWithoutParameters(webView!!.url!!))
                bookmarks.bookmarkLogo = appIcon
                bookmarks.webSplash = color

                bookmarksList!!.add(bookmarks)

                val bundle = Bundle()
                bundle.putString("bookmarkTitle", webView!!.title)
                bundle.putString("bookmarkStoreTitle", appTitle)
                bundle.putString("bookmarkUrl", webView!!.url)
                firebaseAnalytics!!.logEvent("bookmarks_Usage", bundle)

                setBookmarks()

                Helper().makeToast("Item added to your wishlist", this@WebActivity)
                bkmark.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_fill))
                bkmark.tag = R.drawable.ic_baseline_bookmark_fill
            } else {
                bkmark.setImageDrawable(resources.getDrawable(R.drawable.ic_bookmark))
                bkmark.tag = R.drawable.ic_bookmark
                removeBookmark(webView!!.url)
            }
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
        if (bundle?.getString("url") != null) {
            appUrl = bundle?.getString("url")
        }
        if (bundle?.getString("app_icon") != null) {
            appIcon = bundle?.getString("app_icon")
        }
        if (bundle?.getString("title") != null) {
            appTitle = bundle?.getString("title")
        }
        if (bundle?.getString("color") != null) {
            color = bundle?.getString("color")
        }
        if (bundle?.getString("search_url") != null) {
            searchUrl = bundle?.getString("search_url")
        }

    }

    private fun setBookmarks() {
        Log.d("ShareBookmark", bookmarksList.toString())
        editor!!.putString("Bookmarks", Gson().toJson(bookmarksList)).apply()
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
                    Helper().makeToast("Item removed from wishlist", this@WebActivity)
                }
                i++
            }
            setBookmarks()
        }
    }

    fun isBookmarked(webUrl: String?): Boolean {
        getBookmarks()
        if (bookmarksList != null) {
            var i = 0
            while (i < bookmarksList!!.size) {
                val bookmarkData: Bookmarks = bookmarksList!![i]
                Log.d(TAG, "isBookmarked: " + bookmarkData.getBookmarkUrlWithoutAffiliate())
                if (bookmarkData.getBookmarkUrlWithoutAffiliate() != null && bookmarkData.getBookmarkUrlWithoutAffiliate()
                        .equals(webUrl)
                ) {
                    return true
                }
                i++
            }
        }
        return false
    }

    private fun getUrlWithoutParameters(url: String): String? {
        try {
            return if (url.contains("?")) {
                url.substring(0, url.indexOf("?"))
            } else {
                url
            }
        } catch (s: StringIndexOutOfBoundsException) {
            Log.d("TAG", "getUrlWithoutParameters: " + s.localizedMessage)
        }
        return null
    }


    private fun getBookmarks() {
        if (bookmarksList != null && !bookmarksList!!.isEmpty()) {
            bookmarksList!!.clear()
        }
        if (sharedPreferences!!.getString("Bookmarks", null) != null) {
            val serializedObject: String = sharedPreferences!!.getString("Bookmarks", null)!!
            val gson = Gson()
            val type = object : TypeToken<ArrayList<Bookmarks?>?>() {}.type
            bookmarksList = gson.fromJson<ArrayList<Bookmarks>>(serializedObject, type)
            Log.d(TAG, "getBookmarks: " + bookmarksList!!.size)
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

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (isBookmarked(getUrlWithoutParameters(view!!.getUrl()!!))) {
                    bkmark.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_fill))
                    bkmark.tag = R.drawable.ic_baseline_bookmark_fill
                } else {
                    bkmark.setImageDrawable(resources.getDrawable(R.drawable.ic_bookmark))
                    bkmark.tag = R.drawable.ic_bookmark
                }
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
                if (newProgress >= 90) {
                    rlWebSplash!!.visibility = View.GONE
                    bkmark.setEnabled(true)
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

    @SuppressLint("ResourceType")
    fun loadWebSplash() {

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setStatusBarColor(Color.parseColor(color))
        }


        if (color != null) {
            rlWebSplash!!.setBackgroundColor(Color.parseColor(color))
        }

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