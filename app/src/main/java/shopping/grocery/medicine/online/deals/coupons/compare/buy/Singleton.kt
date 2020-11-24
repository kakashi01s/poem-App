package shopping.grocery.medicine.online.deals.coupons.compare.buy

import android.app.Application
import android.content.Context
import android.util.Log
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import shopping.grocery.medicine.online.deals.coupons.compare.buy.data.DataFactory
import shopping.grocery.medicine.online.deals.coupons.compare.buy.data.DataService
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Constants
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.ForceUpdateChecker
import io.reactivex.Scheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.util.*

class Singleton : Application() {
    private var dataService: DataService? = null
    private var scheduler: Scheduler? = null

    companion object{

        var application: Singleton? = null

        fun get():Singleton? {
            return application
        }
    }


    override fun onCreate() {
        super.onCreate()
        application = this

        RxJavaPlugins.setErrorHandler { throwable: Throwable ->
            Log.e(
                "TAG",
                "onCreate: " + throwable.message
            )
        }

        AudienceNetworkAds.initialize(this);
        if(BuildConfig.DEBUG){
            AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        }

        MobileAds.initialize(this) { initializationStatus ->
            val statusMap =
                initializationStatus.adapterStatusMap
            for (adapterClass in statusMap.keys) {
                val status = statusMap[adapterClass]
                Log.d("MyApp", String.format(
                    "Adapter name: %s, Description: %s, Latency: %d",
                    adapterClass, status!!.description, status.latency))
            }

            // Start loading ads here...
        }

        if (BuildConfig.DEBUG){
            AdSettings.setTestMode(true);
        }else{
            AdSettings.setTestMode(false);
        }
        val firebaseRemoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        // set in-app defaults

        // set in-app defaults

        // set in-app defaults
        val remoteConfigDefaults = TreeMap<String?, Any?>()
        remoteConfigDefaults[Constants().SHOW_ADS] = false
        remoteConfigDefaults[ForceUpdateChecker().KEY_UPDATE_REQUIRED] = false
        remoteConfigDefaults[ForceUpdateChecker().KEY_CURRENT_VERSION] = "1.0"
        remoteConfigDefaults[ForceUpdateChecker().KEY_UPDATE_URL] =
            "https://play.google.com/store/apps/details?id="+applicationContext.packageName

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults)
        firebaseRemoteConfig.fetch(300) // fetch every minutes
            .addOnCompleteListener(OnCompleteListener<Void?> { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "remote config is fetched.$task")
                    firebaseRemoteConfig.activateFetched()
                    Log.d(
                        "TAG",
                        "onComplete: " + firebaseRemoteConfig.getString("banner_facebook_ads")
                    )
                }
            })


    }


    fun getDataService(): DataService? {
        val dataService by lazy {
            DataFactory.create()
        }
        return dataService
    }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }
        return scheduler
    }


}