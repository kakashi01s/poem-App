package shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.viewholder.BaseViewHolder
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.poem.PoemClickListener

class PoemViewHolder(itemView : View): BaseViewHolder<List<String>, PoemClickListener<List<String>>>(itemView){


    var poem = itemView.findViewById<TextView>(R.id.poem_body)
    var shareButton = itemView.findViewById<ImageView>(R.id.share_poem)

    override fun onBind(item: List<String>, listener: PoemClickListener<List<String>>?) {
        poem!!.text = item[1]

        shareButton.setOnClickListener{
            val Sharebody = poem!!.text.toString()
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra("body",Sharebody)
//            startActivity(Intent.createChooser(intent,"Send to..."))

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, Sharebody)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            itemView.context.startActivity(shareIntent)
        }
        }
    }
