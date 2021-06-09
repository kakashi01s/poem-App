package world.live.tv.channels.hd.global.free.online.guide.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import world.live.tv.channels.hd.global.free.online.guide.map.R
import world.live.tv.channels.hd.global.free.online.guide.map.base.adapter.GenericRecyclerAdapter
import world.live.tv.channels.hd.global.free.online.guide.map.view.listener.CategoryStoresItemClickListener
import world.live.tv.channels.hd.global.online.guide.map.map.view.viewholder.CategoryStoresViewHolder

class CategoryStoresAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, CategoryStoresItemClickListener<List<String>>, CategoryStoresViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryStoresViewHolder {
        return CategoryStoresViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}