package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.adapter.GenericRecyclerAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.home.TrendingItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder.TrendingViewHolder

class TrendingAdapter (context: Context?) :
    GenericRecyclerAdapter<List<String>, TrendingItemClickListener<List<String>>, TrendingViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(inflate(R.layout.card_trending_layout,parent))
    }
}