package shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.viewholder.BaseViewHolder
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.shayri.ShayriItemClickListener

class ShayriViewHolder(itemView : View): BaseViewHolder<List<String>, ShayriItemClickListener<List<String>>>(itemView){


    var shayri = itemView.findViewById<TextView>(R.id.poem_body)
    var shareButton = itemView.findViewById<ImageView>(R.id.share_poem)

    override fun onBind(item: List<String>, listener: ShayriItemClickListener<List<String>>?) {
        shayri!!.text = item[1]

        shareButton.setOnClickListener{
            val Sharebody = shayri!!.text.toString()
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
