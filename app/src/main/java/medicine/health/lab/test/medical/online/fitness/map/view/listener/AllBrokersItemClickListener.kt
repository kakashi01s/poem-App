package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.listener

import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.listener.BaseRecyclerListener

interface CategoryStoresItemClickListener<T> : BaseRecyclerListener {
    fun CategoryStoresCardClick(item: T)
}