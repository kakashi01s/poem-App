package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.category.MostUsefulAppsItemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder.MostUsefulAppsViewHolder
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter

class MostUsefulAppsAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, MostUsefulAppsItemClickListener<List<String>>, MostUsefulAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MostUsefulAppsViewHolder{
        return MostUsefulAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}