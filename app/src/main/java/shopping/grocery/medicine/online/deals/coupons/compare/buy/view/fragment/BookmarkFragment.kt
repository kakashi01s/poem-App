package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_bookmark.*
import me.toptas.fancyshowcase.FancyShowCaseView
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.model.bookmark.Bookmarks
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Pref
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.RecyclerTouchListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.RecyclerTouchListener.OnRowClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.RecyclerTouchListener.OnSwipeOptionsClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.WebActivity
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter.BookmarkAdapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookmarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarkFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null


    lateinit var b_recycler: RecyclerView
    lateinit var b_adapter: BookmarkAdapter
    var bookmarkList: ArrayList<Bookmarks> = ArrayList()

    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    val STORE_FILE_NAME = "Bookmarks_data"

    private var touchListener: RecyclerTouchListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onRefreshFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        sharedPreferences =
            requireActivity().getSharedPreferences(
                STORE_FILE_NAME,
                Context.MODE_PRIVATE
            )
        editor = sharedPreferences!!.edit()

        Pref.initializeInstance(this.context)
        Log.d("Share", Pref.instance!!.bookmarksData.toString())


//        adapter.setClickListener(View.OnClickListener { v ->
//            focus(v)
//        })


        b_recycler = view.findViewById(R.id.bookmark_rec)

        b_adapter = BookmarkAdapter(this.requireContext(), bookmarkList, this)

        b_recycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        b_recycler.adapter = b_adapter


        touchListener = RecyclerTouchListener(requireActivity(), b_recycler)
        touchListener!!
            .setClickable(object : OnRowClickListener {
                override fun onRowClicked(position: Int) {
                    val bookmarkData: Bookmarks = bookmarkList[position]
                    val bundle = Bundle()
                    val intent: Intent = Intent(activity, WebActivity::class.java)
                    intent.putExtra("title", bookmarkData!!.bookmarkTitle)
                    intent.putExtra("url", bookmarkData.bookmarkUrl)
                    intent.putExtra("app_icon", bookmarkData.bookmarkLogo)
                    intent.putExtra("color", bookmarkData.webSplash)

                    startActivity(intent)

//                    Toast.makeText(
//                        context,
//                        bookmarkList[position].bookmarkTitle,
//                        Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onIndependentViewClicked(independentViewID: Int, position: Int) {}
            })
            .setSwipeOptionViews(R.id.delete_task, R.id.edit_task)
            .setSwipeable(R.id.rowFG, R.id.rowBG, object : OnSwipeOptionsClickListener {
                override fun onSwipeOptionClicked(viewID: Int, position: Int) {
                    when (viewID) {
                        R.id.delete_task -> {
                            bookmarkList.removeAt(position)
                            b_adapter.setList(bookmarkList)
                        }
                        R.id.edit_task -> Toast.makeText(
                            context,
                            "Share",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        b_recycler.addOnItemTouchListener(touchListener!!)


        getBookmarks(view)

    }

    fun fancyShowCase(it: View, title: String) {
        Log.d("Fancy", "Run")
        return FancyShowCaseView.Builder(this.requireActivity()).focusOn(it).title(title).delay(50)
            .titleSize(14, 2)
            .showOnce(title)
            .build()
            .show()
    }

    private fun getBookmarks(view: View) {
        if (bookmarkList.isNotEmpty()) {
            Log.d("TAG", "getBookmarks: list clear")
            bookmarkList.clear()
        }
        if (sharedPreferences!!.getString("Bookmarks", null) != null) {
            val serializedObject: String =
                sharedPreferences!!.getString(
                    "Bookmarks",
                    null
                )!!
            if (serializedObject != null) {
                val gson = Gson()
                val type = object : TypeToken<ArrayList<Bookmarks?>?>() {}.type
                bookmarkList = gson.fromJson(serializedObject, type)
                if (bookmarkList != null) {
                    Log.d("TAG", "getBookmarks: " + bookmarkList.size)
                }
                if (bookmarkList.isEmpty()) {
                    onSetEmptyLayout()
                } else {
                    bookmark_rec.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                    b_adapter.setList(bookmarkList)
                    b_adapter.notifyDataSetChanged()
                }
            } else {
                onSetEmptyLayout()
            }

        } else {
            onSetEmptyLayout()
        }
    }

    private fun onSetEmptyLayout() {
        bookmark_rec.visibility = View.GONE
        emptyLayout.visibility = View.VISIBLE
    }

    private fun onRefreshFragment() {
        Log.d("TAG", "onRefreshFragment: ")
        getBookmarks(requireView())
    }

    override fun onResume() {
        super.onResume()
        b_recycler.addOnItemTouchListener(touchListener!!);
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookmarkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            BookmarkFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}