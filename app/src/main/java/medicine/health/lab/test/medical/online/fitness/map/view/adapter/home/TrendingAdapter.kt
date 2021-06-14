package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.listener.home.TrendingItemClickListener
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.viewholder.TrendingViewHolder

class TrendingAdapter (context: Context?) :
    GenericRecyclerAdapter<List<String>, TrendingItemClickListener<List<String>>, TrendingViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(inflate(R.layout.card_trending_layout,parent))
    }
}