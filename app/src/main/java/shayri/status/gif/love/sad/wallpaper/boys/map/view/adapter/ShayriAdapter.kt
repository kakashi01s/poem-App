package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.poem.PoemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.shayri.ShayriItemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder.PoemViewHolder
import shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder.ShayriViewHolder

class ShayriAdapter(context: Context? ):
    GenericRecyclerAdapter<List<String>, ShayriItemClickListener<List<String>>, ShayriViewHolder>(context){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayriViewHolder {
        return ShayriViewHolder(inflate(R.layout.card_poem,parent))
    }


}