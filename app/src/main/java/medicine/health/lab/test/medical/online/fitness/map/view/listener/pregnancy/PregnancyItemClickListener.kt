package medicine.health.lab.test.medical.online.fitness.map.view.listener.pregnancy

import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.listener.BaseRecyclerListener

interface PregnancyItemClickListener<T> : BaseRecyclerListener {
    fun PregnancyCardClick(item: T)
}