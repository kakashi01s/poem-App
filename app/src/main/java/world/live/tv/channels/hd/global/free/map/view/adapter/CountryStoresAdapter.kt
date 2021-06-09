package world.live.tv.channels.hd.global.free.online.guide.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import world.live.tv.channels.hd.global.free.map.view.listener.category.CountryItemClickListener
import world.live.tv.channels.hd.global.free.online.guide.map.R
import world.live.tv.channels.hd.global.free.online.guide.map.base.adapter.GenericRecyclerAdapter
import world.live.tv.channels.hd.global.free.online.guide.map.view.viewholder.GlobalViewHolder

class CountryStoresAdapter(context: Context?) : GenericRecyclerAdapter<List<String>, CountryItemClickListener<List<String>>,GlobalViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalViewHolder {
        return GlobalViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}