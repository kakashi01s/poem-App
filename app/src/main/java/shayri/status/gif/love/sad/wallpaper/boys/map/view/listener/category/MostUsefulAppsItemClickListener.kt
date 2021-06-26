package shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.category


import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface MostUsefulAppsItemClickListener <T> : BaseRecyclerListener {
    fun onMostUsefulAppsCardClick(item: T)
}