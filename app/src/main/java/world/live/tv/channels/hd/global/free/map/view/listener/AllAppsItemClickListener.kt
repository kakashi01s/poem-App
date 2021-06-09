package world.live.tv.channels.hd.global.free.online.guide.map.view.listener

import world.live.tv.channels.hd.global.free.online.guide.map.base.listener.BaseRecyclerListener

interface AllAppsItemClickListener<T> : BaseRecyclerListener {
    fun onAllCardClick(item: T)
}