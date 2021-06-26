package shayri.status.gif.love.sad.wallpaper.boys.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import shayri.status.gif.love.sad.wallpaper.boys.map.view.listener.labtest.TestResultClickListener
import shayri.status.gif.love.sad.wallpaper.boys.map.view.viewholder.TestResultViewHolder
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.R
import shayri.status.gif.love.sad.wallpaper.boys.girls.attitude.all.base.adapter.GenericRecyclerAdapter

class TestResultAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, TestResultClickListener<List<String>>, TestResultViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestResultViewHolder {
        return TestResultViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}