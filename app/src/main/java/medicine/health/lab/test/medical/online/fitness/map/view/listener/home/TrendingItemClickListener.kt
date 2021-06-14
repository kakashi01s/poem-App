package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.view.listener.home

import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.listener.BaseRecyclerListener

interface TrendingItemClickListener <T> : BaseRecyclerListener {
    fun onTrendingClickListener(item: T)
}