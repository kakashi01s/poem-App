package shopping.grocery.medicine.online.deals.coupons.compare.buy.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import shopping.grocery.medicine.online.deals.coupons.compare.buy.R
import shopping.grocery.medicine.online.deals.coupons.compare.buy.base.adapter.GenericRecyclerAdapter
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.listener.AllAppsItemClickListener
import shopping.grocery.medicine.online.deals.coupons.compare.buy.view.viewholder.AllAppsViewHolder

class AllAppsAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, AllAppsItemClickListener<List<String>>, AllAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAppsViewHolder {
        return AllAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}