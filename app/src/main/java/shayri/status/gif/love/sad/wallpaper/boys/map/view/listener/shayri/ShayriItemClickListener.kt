package shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.shayri

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface ShayriItemClickListener<T> : BaseRecyclerListener {
    fun OnShayricardclick(item : T)
}