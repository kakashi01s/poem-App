package medicine.health.lab.test.medical.online.fitness.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.map.view.listener.category.MostUsefulAppsItemClickListener
import medicine.health.lab.test.medical.online.fitness.map.view.viewholder.MostUsefulAppsViewHolder
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter

class MostUsefulAppsAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, MostUsefulAppsItemClickListener<List<String>>, MostUsefulAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MostUsefulAppsViewHolder{
        return MostUsefulAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}