package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.adapter

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.map.view.listener.category.CountryItemClickListener
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.viewholder.GlobalViewHolder

class CountryStoresAdapter(context: Context?) : GenericRecyclerAdapter<List<String>, CountryItemClickListener<List<String>>,GlobalViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalViewHolder {
        return GlobalViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}