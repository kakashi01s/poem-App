package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.*
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.dialog_show_stores.*
import kotlinx.android.synthetic.main.fragment_category.*
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.BaseFragment
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.utils.Constants
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.MainActivity
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.WebActivity
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.adapter.CategoryStoresAdapter
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.listener.CategoryStoresItemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.viewmodel.CategoryViewModel



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopicsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : BaseFragment(), CategoryStoresItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null


    var rvCategoryStores: RecyclerView? = null
    var categoryStoresAdapter: CategoryStoresAdapter? = null
    var categoryViewModel: CategoryViewModel? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var currentNativeAd: com.google.android.gms.ads.nativead.NativeAd? = null
    var currentNativeAd2: com.google.android.gms.ads.nativead.NativeAd? = null
    var dialogNativeAd: com.google.android.gms.ads.nativead.NativeAd? = null


    var llSuperMarts: LinearLayout? = null
    var llGroceries: LinearLayout? = null
    var llMedicines: LinearLayout? = null
    var llSupplements: LinearLayout? = null
    var llElectronics: LinearLayout? = null
    var llBeauty: LinearLayout? = null
    var llJewellery: LinearLayout? = null
    var llKitchenAppliances: LinearLayout? = null
    var llKidsLifestyle: LinearLayout? = null
    var llBabyToys: LinearLayout? = null
    var llLingerie: LinearLayout? = null
    var llMenInnerWear: LinearLayout? = null
    var llBooks: LinearLayout? = null
    var llFootwear: LinearLayout? = null

    var superMartList: ArrayList<List<String>>? = ArrayList()
    var groceriesList: ArrayList<List<String>>? = ArrayList()
    var medicinesList: ArrayList<List<String>>? = ArrayList()
    var supplementsList: ArrayList<List<String>>? = ArrayList()
    var electronicsList: ArrayList<List<String>>? = ArrayList()
    var beautyList: ArrayList<List<String>>? = ArrayList()
    var jewelleryList: ArrayList<List<String>>? = ArrayList()
    var kitchenAppliancesList: ArrayList<List<String>>? = ArrayList()
    var kidsLifestyleList: ArrayList<List<String>>? = ArrayList()
    var babyToysList: ArrayList<List<String>>? = ArrayList()
    var lingerieList: ArrayList<List<String>>? = ArrayList()
    var menInnerWearList: ArrayList<List<String>>? = ArrayList()
    var booksList: ArrayList<List<String>>? = ArrayList()
    var footwearList: ArrayList<List<String>>? = ArrayList()


    var dialog: Dialog? = null
