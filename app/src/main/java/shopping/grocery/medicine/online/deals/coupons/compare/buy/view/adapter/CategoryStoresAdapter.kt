package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter

import android.content.Context
import android.view.ViewGroup
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.adapter.GenericRecyclerAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.CategoryStoresItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder.CategoryStoresViewHolder

class CategoryStoresAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, CategoryStoresItemClickListener<List<String>>, CategoryStoresViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryStoresViewHolder {
        return CategoryStoresViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}