package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.droidsonroids.gif.GifImageView
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.map.data.giphy.Images

class GifAdapter(var context: Context, var gif: List<Images>) :
    RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_gif,parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {

       var gifs = gif[position]
        var gifx = gifs.original.url

        Glide.with(context)
            .asGif()
            .load(gifx)
            .into(holder.gifimage)

        holder.shareBtn.setOnClickListener {

            //Toast.makeText(holder.itemView.context,gifx,Toast.LENGTH_SHORT).show()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, gifx)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            holder.itemView.context.startActivity(shareIntent)
        }

    }
    override fun getItemCount(): Int {
        return gif.size
    }

    class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var gifimage = itemView.findViewById<GifImageView>(R.id.gif_card)
        var shareBtn = itemView.findViewById<ImageView>(R.id.share_gif)
    }


}