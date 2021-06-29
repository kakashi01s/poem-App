package shayri.status.gif.love.sad.wallpaper.boys.map.view.fragment

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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.fragment_shayri.*
import kotlinx.android.synthetic.main.fragment_shayri.view.*
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.BaseFragment
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.utils.Constants
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.viewmodel.CategoryViewModel
import shayri.status.gif.love.sad.wallpaper.boys.map.view.WallPaperActivity
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.PoemAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.ShayriAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.poem.PoemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.shayri.ShayriItemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.viewmodel.PoemViewModel
import shayri.status.gif.love.sad.wallpaper.boys.map.viewmodel.ShayriViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShayriFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShayriFragment : BaseFragment(), ShayriItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    var rvshayri: RecyclerView? = null
    var shayriadapter: ShayriAdapter? = null
    var shayriViewModel: ShayriViewModel? = null
    var rvdialogshayri: RecyclerView? = null
    var Sharebody: String? = null
    var poembody: TextView? = null
    var sharepoem: ImageView? = null


    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var llloveshayri: LinearLayout? = null
    var llhurtshayri: LinearLayout? = null
    var llattitudeshayri: LinearLayout? = null
    var llghalibshayri: LinearLayout? = null
    var llfunnyshayri: LinearLayout? = null
    var llfriendshipshayri: LinearLayout? = null
    var llwallpaper : LinearLayout? = null

    private var loveshayriList: ArrayList<List<String>>? = ArrayList()
    private var hurtshayriList: ArrayList<List<String>>? = ArrayList()
    private var attitudeshayriList: ArrayList<List<String>>? = ArrayList()
    private var ghalibshayrList: ArrayList<List<String>>? = ArrayList()
    private var funnyshayriList: ArrayList<List<String>>? = ArrayList()
    private var friendshipshayriList: ArrayList<List<String>>? = ArrayList()

    private var nativeAdFB1: NativeAd? = null
    private var nativeAdFB2: NativeAd? = null
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
        return inflater.inflate(R.layout.fragment_shayri, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        dialog = Dialog(requireContext())
        dialog!!.setContentView(R.layout.dialog_show_shayri)
        dialog!!.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        shayriViewModel = ViewModelProvider(requireActivity()).get(ShayriViewModel::class.java)
        shayriViewModel?.loadData()
        shayriViewModel!!.loveLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: calculatorsLiveData $t")
            loveshayriList!!.addAll(t!!)
        })
        shayriViewModel!!.attitudeLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: calculatorsLiveData $t")
            attitudeshayriList!!.addAll(t!!)
        })
        shayriViewModel!!.hurtLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: calculatorsLiveData $t")
            hurtshayriList!!.addAll(t!!)
        })
        shayriViewModel!!.ghalibLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: calculatorsLiveData $t")
            ghalibshayrList!!.addAll(t!!)
        })
        shayriViewModel!!.friendshipLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: calculatorsLiveData $t")
            friendshipshayriList!!.addAll(t!!)
        })
        shayriViewModel!!.funnyLiveData.observe(viewLifecycleOwner, Observer { t ->
            Log.d("TAG", "onViewCreated: calculatorsLiveData $t")
            funnyshayriList!!.addAll(t!!)
        })
        llloveshayri!!.setOnClickListener {
            onShowshayris(loveshayriList!!, view)
        }
        llghalibshayri!!.setOnClickListener {
            onShowshayris(ghalibshayrList!!, view)
        }
        llhurtshayri!!.setOnClickListener {
            onShowshayris(hurtshayriList!!, view)
        }
        llfunnyshayri!!.setOnClickListener {
            onShowshayris(funnyshayriList!!, view)
        }
        llfriendshipshayri!!.setOnClickListener {
            onShowshayris(friendshipshayriList!!, view)
        }
        llattitudeshayri!!.setOnClickListener {
            onShowshayris(attitudeshayriList!!, view)
        }
        llwallpaper!!.setOnClickListener {
            var intent = Intent(requireActivity(),WallPaperActivity::class.java)
            startActivity(intent)

        }


    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            if(firebaseRemoteConfig == null){
                firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
            }
            if (firebaseRemoteConfig!!.getBoolean(Constants().SHOW_ADS)) {
                if(nativeAdFB1 == null){
                    onLoadFBNativeAd1(requireView(), requireContext())
                }
                if(nativeAdFB2 == null){
                    onLoadFBNativeAdCatDailog(requireContext(), dialog!!)
                }
            }
        }
    }

    fun onShowshayris(list: ArrayList<List<String>>, view: View) {
        rvdialogshayri = dialog!!.findViewById(R.id.rvshayridialog)

        setRecyclerView()

        shayriadapter!!.setItems(list)

        dialog!!.show()

        dialog!!.setOnDismissListener {
            onLoadFBNativeAdCatDailog(requireContext(), dialog!!)
        }

    }

    private fun setRecyclerView() {
        shayriadapter = ShayriAdapter(context)
        shayriadapter!!.setListener(this)
        rvdialogshayri.apply {
            rvdialogshayri!!.layoutManager = LinearLayoutManager(activity)
            rvdialogshayri!!.adapter = shayriadapter


        }
    }

    private fun initViews(view: View) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireActivity())
        rvshayri = view.findViewById(R.id.rvloveshayri)

        poembody = view.findViewById(R.id.poem_body)
        sharepoem = view.findViewById(R.id.share_poem)
        llattitudeshayri = view.findViewById(R.id.llattitude)
        llfriendshipshayri = view.findViewById(R.id.llfriendship)
        llfunnyshayri = view.findViewById(R.id.llfunny)
        llloveshayri = view.findViewById(R.id.lllove)
        llhurtshayri = view.findViewById(R.id.llhurt)
        llghalibshayri = view.findViewById(R.id.llghalib)
        llwallpaper = view.findViewById(R.id.llwallpaper)
    }

    fun onLoadFBNativeAd1(view: View, context: Context) {
        nativeAdFB1 = NativeAd(context, Constants().getFbNativeTopic())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAd1 " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB1 == null || nativeAdFB1 !== ad) {
                    return
                }
                // Inflate Native Ad into Container

                // Add the Ad view into the ad container.
                nativeAdLayout = view.findViewById(R.id.native_ad_container_topic)
                val inflater = LayoutInflater.from(context)
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                adView =
                    inflater.inflate(
                        R.layout.native_ad_layout,
                        nativeAdLayout,
                        false
                    ) as LinearLayout
                nativeAdLayout!!.addView(adView)

                inflateAd(nativeAdFB1!!, adView!!)

                val adChoicesContainer: LinearLayout = view.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB1, nativeAdLayout)
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

        nativeAdFB1!!.loadAd(
            nativeAdFB1!!.buildLoadAdConfig()
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

    fun onLoadFBNativeAdCatDailog(context: Context, dialog: Dialog) {
        nativeAdFB2 = NativeAd(context, Constants().getFbNativeCatDailog())
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onError(p0: Ad?, p1: AdError?) {
                Log.d("TAG", "onError: onLoadFBNativeAdCAT " + p1!!.errorMessage)
            }

            override fun onAdLoaded(ad: Ad?) {

                Log.d("TAG", "onAdLoaded: onLoadFBNativeAdCAT")

                // Race condition, load() called again before last ad was displayed
                if (nativeAdFB2 == null || nativeAdFB2 !== ad) {
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

                inflateAd(nativeAdFB2!!, adView!!)

                val adChoicesContainer: LinearLayout =
                    dialog.findViewById(R.id.ad_choices_container)
                val adOptionsView = AdOptionsView(context, nativeAdFB2, nativeAdLayout)
                adChoicesContainer.removeAllViews()
                adChoicesContainer.addView(adOptionsView, 0)
            }

            override fun onAdClicked(p0: Ad?) {
                Log.d("TAG", "onAdClicked: onLoadFBNativeAdCAT")
            }

            override fun onLoggingImpression(p0: Ad?) {
                Log.d("TAG", "onLoggingImpression: onLoadFBNativeAdCAT")
            }

            override fun onMediaDownloaded(p0: Ad?) {
                Log.d("TAG", "onMediaDownloaded: onLoadFBNativeAdCAT")
            }
        }

        nativeAdFB2!!.loadAd(
            nativeAdFB2!!.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShayriFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            ShayriFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun OnShayricardclick(item: List<String>) {
        Log.d("ITEM", item.toString())
        sharepoem?.setOnClickListener {
            Sharebody = poembody!!.text.toString()
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra("body",Sharebody)
//            startActivity(Intent.createChooser(intent,"Send to..."))

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, Sharebody)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
