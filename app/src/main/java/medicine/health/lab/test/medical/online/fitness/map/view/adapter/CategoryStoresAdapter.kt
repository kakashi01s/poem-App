package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.adapter

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.listener.CategoryStoresItemClickListener
import medicine.health.lab.test.medical.online.online.guide.map.map.view.viewholder.CategoryStoresViewHolder

class CategoryStoresAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, CategoryStoresItemClickListener<List<String>>, CategoryStoresViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryStoresViewHolder {
        return CategoryStoresViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}