package shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.poem

import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.listener.BaseRecyclerListener

interface PoemClickListener<T> : BaseRecyclerListener {
    fun Onpoemcardclick(item : T)
}