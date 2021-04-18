package shopping.grocery.medicine.online.deals.coupons.compare.buy.utils

import com.firebase.ui.common.BuildConfig
//import shopping.grocery.medicine.online.deals.coupons.compare.buy.BuildConfig


class Constants {

    val SHOW_ADS = "show_ads"
    val OPEN_BROWSER = "open_browser"


    val FB_ADS_TEST = "VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID"
    val FB_BANNER_TEST = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
    val FB_NATIVE_HOME_1 = "722413805027589_755158915086411"
    val FB_NATIVE_HOME_2 = "722413805027589_755162745086028"
    val FB_NATIVE_CAT_1 = "722413805027589_755163058419330"
    val FB_NATIVE_CAT_2 = "722413805027589_755163241752645"
    val FB_NATIVE_DAILOG = "722413805027589_841024753166493"
    val FB_BANNER_WEB = "722413805027589_755223338413302"
    val FB_INTERSTITIAL_WEB_EXIT = "722413805027589_755222675080035"

    fun getFbNativeHome1(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_HOME_1
    }

    fun getFbNativeHome2(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_HOME_2
    }

    fun getFbNativeCat1(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_CAT_1
    }
    fun getFbNativeCat2(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_CAT_2
    }
    fun getFbNativeDailog(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_NATIVE_DAILOG
    }


    fun getFbBannerWeb(): String {
        return if (BuildConfig.DEBUG)
            FB_BANNER_TEST
        else
            FB_BANNER_WEB
    }

    fun getFbInterstitialWebExit(): String {
        return if (BuildConfig.DEBUG)
            FB_ADS_TEST
        else
            FB_INTERSTITIAL_WEB_EXIT
    }







}