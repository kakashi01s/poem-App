package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener

import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.listener.BaseRecyclerListener

interface AllAppsItemClickListener<T> : BaseRecyclerListener {
    fun onAllCardClick(item: T)
}