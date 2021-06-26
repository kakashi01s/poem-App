package shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.gif

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface gifClickListener<T> :BaseRecyclerListener{
    fun ongifclickListener(item: T)
}