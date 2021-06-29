package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
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

class WallpaperAdapter(var context: Context, var photos: List<WallpaperResponse>) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_wallpaper,parent, false)
        return WallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        var response = photos[position]
        var photo = response.photos[position]

        holder.wallpaperid.text = photo.src.medium

       Glide.with(context)
           .load(photo.src.medium)
           .into(holder.wallpaperimgurl)
    }

    override fun getItemCount(): Int {
        return  photos.size
    }
    class WallpaperViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var wallpaperimgurl = itemView.findViewById<ImageView>(R.id.card_wallpaper)
        var wallpaperid = itemView.findViewById<TextView>(R.id.wall_id)
    }
}