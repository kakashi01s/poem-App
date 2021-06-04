package shopping.grocery.medicine.online.deals.coupons.compare.buy.view

import android.app.Dialog
import android.content.ActivityNotFoundException
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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment
import com.yalantis.contextmenu.lib.MenuObject
import com.yalantis.contextmenu.lib.MenuParams
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bookmark_layout.*
import me.toptas.fancyshowcase.FancyShowCaseView
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.BaseActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Constants
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.CustomViewPager
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.ForceUpdateChecker
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Pref
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter.home.AllAppsAdapter
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

    private lateinit var contextMenuDialogFragment: ContextMenuDialogFragment

    private lateinit var bottomNav: ExpandableBottomBar

    var search_rvCountryStores: RecyclerView? = null

    var appsList: ArrayList<List<String>>? = ArrayList()

    val filterList: ArrayList<List<String>> = ArrayList()

    var dialog: Dialog? = null

    var allAppsAdapter: AllAppsAdapter? = null

    lateinit var search: ImageView

    private var searchTxt: String? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null

    override val bindingVariable: Int
        get() = 0
    override val layoutId: Int
        get() = 0
    var bool: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()

//        initToolbar()
        initMenuFragment()

        Pref.initializeInstance(this)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        if (Pref.instance!!.dataChanged!!) {
            Pref.instance!!.dataChanged = false
        }

        if(firebaseRemoteConfig!!.getBoolean(Constants().DATA_CHANGED)){
            Pref.instance!!.dataChanged = true
        }

        dialog = Dialog(this)

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
                t.getValues()?.let { appsList?.addAll(it) }
            }
        })

        search.setOnClickListener {
            onShowStores(appsList!!)
        }
//
        viewPagerTab!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("TAG", "onTabSelected: " + tab.position)
                val bundleAppUsage = Bundle()
                bundleAppUsage.putString("tab_click", tab.text.toString())
                onUpdateLogEvent(bundleAppUsage, "app_usage", false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        setupViewPager()

        val menu = bottomNav.menu

        menu.select(R.id.id_home)
//        viewPager!!.currentItem = 0


        bottomNav.onItemSelectedListener = { view, menuItem, bool ->
            when (menuItem.id) {
                R.id.id_home -> {

                    viewPager!!.currentItem = 0
                    fancy(view, "Home")

                }
                R.id.id_category -> {

                    viewPager!!.currentItem = 1
                    fancy(view, "Category")
                }
                R.id.id_deals -> {

                    viewPager!!.currentItem = 2
                    fancy(view, "Deals")
                }
                R.id.id_bookmark -> {

                    viewPager!!.currentItem = 3
                    fancy(view, "Deals")
                }
            }
        }

        ivMenu.setOnClickListener {
            showContextMenuDialogFragment()
        }
    }

    private fun initViews() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        bottomNav = findViewById(R.id.expandable_bottom_bar)
        viewPager = findViewById(R.id.vpPager)
        viewPagerTab = findViewById(R.id.view_pager_tab)
        search = findViewById(R.id.search)
    }

//    private fun initToolbar() {
//        setSupportActionBar(toolbar)
//
//        supportActionBar?.apply {
//            setHomeButtonEnabled(false)
//            setDisplayHomeAsUpEnabled(false)
//            setDisplayShowTitleEnabled(false)
//        }
//    }

    private fun fancy(it: View, title: String) {
        return FancyShowCaseView.Builder(this).focusOn(it).title(title).delay(50).showOnce(title)
            .build()
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
            searchTxt = searchChar

//            val itemsModal = ArrayList<List<String>>()
//
//            for (item in filterList) {
//                if (item[1].toLowerCase().contains(searchChar)) {
//                    Log.d("filterdone", item[1])
//                    Log.d("filterChar", searchChar)
//                    itemsModal.add(item)
//                }
//            }
//
//            appsList!!.clear()
//            appsList!!.addAll(itemsModal)
//            Log.d("filterList", itemsModal.toString())
//            allAppsAdapter!!.setItems(appsList)
//            allAppsAdapter!!.notifyDataSetChanged()
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
        viewPager!!.currentItem = 0

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
                intent.putExtra("color", item[4])
                if (searchTxt != null) {
                    intent.putExtra("search_url", item[5] + "$searchTxt")
                }
                startActivity(intent)
            }
        } else {
            val intent: Intent = Intent(this, WebActivity::class.java)
            intent.putExtra("title", item[1])
            intent.putExtra("url", item[2])
            intent.putExtra("app_icon", item[3])
            intent.putExtra("color", item[4])
            if (searchTxt != null) {
                intent.putExtra("search_url", item[5] + "$searchTxt")
            }

            startActivity(intent)
        }

        val bundle = Bundle()
        bundle.putString("title", item[1])
        bundle.putString("url", item[2])

        this.onUpdateLogEvent(bundle, "all_apps_visited", true)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu2, menu)
