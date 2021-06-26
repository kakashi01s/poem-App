package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.listener.home

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface TrendingItemClickListener <T> : BaseRecyclerListener {
    fun onTrendingClickListener(item: T)
}