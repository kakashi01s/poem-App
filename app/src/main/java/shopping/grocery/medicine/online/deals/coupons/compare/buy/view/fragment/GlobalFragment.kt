package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.BaseFragment
import shopping.grocery.medicine.online.deals.coupons.compare.buy.model.category.Calculator
import shopping.grocery.medicine.online.deals.coupons.compare.buy.model.category.TechnicalChart
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.MainActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.WebActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter.CountryStoresAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.CategoryStoresItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.category.CountryItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder.CalculatorViewHolder
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder.TechnicalChartViewHolder
import shopping.grocery.medicine.online.deals.coupons.compare.buy.viewmodel.GlobalViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GlobalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GlobalFragment : BaseFragment(), CountryItemClickListener<List<String>> {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var rvCalculator: RecyclerView? = null
    var rvTechnicalChart: RecyclerView? = null

    var chartReference: DatabaseReference? = null
    var calculatorReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    var calculateRecyclerAdapter: FirebaseRecyclerAdapter<Calculator, CalculatorViewHolder>? = null
    var chartRecyclerAdapter: FirebaseRecyclerAdapter<TechnicalChart, TechnicalChartViewHolder>? =
        null

    var rvCountryStores: RecyclerView? = null
    var countryStoresAdapter: CountryStoresAdapter? = null
    var globalViewModel: GlobalViewModel? = null

    var firebaseRemoteConfig: FirebaseRemoteConfig? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    var nativeAdCat1: UnifiedNativeAd? = null
    var nativeAdCat2: UnifiedNativeAd? = null

    //var llSuperMarts: LinearLayout? = null
    var llIndia: LinearLayout? = null
    var llUsa: LinearLayout? = null
    var llRussia: LinearLayout? = null
    var llPakistan: LinearLayout? = null
    var llChina: LinearLayout? = null
    var llGermany: LinearLayout? = null
    var llTurkey: LinearLayout? = null
    var llUae: LinearLayout? = null
    var llItaly: LinearLayout? = null
    var llSwitzerland: LinearLayout? = null
    var llCanada: LinearLayout? = null
    var llSingapore: LinearLayout? = null
    var llSouthAfrica: LinearLayout? = null
    var llFrance: LinearLayout? = null

    var llIndonesia: LinearLayout? = null
    var llUk: LinearLayout? = null
    var llJapan: LinearLayout? = null
    var llBrazil: LinearLayout? = null
    var llNigeria: LinearLayout? = null
    var llPortugal: LinearLayout? = null
    var llAustralia: LinearLayout? = null
    var llGreece: LinearLayout? = null


    var indiaList: ArrayList<List<String>>? = ArrayList()
    var usaList: ArrayList<List<String>>? = ArrayList()
    var russiaList: ArrayList<List<String>>? = ArrayList()
    var pakistanList: ArrayList<List<String>>? = ArrayList()
    var chinaList: ArrayList<List<String>>? = ArrayList()
    var germanyList: ArrayList<List<String>>? = ArrayList()
    var turkeyList: ArrayList<List<String>>? = ArrayList()
    var uaeList: ArrayList<List<String>>? = ArrayList()
    var italyList: ArrayList<List<String>>? = ArrayList()
    var switzerlandList: ArrayList<List<String>>? = ArrayList()
    var canadaList: ArrayList<List<String>>? = ArrayList()
    var singaporeList: ArrayList<List<String>>? = ArrayList()
    var southAfricaList: ArrayList<List<String>>? = ArrayList()
    var franceList: ArrayList<List<String>>? = ArrayList()
    var indonesiaList: ArrayList<List<String>>? = ArrayList()
    var ukList: ArrayList<List<String>>? = ArrayList()
    var japanList: ArrayList<List<String>>? = ArrayList()
    var brazilList: ArrayList<List<String>>? = ArrayList()
    var nigeriaList: ArrayList<List<String>>? = ArrayList()
    var portugalList: ArrayList<List<String>>? = ArrayList()
    var australiaList: ArrayList<List<String>>? = ArrayList()
    var greeceList: ArrayList<List<String>>? = ArrayList()


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
           // param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_global, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        dialog = Dialog(context!!)

        globalViewModel = ViewModelProvider(activity!!).get( GlobalViewModel ::class.java)
        globalViewModel?.loadData()

        globalViewModel!!.indiaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: indiaLiveData $t")
            indiaList!!.addAll(t!!)
        })

        globalViewModel!!.usaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: usaLiveData $t")
            usaList!!.addAll(t!!)
        })
        globalViewModel!!.russiaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: russiaLiveData $t")
            russiaList!!.addAll(t!!)
        })

        globalViewModel!!.pakistanLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: pakistanLiveData $t")
            pakistanList!!.addAll(t!!)
        })
        globalViewModel!!.chinaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: chinaLiveData $t")
            chinaList!!.addAll(t!!)
        })

        globalViewModel!!.germanyLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: germanyLiveData $t")
            germanyList!!.addAll(t!!)
        })
        globalViewModel!!.turkeyLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: turkeyLiveData $t")
            turkeyList!!.addAll(t!!)
        })

        globalViewModel!!.uaeLiveData
            .observe(this, Observer { t ->
                Log.d("TAG", "onViewCreated: uaeLiveData $t")
                uaeList!!.addAll(t!!)
            })
        globalViewModel!!.italyLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: italyLiveData $t")
            italyList!!.addAll(t!!)
        })

        globalViewModel!!.switzerlandLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: switzerlandLiveData $t")
            switzerlandList!!.addAll(t!!)
        })
        globalViewModel!!.canadaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: canadaLiveData $t")
            canadaList!!.addAll(t!!)
        })

        globalViewModel!!.singaporeLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: singaporeLiveData $t")
            singaporeList!!.addAll(t!!)
        })
        globalViewModel!!.southAfricaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: southAfricaLiveData $t")
            southAfricaList!!.addAll(t!!)
        })

        globalViewModel!!.franceLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: franceLiveData $t")
            franceList!!.addAll(t!!)
        })

        globalViewModel!!.indonesiaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: indonesiaLiveData $t")
            indonesiaList!!.addAll(t!!)
        })

        globalViewModel!!.ukLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: ukLiveData $t")
            ukList!!.addAll(t!!)
        })

        globalViewModel!!.japanLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: japanLiveData $t")
            japanList!!.addAll(t!!)
        })

        globalViewModel!!.brazilLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: brazilLiveData $t")
            brazilList!!.addAll(t!!)
        })

        globalViewModel!!.nigeriaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: nigeriaLiveData $t")
            nigeriaList!!.addAll(t!!)
        })

        globalViewModel!!.portugalLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: portugalLiveData $t")
            portugalList!!.addAll(t!!)
        })

        globalViewModel!!.australiaLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: australiaLiveData $t")
            australiaList!!.addAll(t!!)
        })

        globalViewModel!!.greeceLiveData.observe(this, Observer { t ->
            Log.d("TAG", "onViewCreated: greeceLiveData $t")
            greeceList!!.addAll(t!!)
        })

        llIndia!!.setOnClickListener {
            onShowStores(indiaList!!)
        }
        llUsa!!.setOnClickListener {
            onShowStores(usaList!!)
        }
        llRussia!!.setOnClickListener {
            onShowStores(russiaList!!)
        }
        llPakistan!!.setOnClickListener {
            onShowStores(pakistanList!!)
        }
        llChina!!.setOnClickListener {
            onShowStores(chinaList!!)
        }
        llGermany!!.setOnClickListener {
            onShowStores(germanyList!!)
        }
        llTurkey!!.setOnClickListener {
            onShowStores(turkeyList!!)
        }
        llUae!!.setOnClickListener {
            onShowStores(uaeList!!)
        }
        llItaly!!.setOnClickListener {
            onShowStores(italyList!!)
        }
        llSwitzerland!!.setOnClickListener {
            onShowStores(switzerlandList!!)
        }
        llCanada!!.setOnClickListener {
            onShowStores(canadaList!!)
        }
        llSingapore!!.setOnClickListener {
            onShowStores(singaporeList!!)
        }
        llSouthAfrica!!.setOnClickListener {
            onShowStores(southAfricaList!!)
        }
        llFrance!!.setOnClickListener {
            onShowStores(franceList!!)
        }
        llIndonesia!!.setOnClickListener {
            onShowStores(indonesiaList!!)
        }
        llUk!!.setOnClickListener {
            onShowStores(ukList!!)
        }
        llJapan!!.setOnClickListener {
            onShowStores(japanList!!)
        }
        llBrazil!!.setOnClickListener {
            onShowStores(brazilList!!)
        }
        llNigeria!!.setOnClickListener {
            onShowStores(nigeriaList!!)
        }
        llPortugal!!.setOnClickListener {
            onShowStores(portugalList!!)
        }
        llAustralia!!.setOnClickListener {
            onShowStores(australiaList!!)
        }
        llGreece!!.setOnClickListener {
            onShowStores(greeceList!!)
        }


    }

    fun initViews(view: View) {
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        llIndia = view.findViewById(R.id.llIndia)
        llUsa = view.findViewById(R.id.llUsa)
        llRussia = view.findViewById(R.id.llRussia)
        llPakistan = view.findViewById(R.id.llPakistan)
        llChina = view.findViewById(R.id.llChina)
        llGermany = view.findViewById(R.id.llGermany)
        llTurkey = view.findViewById(R.id.llTurkey)
        llUae = view.findViewById(R.id.llUae)
        llItaly = view.findViewById(R.id.llItaly)
        llSwitzerland = view.findViewById(R.id.llSwitzerland)
        llCanada = view.findViewById(R.id.llCanada)
        llSingapore = view.findViewById(R.id.llSingapore)
        llSouthAfrica = view.findViewById(R.id.llSouthAfrica)
        llFrance = view.findViewById(R.id.llFrance)
        llIndonesia = view.findViewById(R.id.llIndonesia)
        llUk = view.findViewById(R.id.llUk)
        llJapan = view.findViewById(R.id.llJapan)
        llBrazil = view.findViewById(R.id.llBrazil)
        llNigeria = view.findViewById(R.id.llNigeria)
        llPortugal = view.findViewById(R.id.llPortugal)
        llAustralia = view.findViewById(R.id.llAustralia)
        llGreece = view.findViewById(R.id.llGreece)
    }

    fun setRecyclerView() {
        countryStoresAdapter = CountryStoresAdapter(context)
        countryStoresAdapter!!.setListener(this)
        rvCountryStores.apply {
            rvCountryStores?.layoutManager = GridLayoutManager(activity, 3)
            rvCountryStores?.adapter = countryStoresAdapter
        }
    }

    fun onShowStores(list: ArrayList<List<String>>) {
        dialog!!.setContentView(R.layout.dialog_show_stores)

        dialog!!.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        );

        rvCountryStores = dialog!!.findViewById(R.id.rvCategoryStores)

        setRecyclerView()

        countryStoresAdapter!!.setItems(list)

        dialog!!.show()

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GlobalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            GlobalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param2)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

    override fun CategoryStoresCardClick(item: List<String>) {
        Log.d("TAG", "onAllBrokersCardClick: " + item.get(1))

        val bundle = Bundle()
        bundle.putString("title", item.get(1))
        bundle.putString("url", item.get(2))
        (activity as MainActivity?)!!.onUpdateLogEvent(bundle, "brokers_visited", true)

        val intent: Intent? = Intent(activity, WebActivity::class.java)
        intent?.putExtra("title", item.get(1))
        intent?.putExtra("url", item.get(2))
        startActivity(intent)

    }
}