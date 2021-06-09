package world.live.tv.channels.hd.global.free.map.view.listener.category

import world.live.tv.channels.hd.global.free.online.guide.map.base.listener.BaseRecyclerListener

interface CountryItemClickListener<T> : BaseRecyclerListener {
    fun CategoryStoresCardClick(item: T)
}