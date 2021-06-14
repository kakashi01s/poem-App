package medicine.health.lab.test.medical.online.fitness.map.view.listener.category


import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.base.listener.BaseRecyclerListener

interface MostUsefulAppsItemClickListener <T> : BaseRecyclerListener {
    fun onMostUsefulAppsCardClick(item: T)
}