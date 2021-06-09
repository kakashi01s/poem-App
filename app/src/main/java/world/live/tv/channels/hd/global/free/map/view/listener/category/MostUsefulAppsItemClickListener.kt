package world.live.tv.channels.hd.global.free.map.view.listener.category


import world.live.tv.channels.hd.global.free.online.guide.map.base.listener.BaseRecyclerListener

interface MostUsefulAppsItemClickListener <T> : BaseRecyclerListener {
    fun onMostUsefulAppsCardClick(item: T)
}