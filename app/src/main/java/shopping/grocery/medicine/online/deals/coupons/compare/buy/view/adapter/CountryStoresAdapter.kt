package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter

import android.content.Context
import android.view.ViewGroup
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.adapter.GenericRecyclerAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.category.CountryItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder.GlobalViewHolder

class CountryStoresAdapter(context: Context?) : GenericRecyclerAdapter<List<String>, CountryItemClickListener<List<String>>,GlobalViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalViewHolder {
        return GlobalViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}