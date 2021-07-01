package shayri.status.gif.love.sad.wallpaper.boys.map.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy.GiphyData
import shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy.Images
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.GifAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.apiService.gifService


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GifFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GifFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    var gifAdapter : GifAdapter? = null
    var rv_gif: RecyclerView?= null
    var searchgif : EditText? = null
    var searchbutton : ImageButton? = null
    var query : String? = null

    private var layoutManager: RecyclerView.LayoutManager?= null

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
        return inflater.inflate(R.layout.fragment_gif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initviews(view)
        rv_gif = view.findViewById(R.id.rv_gif)
        layoutManager = LinearLayoutManager(view.context)
        rv_gif?.layoutManager = layoutManager
        query = searchgif!!.text.toString()
        getgif(rv_gif!!, layoutManager!!)
        searchbutton?.setOnClickListener {
            if (query != null) {
                querygetgif(rv_gif!!, layoutManager!!)
            } else {
                getgif(rv_gif!!, layoutManager!!)
            }
        }
    }

    private fun querygetgif(rv: RecyclerView, manager: RecyclerView.LayoutManager) {
        query = searchgif!!.text.toString().trim()
        var giphy = gifService.GifInstance.getGif("$query")
        giphy.enqueue(object : Callback<GiphyData> {
            override fun onResponse(call: Call<GiphyData>, response: Response<GiphyData>) {
                var items = response.body()
                if (items != null) {
                    var ImgObjs: MutableList<Images> = arrayListOf()
                    var i = 0
                    for (sample in items.data) {
                        Log.d("SAMPLE", sample.images.original.url)
                        //Log.d("nithik", sample.images.toString())
                        ImgObjs.add(i,sample.images)
                        //gifAdapter = GifAdapter(context!!, url.images.toString())
                    }
                    gifAdapter = GifAdapter(context!!, ImgObjs)
                    rv.adapter = gifAdapter
                }
            }

            override fun onFailure(call: Call<GiphyData>, t: Throwable) {
                Log.d("nithik", t.localizedMessage)
            }
        })
    }


    private fun initviews(view : View) {
        searchgif = view.findViewById(R.id.search_gif)
        searchbutton = view.findViewById(R.id.search_button)


    }

    fun getgif(rv : RecyclerView,manager: RecyclerView.LayoutManager) {
        var giphy = gifService.GifInstance.getGif("hello")
        giphy.enqueue(object : Callback<GiphyData> {
            override fun onResponse(call: Call<GiphyData>, response: Response<GiphyData>) {
                var items = response.body()
                if (items != null) {
                    var ImgObjs: MutableList<Images> = arrayListOf()
                    var i = 0
                    for (sample in items.data) {
                        Log.d("SAMPLE", sample.images.original.url)
                        //Log.d("nithik", sample.images.toString())
                        ImgObjs.add(i,sample.images)
                       //gifAdapter = GifAdapter(context!!, url.images.toString())
                    }
                    gifAdapter = GifAdapter(context!!, ImgObjs)
                    rv.adapter = gifAdapter
                }
            }

            override fun onFailure(call: Call<GiphyData>, t: Throwable) {
                Log.d("nithik", t.localizedMessage)
            }
        })
    }





    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GifFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            GifFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}