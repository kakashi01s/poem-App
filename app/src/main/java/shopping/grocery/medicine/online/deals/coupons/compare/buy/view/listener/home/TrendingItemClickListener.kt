package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.home

import android.view.View
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.listener.BaseRecyclerListener

interface TrendingItemClickListener <T> : BaseRecyclerListener {
    fun onTrendingClickListener(item: T)
}