package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.labtest.BookLabClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder.LiveNewsViewHolder
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter

class BookLabAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, BookLabClickListener<List<String>>, LiveNewsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveNewsViewHolder {
        return LiveNewsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}