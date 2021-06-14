package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.adapter.home

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.listener.AllAppsItemClickListener
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.viewholder.AllAppsViewHolder

class AllAppsAdapter(context: Context?) :
GenericRecyclerAdapter<List<String>, AllAppsItemClickListener<List<String>>, AllAppsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAppsViewHolder {
        return AllAppsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}