package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.utils.Pref
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
    var bookmarkList: String ? = null
    var pre_bookmarkList: String ? = null
   var list : ArrayList<String> = ArrayList()

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
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Pref.initializeInstance(this.context)
        Log.d("Share", Pref.instance!!.bookmarksData.toString())


        pre_bookmarkList = bookmarkList
        bookmarkList = Pref.instance!!.bookmarksData

        Log.d("SharePre" , pre_bookmarkList.toString())
        Log.d("Sharelist" , bookmarkList.toString())

        if (bookmarkList != pre_bookmarkList) {
            list.add(bookmarkList!!)
        }
        Log.d("Sharebl", list.toString())

        b_recycler = view.findViewById(R.id.bookmark_rec)

        b_adapter = BookmarkAdapter(this.requireContext(), list)

        b_recycler.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        b_recycler.adapter = b_adapter

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