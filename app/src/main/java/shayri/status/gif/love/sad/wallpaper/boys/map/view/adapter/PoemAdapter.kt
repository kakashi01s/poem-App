package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.poem.PoemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder.PoemViewHolder

class PoemAdapter(context: Context? ):
    GenericRecyclerAdapter<List<String>, PoemClickListener<List<String>>, PoemViewHolder>(context){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemViewHolder {
        return PoemViewHolder(inflate(R.layout.card_poem,parent))
    }


}