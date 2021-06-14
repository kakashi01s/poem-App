package medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.week
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.data.DataFactory
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.data.DataService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import medicine.health.lab.test.medical.online.fitness.food.shopping.pregnancy.model.AllAppsModel

class HomeViewModel : ViewModel() {

    var allAppsLiveData: MutableLiveData<AllAppsModel> = MutableLiveData()
    var carouselImagesLiveData: MutableLiveData<AllAppsModel> = MutableLiveData()
    var trendingLiveData: MutableLiveData<AllAppsModel> = MutableLiveData()
    private var context: Context? = null
    var compositeDisposable: CompositeDisposable? = null

    fun loadData(){
        Log.d("TAG", "loadData: ")
        compositeDisposable = CompositeDisposable()
        fetchCarouselImages()
        fetchTrendingData()
        fetchAllApps()
    }

    private fun fetchAllApps(){
        Log.d("TAG", "fetchAllApps: ")
        val week: week? = week.get()
        val dataService: DataService? = week!!.getDataService()

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_RENT_ALL_APPS,DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("fetchAll", "fetchAllApps Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("fetchAll", "fetchAllApps Response ${t.getValues()}")
                changeAllAppsDataSet(t)
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchCarouselImages(){
        Log.d("TAG", "fetchCarouselImages: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_RENT_CAROUSEL_IMAGES,DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchCarouselImages Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchCarouselImages Response ${t.getValues()}")
                changeCarouselDataSet(t)
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }

    private fun fetchTrendingData(){
        Log.d("TAG", "fetchTrendingData: ")

        val dataService by lazy {
            DataFactory.create()
        }

        val disposable: Disposable?
        disposable = dataService?.fetchAllApps(DataFactory().URL_RENT_EXPLORER_IMAGES,DataFactory().KEY)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.doOnError(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Error ${t.localizedMessage}")
            })
            ?.subscribe(Consumer { t ->
                Log.d("TAG", "fetchTrendingData Response ${t.getValues()}")
                changeTrendingDataSet(t)
            })

        if (disposable != null) {
            compositeDisposable?.add(disposable)
        }
    }


    fun changeAllAppsDataSet(allAppsList: AllAppsModel){
        allAppsLiveData.value = allAppsList
    }

    fun changeCarouselDataSet(carouselList: AllAppsModel){
        carouselImagesLiveData.value = carouselList
    }

    fun changeTrendingDataSet(trendingList: AllAppsModel){
        trendingLiveData.value = trendingList
    }


    private fun unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable!!.isDisposed) {
            compositeDisposable!!.dispose()
        }
    }

    fun reset() {
        unSubscribeFromObservable()
        compositeDisposable = null
        context = null
    }

}