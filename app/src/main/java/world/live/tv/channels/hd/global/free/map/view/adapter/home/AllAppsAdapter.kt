package world.live.tv.channels.hd.global.free.online.guide.map.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import world.live.tv.channels.hd.global.free.online.guide.map.R
import world.live.tv.channels.hd.global.free.online.guide.map.base.adapter.GenericRecyclerAdapter
import world.live.tv.channels.hd.global.free.online.guide.map.view.listener.AllAppsItemClickListener
import world.live.tv.channels.hd.global.free.online.guide.map.view.viewholder.AllAppsViewHolder

class AllAppsAdapter(context: Context?) :
GenericRecyclerAdapter<List<String>, AllAppsItemClickListener<List<String>>, AllAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAppsViewHolder {
        return AllAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}