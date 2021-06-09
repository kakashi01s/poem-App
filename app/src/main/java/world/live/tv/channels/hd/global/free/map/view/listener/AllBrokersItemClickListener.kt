package world.live.tv.channels.hd.global.free.online.guide.map.view.listener

import world.live.tv.channels.hd.global.free.online.guide.map.base.listener.BaseRecyclerListener

interface CategoryStoresItemClickListener<T> : BaseRecyclerListener {
    fun CategoryStoresCardClick(item: T)
}