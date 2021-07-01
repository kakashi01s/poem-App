package shayri.status.gif.love.sad.wallpaper.boys.map.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_wall_paper.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.Photo
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.WallpaperResponse
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.GifAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.WallpaperAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.apiService.wallpaperService

class WallPaperActivity : AppCompatActivity(), WallpaperAdapter.WallpapercardclickLintener {
    var wallpaperAdapter : WallpaperAdapter?= null
    var wallpapersearch : EditText? = null
    var searchwall : ImageButton? = null
    var query : String? = null

    private var layoutManager: RecyclerView.LayoutManager?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)
        layoutManager = LinearLayoutManager(this)
        rv_wallpaper.layoutManager = layoutManager
        searchwall = findViewById(R.id.search_button_wall)
        wallpapersearch = findViewById(R.id.search_wallpaper)
        query = wallpapersearch!!.text.toString()
        getWallpapers(rv_wallpaper!!,layoutManager!!)
        searchwall!!.setOnClickListener {
            if (query != null){
                searchwallpapers(rv_wallpaper,layoutManager!!)
            }else
            {
                getWallpapers(rv_wallpaper!!,layoutManager!!)
            }
        }

    }


    private fun searchwallpapers(rv: RecyclerView?, manager: RecyclerView.LayoutManager) {
        query = wallpapersearch!!.text.toString()
        var photos = wallpaperService.WallpaperInstance.getWallpaper("$query")
        photos.enqueue(object: Callback<WallpaperResponse>{
            override fun onResponse(
                call: Call<WallpaperResponse>,
                response: Response<WallpaperResponse>
            ) {
                var photos = response.body()
                if (photos != null)
                {
                    Log.d("nithik",photos.photos.toString())
                    wallpaperAdapter = WallpaperAdapter(this@WallPaperActivity,photos.photos,this@WallPaperActivity)
                    Log.d("nithik",wallpaperAdapter.toString())
                    rv?.layoutManager = LinearLayoutManager(this@WallPaperActivity)
                    rv?.adapter = wallpaperAdapter
                }
            }

            override fun onFailure(call: Call<WallpaperResponse>, t: Throwable) {
                Log.d("nithik", t.localizedMessage)
            }
        })
    }


    private fun getWallpapers(rv: RecyclerView, manager: RecyclerView.LayoutManager) {

        var photos = wallpaperService.WallpaperInstance.getWallpaper("nature")
        photos.enqueue(object: Callback<WallpaperResponse>{
            override fun onResponse(
                call: Call<WallpaperResponse>,
                response: Response<WallpaperResponse>
            ) {
                var photos = response.body()
                if (photos != null)
                {
                    Log.d("nithik",photos.photos.toString())
                    wallpaperAdapter = WallpaperAdapter(this@WallPaperActivity,photos.photos,this@WallPaperActivity)
                    Log.d("nithik",wallpaperAdapter.toString())
                    rv.layoutManager = LinearLayoutManager(this@WallPaperActivity)
                    rv.adapter = wallpaperAdapter
                }
            }

            override fun onFailure(call: Call<WallpaperResponse>, t: Throwable) {
                Log.d("nithik", t.localizedMessage)
            }
        })
    }

    override fun OnwallpaperCardClick(url: String) {
        val intent: Intent? = Intent(this,FullimageActivity::class.java)
        intent?.putExtra("url", url)
        val bundle = Bundle()
        bundle.putString("url", url)
        startActivity(intent)
    }
}