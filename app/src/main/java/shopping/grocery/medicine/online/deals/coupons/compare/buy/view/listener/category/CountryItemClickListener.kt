package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.category

import android.view.View
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.listener.BaseRecyclerListener

interface CountryItemClickListener<T> : BaseRecyclerListener {
    fun CategoryStoresCardClick(item: T)
}