//        Log.d("menuInflate", "done")
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        item.let {
//            when (it.itemId) {
//                R.id.context_menu -> {
//                    Log.d("Menu Item", it.itemId.toString())
//                    showContextMenuDialogFragment()
//                }
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

    private fun initMenuFragment() {
        val menuParams = MenuParams(
            actionBarSize = resources.getDimension(R.dimen.menu).toInt(),
            menuObjects = getMenuObjects(),
            isClosableOutside = true
        )

        contextMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams).apply {
            menuItemClickListener = { view, position ->
                when(position){
                    1 -> {
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6823602592155636380")))
                        } catch (e: ActivityNotFoundException) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=6823602592155636380")))
                        }
                    }
                    2 -> {
                        try {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=shopping.grocery.medicine.online.deals.coupons.compare.buy")))
                        } catch (e: ActivityNotFoundException) {
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=shopping.grocery.medicine.online.deals.coupons.compare.buy")))
                        }
                    }
                    2 -> {
                        val i = Intent(Intent.ACTION_SEND)
                        i.type = "message/rfc822"
                        i.putExtra(Intent.EXTRA_EMAIL, arrayOf("infinitywebapps@gmail.com"))
                        i.putExtra(Intent.EXTRA_SUBJECT, "Feedback "+resources.getString(R.string.app_name))
                        i.putExtra(Intent.EXTRA_TEXT, "Feedback :: ")
                        try {
                            startActivity(Intent.createChooser(i, "Send feedback..."))
                        } catch (ex: ActivityNotFoundException) {
                            Toast.makeText(
                                this@MainActivity,
                                "There are no email clients installed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    2 -> {
                        val intent = Intent(Intent.ACTION_SEND)
                        /*This will be the actual content you wish you share.*/
                        /*This will be the actual content you wish you share.*/
                        val shareBody = "Use this "
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

                }
            }
        }
    }


    private fun getMenuObjects() = mutableListOf<MenuObject>().apply {
        val close = MenuObject().apply {
            setResourceValue(R.drawable.ic_baseline_close)
            setBgColorValue(resources.getColor(R.color.colorPrimary))
        }
        val moreApps = MenuObject("More apps").apply {
            setResourceValue(R.drawable.ic_baseline_store)
            setBgColorValue(resources.getColor(R.color.colorPrimary))
        }
        val rateUs = MenuObject("Rate Us").apply {
            setResourceValue(R.drawable.ic_baseline_star_rate)
            setBgColorValue(resources.getColor(R.color.colorPrimary))
        }
        val feedback = MenuObject("Feedback").apply {
            setResourceValue(R.drawable.ic_baseline_feedback)
            setBgColorValue(resources.getColor(R.color.colorPrimary))
        }
        val share = MenuObject("Share").apply {
            setResourceValue(R.drawable.ic_share)
            setBgColorValue(resources.getColor(R.color.colorPrimary))
        }

        add(close)
        add(moreApps)
        add(rateUs)
        add(feedback)
        add(share)
    }

    private fun showContextMenuDialogFragment() {
        Log.d("TAG", "showContextMenuDialogFragment: ")
        if (supportFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
            contextMenuDialogFragment.show(supportFragmentManager, ContextMenuDialogFragment.TAG)
        }
    }
}



