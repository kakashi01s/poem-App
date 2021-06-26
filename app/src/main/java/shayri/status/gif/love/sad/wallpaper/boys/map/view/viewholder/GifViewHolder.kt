package shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.droidsonroids.gif.GifImageView
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R


class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var gifcard : GifImageView? = null
    var gifid : TextView? = null

    init {
        gifcard = itemView.findViewById(R.id.gif_card)
        gifid = itemView.findViewById(R.id.gif_id)
    }


}