package shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.labtest

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface BookLabClickListener<T> : BaseRecyclerListener {
    fun onBookLabCardClick(item: T)
}