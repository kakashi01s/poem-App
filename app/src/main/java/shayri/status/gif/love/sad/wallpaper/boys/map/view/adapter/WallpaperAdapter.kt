package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.Photo
import shayri.status.gif.love.sad.wallpaper.boys.map.model.wallpaper.WallpaperResponse

class WallpaperAdapter(var context: Context, var photos: List<Photo> ,var listener : WallpapercardclickLintener) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_wallpaper,parent, false)
        return WallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        var response = photos[position]



        Log.d("adapterx", response.url)

       Glide.with(context)
           .load(response.src.original)
           .into(holder.wallpaperimgurl)
    }

    override fun getItemCount(): Int {
        return  photos.size
    }
    inner class WallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var wallpaperimgurl = itemView.findViewById<ImageView>(R.id.wallpaper_img)
        init {
            itemView.setOnClickListener {
                var url = photos[position].src.original
                listener.OnwallpaperCardClick(url)
            }
        }

    }
    interface WallpapercardclickLintener{
        fun OnwallpaperCardClick(url : String)
    }
}