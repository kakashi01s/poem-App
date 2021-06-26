package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.listener.AllAppsItemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.viewholder.AllAppsViewHolder

class AllAppsAdapter(context: Context?) :
GenericRecyclerAdapter<List<String>, AllAppsItemClickListener<List<String>>, AllAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAppsViewHolder {
        return AllAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}