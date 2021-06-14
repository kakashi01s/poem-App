package medicine.health.lab.test.medical.online.fitness.map.view.listener.labtest

import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.listener.BaseRecyclerListener

interface BookLabClickListener<T> : BaseRecyclerListener {
    fun onBookLabCardClick(item: T)
}