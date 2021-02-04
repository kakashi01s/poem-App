package shopping.grocery.medicine.online.deals.coupons.compare.buy.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.onesignal.OneSignal
import shopping.grocery.medicine.online.deals.coupons.compare.buy.BuildConfig
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.BaseActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.CustomViewPager
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.ForceUpdateChecker
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.CategoryViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.HomeViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.DealsViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewpager.AppPagerAdapter

class MainActivity : BaseActivity(), ForceUpdateChecker.OnUpdateNeededListener {
    private val ONESIGNAL_APP_ID ="df46e0a9-5880-4d88-ade5-f973c52a8a0e"
    var viewPager: CustomViewPager? = null
    var viewPagerTab: TabLayout? =null
    var fragmentPagerAdapter: FragmentPagerAdapter ?= null
    var homeViewModel: HomeViewModel? = null
    var dealsViewModel: DealsViewModel? = null
    var categoryViewModel: CategoryViewModel? = null
    var firebaseAnalytics: FirebaseAnalytics? = null
    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupViewPager()

        ForceUpdateChecker().with(this)!!.onUpdateNeeded(this).check()
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        dealsViewModel = ViewModelProvider(this).get(DealsViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        viewPagerTab!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("TAG", "onTabSelected: " + tab.position)
                val bundleAppUsage = Bundle()
                bundleAppUsage.putString("tab_click", tab.text.toString())
                onUpdateLogEvent(bundleAppUsage,"app_usage",false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    fun initViews(){
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        viewPager = findViewById(R.id.vpPager)
        viewPagerTab = findViewById(R.id.view_pager_tab)
    }

    fun setupViewPager(){
        fragmentPagerAdapter = AppPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = fragmentPagerAdapter
        val limit = if ((fragmentPagerAdapter as AppPagerAdapter).getCount() > 1) (fragmentPagerAdapter as AppPagerAdapter).getCount() - 1 else 1
        viewPager!!.offscreenPageLimit = limit;
        viewPager!!.currentItem = 1;

        viewPager!!.setSwipePagingEnabled(false)

        viewPagerTab!!.setupWithViewPager(viewPager)

    }

    override fun onDestroy() {
        homeViewModel?.reset()
        dealsViewModel?.reset()
        categoryViewModel?.reset()
        super.onDestroy()

    }

    override fun onUpdateNeeded(updateUrl: String?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("New version available")
            .setMessage("Please, update app to new version to continue shopping.")
            .setPositiveButton(
                "Update"
            ) { dialog, which -> redirectStore(updateUrl!!) }.setNegativeButton(
                "No, thanks"
            ) { dialog, which -> finish() }.create()
        dialog.show()

    }

    private fun redirectStore(updateUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun onUpdateLogEvent(bundle: Bundle, eventName: String, isUrlVisited: Boolean){
        Log.d("TAG", "onUpdateLogEvent: ")
        if(BuildConfig.DEBUG){
            return
        }
        else{
            if(isUrlVisited){
                firebaseAnalytics!!.logEvent(eventName, bundle)
                firebaseAnalytics!!.logEvent("url_visited", bundle)
            }
            else
                firebaseAnalytics!!.logEvent(eventName, bundle)
        }
    }

}
