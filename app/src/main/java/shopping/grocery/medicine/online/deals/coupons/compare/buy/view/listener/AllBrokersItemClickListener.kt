package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener

import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.listener.BaseRecyclerListener

interface CategoryStoresItemClickListener<T> : BaseRecyclerListener {
    fun CategoryStoresCardClick(item: T)
}