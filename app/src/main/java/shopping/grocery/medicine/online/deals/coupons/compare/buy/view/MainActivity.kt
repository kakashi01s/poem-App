package shopping.grocery.medicine.online.deals.coupons.compare.buy.view

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.BuildConfig
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import me.toptas.fancyshowcase.FancyShowCaseView
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.BaseActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.CustomViewPager
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.ForceUpdateChecker
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter.home.AllAppsAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.CategoryFragment
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.DealFragment
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment.FragmentHome
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.AllAppsItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.CategoryViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.DealsViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.GlobalViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.HomeViewModel
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewpager.AppPagerAdapter


class MainActivity : BaseActivity(), AllAppsItemClickListener<List<String>>,
    ForceUpdateChecker.OnUpdateNeededListener {

    var viewPager: CustomViewPager? = null
    var viewPagerTab: TabLayout? = null
    var fragmentPagerAdapter: FragmentPagerAdapter? = null
    var homeViewModel: HomeViewModel? = null
    var globalViewModel: GlobalViewModel? = null
    var dealsViewModel: DealsViewModel? = null
    var categoryViewModel: CategoryViewModel? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    private lateinit var bottomNav: ExpandableBottomBar


    var search_rvCountryStores: RecyclerView? = null

    var appsList: ArrayList<List<String>>? = ArrayList()

    val filterList: ArrayList<List<String>> = ArrayList()

    var dialog: Dialog? = null

    var allAppsAdapter: AllAppsAdapter? = null

    lateinit var search: ImageView

    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0
    var bool: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        dialog = Dialog(this)

//        setupViewPager()

        ForceUpdateChecker().with(this)!!.onUpdateNeeded(this).check()

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        dealsViewModel = ViewModelProvider(this).get(DealsViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        globalViewModel = ViewModelProvider(this).get(GlobalViewModel::class.java)


        homeViewModel?.loadData()

        homeViewModel!!.allAppsLiveData.observe(this, Observer { t ->
            Log.d("TAG", "HomeFragment Live allAppsLiveData$t")
            appsList!!.clear()
            if (t != null) {
                appsList?.addAll(t)
            }
        })

        search.setOnClickListener {
            onShowStores(appsList!!)
        }

//        viewPagerTab!!.addOnTabSelectedListener(object : OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                Log.d("TAG", "onTabSelected: " + tab.position)
//                val bundleAppUsage = Bundle()
//                bundleAppUsage.putString("tab_click", tab.text.toString())
//                onUpdateLogEvent(bundleAppUsage, "app_usage", false)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })


        var menu = bottomNav.menu
        menu.select(R.id.id_home)
        supportFragmentManager.beginTransaction().replace(R.id.frame, FragmentHome())
            .commit()

        bottomNav.onItemSelectedListener = { view, menuItem, bool ->
            when (menuItem.id) {
                R.id.id_home -> {
                    fancy(view, "Home")
                    supportFragmentManager.beginTransaction().replace(R.id.frame, FragmentHome())
                        .commit()
                }
                R.id.id_category -> {
                    fancy(view, "Category")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, CategoryFragment())
                        .commit()

                }
                R.id.id_deals -> {
                    fancy(view, "Deals")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, DealFragment())
                        .commit()
                }
            }
        }
        search.setOnClickListener {
        }
    }

    private fun initViews() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        bottomNav = findViewById(R.id.expandable_bottom_bar)
//        viewPager = findViewById(R.id.vpPager)
//        viewPagerTab = findViewById(R.id.view_pager_tab)
        search = findViewById(R.id.search)
    }

    private fun fancy(it: View, title : String) {
        return FancyShowCaseView.Builder(this).focusOn(it).title(title).delay(50).build()
            .show()
    }

    private fun onShowStores(list: ArrayList<List<String>>) {
        dialog!!.setContentView(R.layout.dialog_search)

        dialog!!.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        );

        search_rvCountryStores = dialog!!.findViewById(R.id.search_rvCategoryStores)

        setRecyclerView()

        allAppsAdapter!!.setItems(list)

        dialog!!.show()

    }

    private fun setRecyclerView() {
        allAppsAdapter = AllAppsAdapter(applicationContext)
        allAppsAdapter!!.setListener(this)
        search_rvCountryStores.apply {
            search_rvCountryStores?.layoutManager = GridLayoutManager(this@MainActivity, 3)
            search_rvCountryStores?.adapter = allAppsAdapter

            val appSearchView: EditText = dialog!!.findViewById(R.id.app_search)
            filterList.clear()
            filterList.addAll(appsList!!)

            appSearchView.addTextChangedListener(textwatcher)
        }
    }

    private val textwatcher = object : TextWatcher {


        override fun beforeTextChanged(
            charSequence: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(
            charSequence: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {

            Log.d("Filter", filterList.toString())

            val searchChar = charSequence.toString().toLowerCase()
            Log.d("filterSea", searchChar)

            val itemsModal = ArrayList<List<String>>()

            for (item in filterList) {
                if (item[1].toLowerCase().contains(searchChar)) {
                    Log.d("filterdone", item[1])
                    Log.d("filterChar", searchChar)
                    itemsModal.add(item)
                }
            }

            appsList!!.clear()
            appsList!!.addAll(itemsModal)
            Log.d("filterList", itemsModal.toString())
            allAppsAdapter!!.setItems(appsList)
            allAppsAdapter!!.notifyDataSetChanged()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun setupViewPager() {
        fragmentPagerAdapter = AppPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = fragmentPagerAdapter
        val limit =
            if ((fragmentPagerAdapter as AppPagerAdapter).count > 1) (fragmentPagerAdapter as AppPagerAdapter).count - 1 else 1
        viewPager!!.offscreenPageLimit = limit;
        viewPager!!.currentItem = 1;

        viewPager!!.setSwipePagingEnabled(false)

        viewPagerTab!!.setupWithViewPager(viewPager)

    }

    override fun onDestroy() {
        homeViewModel?.reset()
        globalViewModel?.reset()
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

    fun onUpdateLogEvent(bundle: Bundle, eventName: String, isUrlVisited: Boolean) {
        Log.d("TAG", "onUpdateLogEvent: ")
        if (BuildConfig.DEBUG) {
            return
        } else {
            if (isUrlVisited) {
                firebaseAnalytics!!.logEvent(eventName, bundle)
                firebaseAnalytics!!.logEvent("url_visited", bundle)
            } else
                firebaseAnalytics!!.logEvent(eventName, bundle)
        }
    }

    override fun onAllCardClick(item: List<String>) {
        Log.d("TAG", "onAllCardClick: " + item[1])

        if (item[1] == "Amazon") {

            if (bool) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item[2]))
                startActivity(browserIntent)
            } else {
                val intent: Intent = Intent(this, WebActivity::class.java)
                intent.putExtra("title", item[1])
                intent.putExtra("url", item[2])
                intent.putExtra("app_icon", item[3])

                startActivity(intent)
            }
        } else {
            val intent: Intent = Intent(this, WebActivity::class.java)
            intent.putExtra("title", item[1])
            intent.putExtra("url", item[2])
            intent.putExtra("app_icon", item[3])

            startActivity(intent)
        }

        val bundle = Bundle()
        bundle.putString("title", item[1])
        bundle.putString("url", item[2])

        this.onUpdateLogEvent(bundle, "all_apps_visited", true)
    }


}
