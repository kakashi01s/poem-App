package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.listener

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface CategoryStoresItemClickListener<T> : BaseRecyclerListener {
    fun CategoryStoresCardClick(item: T)
}