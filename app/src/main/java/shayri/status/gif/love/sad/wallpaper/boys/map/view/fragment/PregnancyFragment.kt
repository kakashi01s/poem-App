package shayri.status.gif.love.sad.wallpaper.boys.map.view.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.lifecycle.Observer
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.fragment_pregnancy.*
import shayri.status.gif.love.sad.wallpaper.boys.map.viewmodel.pregnancyViewModel
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.BaseFragment
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.utils.Constants
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.MainActivity
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.WebActivity
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.adapter.CategoryStoresAdapter
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.listener.CategoryStoresItemClickListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PregnancyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */



class PregnancyFragment : BaseFragment() ,
    CategoryStoresItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    var pregnancyViewModel : pregnancyViewModel? = null
    var rvCategoryStores: RecyclerView? = null
    var categoryStoresAdapter: CategoryStoresAdapter? = null




    var AdviceList: ArrayList<List<String>>? = ArrayList()
    var AfterList: ArrayList<List<String>>? = ArrayList()
    var CalculatorList: ArrayList<List<String>>? = ArrayList()
    var DietList: ArrayList<List<String>>? = ArrayList()
    var DuedateList: ArrayList<List<String>>? = ArrayList()
    var ExerciesList: ArrayList<List<String>>? = ArrayList()
    var MiscarriageList: ArrayList<List<String>>? = ArrayList()
    var precautionList: ArrayList<List<String>>? = ArrayList()
    var symptomsList: ArrayList<List<String>>? = ArrayList()
    var pregnancyCalenderList: ArrayList<List<String>>? = ArrayList()
    var TestList: ArrayList<List<String>>? = ArrayList()
    var WeightList: ArrayList<List<String>>? = ArrayList()


    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null
    private var nativeAdFB1: NativeAd? = null
    private var nativeAdFB2: NativeAd? = null
    private var nativeAdFB3: NativeAd? = null
    private var nativeAdFB4: NativeAd? = null

    private var nativeAdLayout: NativeAdLayout? = null
    private var adView: LinearLayout? = null

    var dialog: Dialog? = null

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
        return inflater.inflate(R.layout.fragment_pregnancy, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews(view)

        if (firebaseRemoteConfig == null) {
            firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

            dialog = Dialog(requireContext())

            pregnancyViewModel = ViewModelProvider(requireActivity()).get(shayri.status.gif.love.sad.wallpaper.boys.map.viewmodel.pregnancyViewModel::class.java)
            pregnancyViewModel?.loadData()

            pregnancyViewModel!!.AfterLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: usaLiveData $t")
                AfterList!!.addAll(t!!)
            })
            pregnancyViewModel!!.AdviceLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: russiaLiveData $t")
                AdviceList!!.addAll(t!!)
            })

            pregnancyViewModel!!.CalculatorLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: pakistanLiveData $t")
                CalculatorList!!.addAll(t!!)
            })
            pregnancyViewModel!!.MiscarriageLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: chinaLiveData $t")
                MiscarriageList!!.addAll(t!!)
            })

            pregnancyViewModel!!.DietLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: germanyLiveData $t")
                DietList!!.addAll(t!!)
            })
            pregnancyViewModel!!.DuedateLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: turkeyLiveData $t")
                DuedateList!!.addAll(t!!)
            })

            pregnancyViewModel!!.ExerciesLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: uaeLiveData $t")
                ExerciesList!!.addAll(t!!)
            })
            pregnancyViewModel!!.PrecautionLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: italyLiveData $t")
                precautionList!!.addAll(t!!)
            })

            pregnancyViewModel!!.SymptomsLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: switzerlandLiveData $t")
                symptomsList!!.addAll(t!!)
            })
            pregnancyViewModel!!.PregnancyCalenderLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: canadaLiveData $t")
                pregnancyCalenderList!!.addAll(t!!)
            })

            pregnancyViewModel!!.TestLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: singaporeLiveData $t")
                TestList!!.addAll(t!!)
            })

            pregnancyViewModel!!.WeightLiveData.observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: southAfricaLiveData $t")
                WeightList!!.addAll(t!!)
            })



            llprecaution!!.setOnClickListener {
                onShowStores(precautionList!!,view)
            }
            llPregnancycalendar!!.setOnClickListener {
                onShowStores(pregnancyCalenderList!!,view)
            }
            llpregtest!!.setOnClickListener {
                onShowStores(TestList!!,view)
            }
            llweight!!.setOnClickListener {
                onShowStores(WeightList!!,view)
            }
            llCalculator!!.setOnClickListener {
                onShowStores(CalculatorList!!,view)
            }
            llAdvice!!.setOnClickListener {
                onShowStores(AdviceList!!,view)
            }
            llAfter!!.setOnClickListener {
                onShowStores(AfterList!!,view)
            }
            llsymptoms!!.setOnClickListener {
                onShowStores(symptomsList!!,view)
            }
            llMiscarriage!!.setOnClickListener {
                onShowStores(MiscarriageList!!,view)
            }
            lldiet!!.setOnClickListener {
                onShowStores(DietList!!,view)
            }
            llDuedate!!.setOnClickListener {
                onShowStores(DuedateList!!,view)
            }
            llExercise!!.setOnClickListener {
                onShowStores(ExerciesList!!,view)
            }



        }
    }

    fun initViews(view: View){
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())

    }
    @SuppressLint("UseRequireInsteadOfGet")
    fun onShowStores(list: ArrayList<List<String>>, view: View){
        dialog!!.setContentView(R.layout.dialog_show_stores)
        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT);
        rvCategoryStores = dialog!!.findViewById(R.id.rvCategoryStores)
        setRecyclerView()
        categoryStoresAdapter!!.setItems(list)
        if(firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)){
            onLoadFBNativeAdCatDailog(view, context!!, dialog!!)
        }

        dialog!!.show()
    }

    fun setRecyclerView(){
        categoryStoresAdapter = CategoryStoresAdapter(context)
        categoryStoresAdapter!!.setListener(this)
        rvCategoryStores.apply {
            rvCategoryStores?.layoutManager = GridLayoutManager(activity, 3)
            rvCategoryStores?.adapter = categoryStoresAdapter
        }
    }


    fun onLoadFBNativeAdCatDailog(view: View, context: Context, dialog: Dialog) {
        nativeAdFB4 = NativeAd(context, Constants().getFbNativeDailog())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAd1 " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB4 == null || nativeAdFB4 !== ad) {
                    return
                }
                // Inflate Native Ad into Container

                // Add the Ad view into the ad container.
                nativeAdLayout = dialog.findViewById(R.id.native_ad_container_dailog)
                val inflater = LayoutInflater.from(context)
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                adView =
                    inflater.inflate(
                        R.layout.native_ad_layout,
                        nativeAdLayout,
                        false
                    ) as LinearLayout
                nativeAdLayout!!.addView(adView)

                inflateAd(nativeAdFB4!!, adView!!)

                val adChoicesContainer: LinearLayout = dialog.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB4, nativeAdLayout)
                adChoicesContainer.removeAllViews()
                adChoicesContainer.addView(adOptionsView, 0)
            }

            override fun onAdClicked(p0: Ad?) {
                Log.d("TAG", "onAdClicked: onLoadFBNativeAd1")
            }

            override fun onLoggingImpression(p0: Ad?) {
                Log.d("TAG", "onLoggingImpression: onLoadFBNativeAd1")
            }

            override fun onMediaDownloaded(p0: Ad?) {
                Log.d("TAG", "onMediaDownloaded: onLoadFBNativeAd1")
            }
        }

        nativeAdFB4!!.loadAd(
            nativeAdFB4!!.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        );
    }


    private fun inflateAd(nativeAd: NativeAd, adView: LinearLayout) {
        nativeAd.unregisterView()

        // Add the AdOptionsView

        // Create native UI using the ad metadata.
        val nativeAdIcon: com.facebook.ads.MediaView = adView.findViewById(R.id.native_ad_icon)
        val nativeAdTitle: TextView = adView.findViewById(R.id.native_ad_title)
        val nativeAdMedia: com.facebook.ads.MediaView = adView.findViewById(R.id.native_ad_media)
        val nativeAdSocialContext: TextView = adView.findViewById(R.id.native_ad_social_context)
        val nativeAdBody: TextView = adView.findViewById(R.id.native_ad_body)
        val sponsoredLabel: TextView = adView.findViewById(R.id.native_ad_sponsored_label)
        val nativeAdCallToAction: Button = adView.findViewById(R.id.native_ad_call_to_action)

        // Set the Text.
        nativeAdTitle.text = nativeAd.advertiserName
        nativeAdBody.text = nativeAd.adBodyText
        nativeAdSocialContext.text = nativeAd.adSocialContext
        nativeAdCallToAction.visibility =
            if (nativeAd.hasCallToAction()) View.VISIBLE else View.INVISIBLE
        nativeAdCallToAction.text = nativeAd.adCallToAction
        sponsoredLabel.text = nativeAd.sponsoredTranslation

        // Create a list of clickable views
        val clickableViews: ArrayList<View> = ArrayList()
        clickableViews.add(nativeAdTitle)
        clickableViews.add(nativeAdCallToAction)

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
            adView, nativeAdMedia, nativeAdIcon, clickableViews
        )
    }


    override fun onDestroy() {
        pregnancyViewModel?.reset()
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PregnancyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            PregnancyFragment().apply {
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