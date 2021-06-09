package world.live.tv.channels.hd.global.free.online.guide.map.view.listener.home

import world.live.tv.channels.hd.global.free.online.guide.map.base.listener.BaseRecyclerListener

interface TrendingItemClickListener <T> : BaseRecyclerListener {
    fun onTrendingClickListener(item: T)
}