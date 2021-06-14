package medicine.health.lab.test.medical.online.fitness.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.map.view.listener.labtest.BookLabClickListener
import medicine.health.lab.test.medical.online.fitness.map.view.viewholder.LiveNewsViewHolder
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter

class BookLabAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, BookLabClickListener<List<String>>, LiveNewsViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveNewsViewHolder {
        return LiveNewsViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}