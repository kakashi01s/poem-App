package world.live.tv.channels.hd.global.free.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import world.live.tv.channels.hd.global.free.map.view.listener.category.MostUsefulAppsItemClickListener
import world.live.tv.channels.hd.global.free.map.view.viewholder.MostUsefulAppsViewHolder
import world.live.tv.channels.hd.global.free.online.guide.map.R
import world.live.tv.channels.hd.global.free.online.guide.map.base.adapter.GenericRecyclerAdapter

class MostUsefulAppsAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, MostUsefulAppsItemClickListener<List<String>>, MostUsefulAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MostUsefulAppsViewHolder{
        return MostUsefulAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}