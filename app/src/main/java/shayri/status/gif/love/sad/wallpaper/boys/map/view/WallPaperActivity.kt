package shayri.status.gif.love.sad.wallpaper.boys.map.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy.Images
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.Photo
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.WallpaperResponse
import shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter.WallpaperAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.apiService.wallpaperService

class WallPaperActivity : AppCompatActivity() {
    var wallpaperAdapter : WallpaperAdapter?= null
    var rvwallpaper : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)
        rvwallpaper = findViewById(R.id.rvwallpaper)
        getWallpapers()
    }


    private fun getWallpapers() {
        var photos = wallpaperService.WallpaperInstance.getWallpaper()
        photos.enqueue(object: Callback<WallpaperResponse>{
            override fun onResponse(
                call: Call<WallpaperResponse>,
                response: Response<WallpaperResponse>
            ) {
                var ImgObjs: MutableList<Photo> = arrayListOf()
                var i = 0
                var photos = response.body()
                if (photos != null) {
                    for (item in photos.photos)
                    {
                        Log.d("infini", item.src.toString())
                        ImgObjs.add(i,item)
                    }
//                    wallpaperAdapter = WallpaperAdapter(this@WallPaperActivity,ImgObjs)
//                    rvwallpaper?.adapter = wallpaperAdapter

                }
            }
            override fun onFailure(call: Call<WallpaperResponse>, t: Throwable) {
                Log.d("infini", t.localizedMessage)
            }
        })
    }
}