//
//    var nativeAdCat1: UnifiedNativeAd? = null
//    var nativeAdCat2: UnifiedNativeAd? = null

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
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        if(firebaseRemoteConfig == null){
            firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        }

        dialog = Dialog(requireContext())
        dialog!!.setContentView(R.layout.dialog_show_stores)
        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)

        categoryViewModel = ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
        categoryViewModel?.loadData()

        categoryViewModel!!.superMartLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: superMartLiveData $t")
            superMartList!!.addAll(t!!)
        })

        categoryViewModel!!.groceriesLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: groceriesLiveData $t")
            groceriesList!!.addAll(t!!)
        })
        categoryViewModel!!.medicinesLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: medicinesLiveData $t")
            medicinesList!!.addAll(t!!)
        })

        categoryViewModel!!.supplementsLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: supplementsLiveData $t")
            supplementsList!!.addAll(t!!)
        })
        categoryViewModel!!.electronicsLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: electronicsLiveData $t")
            electronicsList!!.addAll(t!!)
        })

        categoryViewModel!!.beautyLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: beautyLiveData $t")
            beautyList!!.addAll(t!!)
        })
        categoryViewModel!!.jewelleryLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: jewelleryLiveData $t")
            jewelleryList!!.addAll(t!!)
        })

        categoryViewModel!!.kitchenAppliancesLiveData
            .observe(viewLifecycleOwner, Observer { t ->
                Log.d("TAG", "onViewCreated: kitchenAppliancesLiveData $t")
                kitchenAppliancesList!!.addAll(t!!)
            })
        categoryViewModel!!.kidsLifestyleLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: kidsLifestyleLiveData $t")
            kidsLifestyleList!!.addAll(t!!)
        })

        categoryViewModel!!.babyToysLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: babyToysLiveData $t")
            babyToysList!!.addAll(t!!)
        })
        categoryViewModel!!.lingerieLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: lingerieLiveData $t")
            lingerieList!!.addAll(t!!)
        })

        categoryViewModel!!.menInnerWearLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: menInnerWearLiveData $t")
            menInnerWearList!!.addAll(t!!)
        })
        categoryViewModel!!.booksLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: booksLiveData $t")
            booksList!!.addAll(t!!)
        })

        categoryViewModel!!.footwearLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: footwearLiveData $t")
            footwearList!!.addAll(t!!)
        })


        llSuperMarts!!.setOnClickListener {
            onShowStores(superMartList!!,view)
        }
        llGroceries!!.setOnClickListener {
            onShowStores(groceriesList!!,view)
        }
        llMedicines!!.setOnClickListener {
            onShowStores(medicinesList!!,view)
        }
        llSupplements!!.setOnClickListener {
            onShowStores(supplementsList!!,view)
        }
        llElectronics!!.setOnClickListener {
            onShowStores(electronicsList!!,view)
        }
        llBeauty!!.setOnClickListener {
            onShowStores(beautyList!!,view)
        }
        llJewellery!!.setOnClickListener {
            onShowStores(jewelleryList!!,view)
        }
        llKitchenAppliances!!.setOnClickListener {
            onShowStores(kitchenAppliancesList!!,view)
        }
        llKidsLifestyle!!.setOnClickListener {
            onShowStores(kidsLifestyleList!!,view)
        }
        llBabyToys!!.setOnClickListener {
            onShowStores(babyToysList!!,view)
        }
        llLingerie!!.setOnClickListener {
            onShowStores(lingerieList!!,view)
        }
        llMenInnerWear!!.setOnClickListener {
            onShowStores(menInnerWearList!!,view)
        }
        llBooks!!.setOnClickListener {
            onShowStores(booksList!!,view)
        }
        llFootwear!!.setOnClickListener {
            onShowStores(footwearList!!,view)
        }

    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            if(firebaseRemoteConfig == null){
                firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            }
            if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {

                onLoadNativeAd1()
                onLoadNativeAd2()
            }

        }
    }


    fun initViews(view: View){
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())
        llSuperMarts = view.findViewById(R.id.llSuperMarts)
        llGroceries = view.findViewById(R.id.llGroceries)
        llMedicines = view.findViewById(R.id.llMedicines)
        llSupplements = view.findViewById(R.id.llSupplements)
        llElectronics = view.findViewById(R.id.llElectronics)
        llBeauty = view.findViewById(R.id.llBeauty)
        llJewellery = view.findViewById(R.id.llJewellery)
        llKitchenAppliances = view.findViewById(R.id.llKitchenAppliances)
        llKidsLifestyle = view.findViewById(R.id.llKidsLifestyle)
        llBabyToys = view.findViewById(R.id.llBabyToys)
        llLingerie = view.findViewById(R.id.llLingerie)
        llMenInnerWear = view.findViewById(R.id.llMenInnerWear)
        llBooks = view.findViewById(R.id.llBooks)
        llFootwear = view.findViewById(R.id.llFootwear)

    }

    fun setRecyclerView(){
        categoryStoresAdapter = CategoryStoresAdapter(context)
        categoryStoresAdapter!!.setListener(this)
        rvCategoryStores.apply {
            rvCategoryStores?.layoutManager = GridLayoutManager(activity, 3)
            rvCategoryStores?.adapter = categoryStoresAdapter
        }
    }

    fun onShowStores(list: ArrayList<List<String>>, view: View){
        rvCategoryStores = dialog!!.findViewById(R.id.rvCategoryStores)

        setRecyclerView()

        categoryStoresAdapter!!.setItems(list)

        dialog!!.show()

        if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {
            onLoadNativeAdDialog(dialog!!)
        }

    }

    fun onLoadNativeAdDialog(dialog: Dialog){
        val builder = AdLoader.Builder(context, Constants().getFbNativeDailog())
            .forNativeAd { nativeAd ->
                // Assumes that your ad layout is in a file call native_ad_layout.xml
                // in the res/layout folder
                dialogNativeAd?.destroy()
                dialogNativeAd = nativeAd
                val adView = layoutInflater
                    .inflate(R.layout.ad_unified, null) as com.google.android.gms.ads.nativead.NativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
                populateNativeAdView(nativeAd, adView)
                // Assumes you have a placeholder FrameLayout in your View layout
                // (with id ad_frame) where the ad is to be placed.
                dialog.ad_frame_dialog.removeAllViews()
                dialog.ad_frame_dialog.addView(adView)
            }

        val adLoader = builder.withAdListener(object : com.google.android.gms.ads.AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d("TAG", "onAdFailedToLoad: ad_frame_home_1"+loadAdError.message)
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    fun onLoadNativeAd1(){
        val builder = AdLoader.Builder(context, Constants().getFbNativeCat1())
            .forNativeAd { nativeAd ->
                // Assumes that your ad layout is in a file call native_ad_layout.xml
                // in the res/layout folder
                currentNativeAd?.destroy()
                currentNativeAd = nativeAd
                val adView = layoutInflater
                    .inflate(R.layout.ad_unified, null) as com.google.android.gms.ads.nativead.NativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
                populateNativeAdView(nativeAd, adView)
                // Assumes you have a placeholder FrameLayout in your View layout
                // (with id ad_frame) where the ad is to be placed.
                ad_frame_cat_1.removeAllViews()
                ad_frame_cat_1.addView(adView)
            }

        val adLoader = builder.withAdListener(object :  com.google.android.gms.ads.AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d("TAG", "onAdFailedToLoad: ad_frame_shayri_1"+loadAdError.message)
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }
    fun onLoadNativeAd2(){
        val builder = AdLoader.Builder(context, Constants().getFbNativeCat2())
            .forNativeAd { nativeAd ->
                // Assumes that your ad layout is in a file call native_ad_layout.xml
                // in the res/layout folder
                currentNativeAd?.destroy()
                currentNativeAd = nativeAd
                val adView = layoutInflater
                    .inflate(R.layout.ad_unified, null) as com.google.android.gms.ads.nativead.NativeAdView
                // This method sets the text, images and the native ad, etc into the ad
                // view.
                populateNativeAdView(nativeAd, adView)
                // Assumes you have a placeholder FrameLayout in your View layout
                // (with id ad_frame) where the ad is to be placed.
                ad_frame_cat_2.removeAllViews()
                ad_frame_cat_2.addView(adView)
            }

        val adLoader = builder.withAdListener(object :  com.google.android.gms.ads.AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d("TAG", "onAdFailedToLoad: ad_frame_cat_2"+loadAdError.message)
            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    private fun populateNativeAdView(nativeAd: NativeAd, adView: com.google.android.gms.ads.nativead.NativeAdView) {
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
        categoryViewModel?.reset()
        super.onDestroy()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun CategoryStoresCardClick(item: List<String>) {
        Log.d("TAG", "onAllBrokersCardClick: " + item.get(1))

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(2))
        (activity as MainActivity?)!!.onUpdateLogEvent(bundle,"brokers_visited",true)

        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(2))
        startActivity(intent)
    }
}

