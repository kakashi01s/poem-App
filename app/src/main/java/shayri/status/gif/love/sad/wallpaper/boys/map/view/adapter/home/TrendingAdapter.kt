package shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.listener.home.TrendingItemClickListener
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.view.viewholder.TrendingViewHolder

class TrendingAdapter (context: Context?) :
    GenericRecyclerAdapter<List<String>, TrendingItemClickListener<List<String>>, TrendingViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(inflate(R.layout.card_trending_layout,parent))
    }
}