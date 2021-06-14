package medicine.health.lab.test.medical.online.fitness.map.view.adapter

import android.content.Context
import android.view.ViewGroup
import medicine.health.lab.test.medical.online.fitness.map.view.listener.labtest.TestResultClickListener
import medicine.health.lab.test.medical.online.fitness.map.view.viewholder.TestResultViewHolder
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.R
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.adapter.GenericRecyclerAdapter

class TestResultAdapter(context: Context?) :
    GenericRecyclerAdapter<List<String>, TestResultClickListener<List<String>>, TestResultViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestResultViewHolder {
        return TestResultViewHolder(inflate(R.layout.card_all_apps_portal_layout,parent))
    }
}